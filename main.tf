provider "aws" {
  region = "ap-south-1"
} 

data "aws_iam_role" "existing_lambda_role" {
  name = "lambda-exec-role"
}

resource "aws_iam_role" "lambda_role" {
  count = data.aws_iam_role.existing_lambda_role ? 0 : 1
  name  = "lambda-exec-role"
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
  count = var.create_lambda_function ? 1 : 0

  role       = aws_iam_role.lambda_role[0].name
  policy_arn = "arn:aws:iam::aws:policy/service-role/AWSLambdaBasicExecutionRole"
}

resource "aws_lambda_function" "lambda_function" {
  count = var.create_lambda_function ? 1 : 0

  function_name    = var.function_name
  runtime          = var.runtime
  handler          = var.handler
  role             = aws_iam_role.lambda_role[0].arn
  timeout          = 10
  memory_size      = 256
  publish          = true
  reserved_concurrent_executions = var.concurrency

  s3_bucket = "s3-bucket-for-lambda-demo"
  s3_key    = "function.zip"

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



  # Conditional expression to create or update the Lambda function
  
}
