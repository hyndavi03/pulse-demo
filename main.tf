provider "aws" {
  region = "ap-south-1"
}

resource "aws_iam_role" "lambda_role" {
  count = var.create_lambda_role ? 1 : 0

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
  count = var.create_lambda_role ? 1 : 0

  policy_arn = "arn:aws:iam::aws:policy/service-role/AWSLambdaBasicExecutionRole"
  role       = aws_iam_role.lambda_role[0].name
}

resource "aws_lambda_function" "lambda_function" {
  function_name = var.function_name
  runtime       = var.runtime
  handler       = var.handler
  role          = var.create_lambda_role ? aws_iam_role.lambda_role[0].arn : var.iam_role_arn
  timeout       = 10
  memory_size   = 256
  publish       = true
  reserved_concurrent_executions = var.concurrency

  s3_bucket = var.s3_bucket
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

  depends_on = var.create_lambda_role ? [aws_iam_role_policy_attachment.lambda_policy] : []

  lifecycle {
    create_before_destroy = true
  }
}
