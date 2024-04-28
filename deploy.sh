#!/bin/bash

#shellcheck disable=SC1101

# Define AWS Dynamodb endpoint and region if not already set and create DynamoDB table
aws dynamodb create-table \
    --table-name clients \
    --attribute-definitions AttributeName=cpf,AttributeType=S \
    --key-schema AttributeName=cpf,KeyType=HASH \
    --provisioned-throughput ReadCapacityUnits=1,WriteCapacityUnits=1 \
    --endpoint-url "https://dynamodb.us-west-2.amazonaws.com" \
    --region us-east-2


echo "Created table in DynamoDB completed!"


#############################################
# Deploy infrastructure using Terraform
#############################################

cd infra
terraform init
terraform validate
#terraform apply -auto-approve -var "aws_access_key=${AWS_ACCESS_KEY}" -var "aws_secret_key=${AWS_SECRET_KEY}"
#cd ..

echo "Deployment completed!"