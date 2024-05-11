terraform {
  required_providers {
    aws = {
      source  = "hashicorp/aws"
      version = "5.40.0"
    }
  }

  required_version = ">= 1.0.0"
}

# Data source para obter o account ID atual, usado na URL do container
data "aws_caller_identity" "current" {}

provider "aws" {
  region = "us-east-2"
}

# Attach Policies to IAM User
resource "aws_iam_user_policy_attachment" "ecr_policy_attachment" {
  user       = "jairmendes-dev"
  policy_arn = "arn:aws:iam::851725557582:policy/ECRCreateRepositoryPolicy"
}

resource "aws_iam_user_policy_attachment" "ecs_policy_attachment" {
  user       = "jairmendes-dev"
  policy_arn = "arn:aws:iam::851725557582:policy/ECSDescribeTaskDefinitionPolicy"
}

resource "aws_iam_user_policy_attachment" "ec2_policy_attachment" {
  user       = "jairmendes-dev"
  policy_arn = "arn:aws:iam::851725557582:policy/EC2CreateVpcPolicy"
}

# Create ECS Cluster Fargate
resource "aws_ecs_cluster" "tc_ms_client_cluster" {
  name = "tc-ms-client-cluster"
}

# ECS Task Definition
resource "aws_ecs_task_definition" "tc_ms_client_cluster_task" {
  family                   = "tc-ms-client-task"
  network_mode             = "awsvpc"
  requires_compatibilities = ["FARGATE"]
  execution_role_arn       = aws_iam_user_policy_attachment.ec2_policy_attachment.policy_arn
  cpu                      = "512" # 0.5 vCPU
  memory                   = "1024" # 1GB

  container_definitions = jsonencode([
    {
      name      = "ts-ms-client-container"
      image     = "${aws_caller_identity.current.account_id}.dkr.ecr.${var.aws_region}.amazonaws.com/tc-ms-client:latest"
      essential = true
      portMappings = [
        {
          containerPort = 80
          hostPort      = 80
          protocol      = "tcp"
        },
        {
          containerPort = 8080
          hostPort      = 8080
          protocol      = "tcp"
        }
      ]
    }
  ])
}

# Criar a IAM Role para a Task Execution
resource "aws_iam_role" "ecs_task_execution_role" {
  name = "ecs_task_execution_role"

  assume_role_policy = jsonencode({
    Version = "2012-10-17"
    Statement = [
      {
        Action = "sts:AssumeRole"
        Principal = {
          Service = "ecs-tasks.amazonaws.com"
        }
        Effect = "Allow"
      },
    ]
  })
}

# 3. Criar Security Group para o cluster
resource "aws_security_group" "tc_ms_client_sg" {
  name        = "tc-ms-client-sg"
  description = "Allow all inbound traffic"
  vpc_id      = "vpc-025dcd22cfaa4c66a"

  ingress {
    description      = "Allow all inbound traffic"
    from_port        = 0
    to_port          = 0
    protocol         = "-1" # All
    cidr_blocks      = ["0.0.0.0/0"]
  }

  egress {
    description      = "Allow all outbound traffic"
    from_port        = 0
    to_port          = 0
    protocol         = "-1" # All
    cidr_blocks      = ["0.0.0.0/0"]
  }
}

