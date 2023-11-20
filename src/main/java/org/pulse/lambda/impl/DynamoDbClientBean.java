package org.pulse.lambda.impl;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Named;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

@ApplicationScoped
public class DynamoDbClientBean {

    @Produces
    @ApplicationScoped
    @Named("myDynamoDbClient")
    public DynamoDbClient produceDynamoDbEnhancedClient(DynamoDbClient dynamoDbClient) {
        return DynamoDbClient.create();
    }
}