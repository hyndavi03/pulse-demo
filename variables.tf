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
