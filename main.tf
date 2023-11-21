provider "aws" {
  region = "ap-south-1"  # Change to your desired region
}

resource "aws_lambda_function" "lambda_function" {
  function_name    = var.function_name
  runtime          = var.runtime
  handler          = var.handler
  role             = var.iam_role_arn
  timeout          = 10
  memory_size      = 256
  publish          = true
  reserved_concurrent_executions = var.concurrency

  s3_bucket        = "s3-bucket-for-lambda-demo"
  s3_key           = "s3://s3-bucket-for-lambda-demo/create-contribution-lambda_$(date +'%Y-%m-%d').zip"

  environment {
    variables = {
      KEY1 = "VALUE1"
      KEY2 = "VALUE2"
    }
  }

  tracing_config {
    mode = "Active"
  }

  lifecycle {
    create_before_destroy = true
  }
}

resource "aws_iam_role" "lambda_role" {
  name = "lambda-exec-role"

  assume_role_policy = jsonencode({
    Version = "2012-10-17",
    Statement = [
      {
        Action = "sts:AssumeRole",
        Effect = "Allow",
        Principal = {
          Service = "lambda.amazonaws.com",
        },
      },
    ],
  })
}

resource "aws_iam_role_policy_attachment" "lambda_policy" {
  policy_arn = "arn:aws:iam::aws:policy/service-role/AWSLambdaBasicExecutionRole"
  role       = aws_iam_role.lambda_role.name
}
