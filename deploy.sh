#!/bin/bash

export AWS_DYNAMODB_ENDPOINT="https://dynamodb.us-east-2.amazonaws.com"
export AWS_REGION="us-east-2"

# Verify AWS environment variables
if [[ -z "$AWS_DYNAMODB_ENDPOINT" ]]; then
  echo "Error: AWS_DYNAMODB_ENDPOINT is not set or empty."
  exit 1
fi

if [[ -z "$AWS_REGION" ]]; then
  echo "Error: AWS_REGION is not set or empty."
  exit 1
fi

# Function to check if the DynamoDB table exists
check_table_exists() {
  aws dynamodb describe-table \
      --table-name clients \
      --endpoint-url "$AWS_DYNAMODB_ENDPOINT" \
      --region "$AWS_REGION" &> /dev/null
}

# Create DynamoDB table if it does not exist
if check_table_exists; then
  echo "Table 'clients' already exists in DynamoDB."
else
  echo "Table 'clients' does not exist. Creating table..."
  aws dynamodb create-table \
      --table-name clients \
      --attribute-definitions AttributeName=cpf,AttributeType=S \
      --key-schema AttributeName=cpf,KeyType=HASH \
      --provisioned-throughput ReadCapacityUnits=1,WriteCapacityUnits=1 \
      --endpoint-url "$AWS_DYNAMODB_ENDPOINT" \
      --region "$AWS_REGION"

  if [ $? -eq 0 ]; then
    echo "Created table in DynamoDB completed successfully!"
  else
    echo "Failed to create table in DynamoDB."
    exit 1
  fi
fi

#############################################
# Deploy infrastructure using Terraform
#############################################

cd infra
terraform init
terraform validate
terraform apply -auto-approve \
  -var "aws_access_key=${AWS_ACCESS_KEY_ID}" \
  -var "aws_secret_key=${AWS_SECRET_ACCESS_KEY}" \
  -var "aws_region=${AWS_REGION}"

#cd ..

echo "Deployment completed!"