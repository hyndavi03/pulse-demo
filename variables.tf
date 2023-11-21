variable "function_name" {
  description = "Name of the Lambda function"
  type        = string
}

variable "runtime" {
  description = "Runtime for the Lambda function"
  type        = string
}

variable "s3_bucket" {
  description = "Concurrency limit for the Lambda function"
  type        = number
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
