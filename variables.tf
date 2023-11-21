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

variable "create_lambda_function" {
  description = "Flag to create or update Lambda function"
  type        = bool
  default     = true
}

variable "create_lambda_role" {
  description = "Flag to create or update IAM role for Lambda function"
  type        = bool
  default     = true
}