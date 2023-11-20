package org.pulse.lambda.impl;

import java.util.HashMap;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.pulse.lambda.beans.CreateContributionRequest;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

@ApplicationScoped
@Named("dynamoDbPayloadConstructor")
public class DynamoDbPayloadConstructor implements Processor{
    
    @Override
    public void process(Exchange exchange) throws Exception {
        CreateContributionRequest req = exchange.getIn().getBody(CreateContributionRequest.class);
        req.setIsCancelled("false");
        HashMap<String, AttributeValue> response = DynamoDbUtil.pojoToDynamoDbItem(req);
        exchange.getIn().setHeader("CamelAwsDdbItem", response);
    }  
    
}
