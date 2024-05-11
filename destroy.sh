#!/bin/bash

cd infra

echo "Destruindo a infraestrutura gerenciada pelo Terraform..."

terraform init
terraform destroy -auto-approve  \
  -var "aws_access_key=${AWS_ACCESS_KEY}" \
  -var "aws_secret_key=${AWS_SECRET_KEY}" \
  -var "aws_region=${AWS_REGION}"

echo "Deployment completed!"