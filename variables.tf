variable "function_name" {
  description = "Name of the Lambda function"
  type        = string
}

variable "runtime" {
  description = "Runtime for the Lambda function"
  type        = string
}

variable "handler" {
  description = "Handler for the Lambda function"
  type        = string
}

variable "iam_role_arn" {
  description = "ARN of the IAM role for the Lambda function"
  type        = string
}

variable "concurrency" {
  description = "Concurrency limit for the Lambda function"
  type        = number
}

variable "create_lambda_role" {
  description = "Whether to create the Lambda role"
  type        = bool
}

variable "create_lambda_function" {
  description = "Whether to create the Lambda function"
  type        = bool
}

variable "s3_key" {
  description = "The object key for the Lambda function package in S3"
}