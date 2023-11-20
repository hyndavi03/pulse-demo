package org.pulse.lambda.impl;

import java.util.HashMap;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.ComparisonOperator;
import software.amazon.awssdk.services.dynamodb.model.Condition;

@ApplicationScoped
@Named("buildDynamoDBQueryRequest")
public class DynamoDBQueryRequestBuilder implements Processor{
    
    @Override
    public void process(Exchange exchange) throws Exception {
        HashMap<String,Condition> queryFilter = new HashMap<>();
        queryFilter.put("accountNumber", Condition.builder().
        comparisonOperator(ComparisonOperator.EQ).
        attributeValueList(AttributeValue.builder().s((String) exchange.getIn().getHeader("queryInAccountNumber")).build()).build());
        exchange.getIn().setHeader("CamelAwsDdbOperation","Query");
        exchange.getIn().setHeader("CamelAwsDdbKeyConditions",queryFilter);
    }  
    
}
