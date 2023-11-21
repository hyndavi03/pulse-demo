function_name    = "my-lambda-function"
runtime          = "java17"  # Change to your desired runtime
handler          = "io.quarkus.amazon.lambda.runtime.QuarkusStreamHandler::handleRequest"  # Change to your actual handler
iam_role_arn     = "arn:aws:iam::316412582500:role/dynamodballaccess"  # Change to your IAM role ARN
concurrency      = 2  # Change to your desired concurrency limit
create_lambda_function = true
create_lambda_role     = true
