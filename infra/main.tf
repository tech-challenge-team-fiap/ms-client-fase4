terraform {
  required_providers {
    aws = {
      source  = "hashicorp/aws"
      version = "5.40.0"
    }
  }

  required_version = ">= 1.0.0"
}

data "aws_caller_identity" "current" {}
data "aws_region" "current" {}

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

resource "aws_ecs_cluster" "tc_ms_client_cluster" {
  name = "tc-ms-client-cluster"
}

resource "aws_ecs_task_definition" "tc_ms_client_cluster_task" {
  family                   = "tc-ms-client-task"
  network_mode             = "awsvpc"
  requires_compatibilities = ["FARGATE"]
  execution_role_arn       = aws_iam_role.ecs_task_execution_role.arn
  cpu                      = "512"
  memory                   = "1024"

  container_definitions = jsonencode([
    {
      name      = "ts-ms-client-container"
      image     = "${data.aws_caller_identity.current.account_id}.dkr.ecr.${data.aws_region.current.name}.amazonaws.com/tc-ms-client:latest"
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

resource "aws_security_group" "tc_ms_client_sg" {
  name        = "tc-ms-client-sg"
  description = "Allow all inbound traffic"
  vpc_id      = "vpc-025dcd22cfaa4c66a"  # Consider dynamic retrieval

  ingress {
    description      = "Allow all inbound traffic"
    from_port        = 0
    to_port          = 0
    protocol         = "-1"
    cidr_blocks      = ["0.0.0.0/0"]
  }

  egress {
    description      = "Allow all outbound traffic"
    from_port        = 0
    to_port          = 0
    protocol         = "-1"
    cidr_blocks      = ["0.0.0.0/0"]
  }
}
