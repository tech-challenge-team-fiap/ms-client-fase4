terraform {
  required_providers {
    aws = {
      source  = "hashicorp/aws"
      version = "5.40.0"
    }
  }

  required_version = ">= 1.0.0"
}

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
#
## VPC Setting
#module "main_vpc" {
#  source  = "terraform-aws-modules/vpc/aws"
#  version = "5.5.3"
#
#  name            = "main_vpc"
#  cidr            = "10.0.0.0/16"
#  azs             = ["us-east-2a", "us-east-2b", "us-east-2c"]
#  private_subnets = ["10.0.1.0/24", "10.0.2.0/24", "10.0.3.0/24"]
#  public_subnets  = ["10.0.4.0/24", "10.0.5.0/24", "10.0.6.0/24"]
#
#  enable_nat_gateway   = true
#  single_nat_gateway   = true
#  enable_dns_hostnames = true
#}

# ECR Setting
#resource "aws_ecr_repository" "tech_challenge" {
#  name                 = "jaircmendes/techchallenge:tcclient"
#  image_tag_mutability = "MUTABLE"
#  force_delete         = true
#
#  image_scanning_configuration {
#    scan_on_push = true
#  }
#}

# ECS Cluster
resource "aws_ecs_cluster" "tech_challenge_cluster" {
  name = "tech_challenge_cluster"
}

# ECS Task Definition
resource "aws_ecs_task_definition" "tech_challenge_client_task" {
  family                   = "tech_challenge_client_task"
  network_mode             = "awsvpc"
  requires_compatibilities = ["FARGATE"]
  cpu                      = "256"
  memory                   = "512"

  container_definitions = jsonencode([
    {
      name        : "tech-challenge-client-container",
      image       : "jaircmendes/techchallenge:tcclient",
      cpu         : 256,
      memory      : 512,
      essential   : true,
      portMappings: [
        {
          containerPort: 8080,
          hostPort      : 8080
        }
      ]
    }
  ])
}

# ECS Service
resource "aws_ecs_service" "tech_challenge_service" {
  name            = "tech-challenge-client-service"
  cluster         = aws_ecs_cluster.tech_challenge_cluster.id
  task_definition = aws_ecs_task_definition.tech_challenge_client_task.arn
  launch_type     = "FARGATE"

  network_configuration {
    subnets         = ["subnet-04715a6b7400f9757", "subnet-06f39bcc16f6bd4ce", "subnet-010e2a0f51a3d00bc"]
    security_groups = ["sg-0edafd1e8b0f706e9"]
    assign_public_ip = true
  }

  lifecycle {
    create_before_destroy = true
  }
}