provider "aws" {
  region = "us-east-1"
}

resource "aws_ecs_cluster" "tcclient_cluster" {
  name = "tcclient_cluster"
}

resource "aws_ecs_task_definition" "tcclient_task" {
  family                   = "tcclient_task"
  network_mode             = "awsvpc"
  requires_compatibilities = ["FARGATE"]
  cpu                      = "256"
  memory                   = "512"

  container_definitions = jsonencode(
    [
      {
        name : "tcclient-container",
        image : "tc-client",
        cpu : 256,
        memory : 512,
        essential : true,
        portMappings : [
          {
            containerPort : 3000,
            hostPort : 3000
          }
        ]
      }
    ]
  )
}

resource "aws_ecs_service" "tcclient_service" {
  name            = "tcclient-service"
  cluster         = aws_ecs_cluster.tcclient_cluster.id
  task_definition = aws_ecs_task_definition.tcclient_task.arn
  launch_type     = "FARGATE"

  network_configuration {
    subnets         = ["subnet-010e2a0f51a3d00bc", "subnet-06f39bcc16f6bd4ce", "subnet-04715a6b7400f9757"]
    security_groups = ["sg-0edafd1e8b0f706e9"]
  }
}

resource "aws_iam_role" "ecs_execution_role-tc-client" {
  name = "ecs_execution_role-tc-client"

  assume_role_policy = jsonencode({
    Version   = "2012-10-17",
    Statement = [
      {
        Action    = "sts:AssumeRole",
        Effect    = "Allow",
        Principal = {
          Service = "ecs-tasks.amazonaws.com"
        }
      }
    ]
  })
}