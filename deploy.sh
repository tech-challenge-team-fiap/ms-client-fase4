#!/bin/bash

#############################################
# Created database with dynamodb
#############################################
 shellcheck disable=SC1101
aws dynamodb create-table \
    --table-name clients \
    --attribute-definitions AttributeName=cpf,AttributeType=S \
    --key-schema AttributeName=cpf,KeyType=HASH \
    --provisioned-throughput ReadCapacityUnits=1,WriteCapacityUnits=1 \
    --endpoint-url ${AWS_DYNAMODB_ENDPOINT}
    --region ${AWS_REGION}

echo "Created table in Dynamodb completed!"

#############################################
# Deploy infrastructure using Terraform
#############################################

cd infra
#terraform init
terraform validate
#terraform apply -auto-approve -var "aws_access_key=${AWS_ACCESS_KEY}" -var "aws_secret_key=${AWS_SECRET_KEY}"
cd ..

echo "Deployment completed!"