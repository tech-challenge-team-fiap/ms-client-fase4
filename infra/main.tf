provider "aws" {
  region = "us-east-2"
}

data "aws_caller_identity" "current" {}
data "aws_region" "current" {}

resource "aws_ecs_cluster" "tc_ms_client_cluster" {
  name = "tc-ms-client-cluster"

  setting {
    name  = "containerInsights"
    value = "enabled"
  }
}

resource "aws_ecs_task_definition" "tc_ms_client_task" {
  family                   = "tc-ms-client-task"
  network_mode             = "awsvpc"
  requires_compatibilities = ["FARGATE"]
  execution_role_arn       = aws_iam_role.ecs_execution_role.arn
  cpu                      = 512
  memory                   = 1024

  runtime_platform {
    operating_system_family = "LINUX"
    cpu_architecture        = "X86_64"
  }

  container_definitions = jsonencode(
    [
      {
        name : "tc-ms-client-container",
        image : "${data.aws_caller_identity.current.account_id}.dkr.ecr.${data.aws_region.current.name}.amazonaws.com/tc-ms-client:latest",
        cpu : 512,
        memory : 1024,
        essential : true,
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
    ]
  )
}

resource "aws_ecs_service" "tc_ms_client_service" {
  name            = "tc-ms-client-service"
  cluster         = aws_ecs_cluster.tc_ms_client_cluster.id
  task_definition = aws_ecs_task_definition.tc_ms_client_task.arn
  launch_type     = "FARGATE"
  desired_count   =  2

  network_configuration {
    subnets          = ["subnet-04715a6b7400f9757", "subnet-06f39bcc16f6bd4ce", "subnet-010e2a0f51a3d00bc"]
    security_groups  = [aws_security_group.tc_ms_client_sg.id]
    assign_public_ip = true
  }
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

resource "aws_iam_role_policy_attachment" "ecs_execution_policy_attachment" {
  role       = aws_iam_role.ecs_execution_role.name
  policy_arn = "arn:aws:iam::aws:policy/service-role/AmazonECSTaskExecutionRolePolicy"
}

#resource "aws_iam_role" "ecs_execution_role" {
#  name = "ecs_execution_role"
#
#  assume_role_policy = jsonencode({
#    Version   = "2012-10-17",
#    Statement = [
#      {
#        Effect    = "Allow",
#        Principal = {
#          Service = "ecs-tasks.amazonaws.com"
#        },
#        Action = "sts:AssumeRole"
#      }
#    ]
#  })
#}