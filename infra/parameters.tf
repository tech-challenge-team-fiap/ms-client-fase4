data "aws_ssm_parameter" "aws_dynamo_url_endpoint_param" {
  name = "/msclient/AWS-DYNAMO-ENDPOINT"
}

data "aws_ssm_parameter" "aws_access_key_id_param" {
  name = "/msclient/AWS_ACCESS_KEY_ID"
}

data "aws_ssm_parameter" "aws_secret_key_param" {
  name = "/msclient/AWS_SECRET_ACCESS_KEY"
}

data "aws_ssm_parameter" "aws_vpc_param" {
  name = "/msclient/AWS_VPC_ID"
}