package org.pulse.lambda.impl;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import org.apache.camel.Converter;

@Converter
public class APIGatewayProxyResponseEventConverter {
    @Converter
    public static APIGatewayProxyResponseEvent convertStringToAPIGatewayProxyResponseEvent(String input) {
        // Your conversion logic here
        // For simplicity, this example assumes a direct mapping from input to APIGatewayProxyResponseEvent
        System.out.println("from type converter: "+input);
        APIGatewayProxyResponseEvent responseEvent = new APIGatewayProxyResponseEvent();
        responseEvent.setBody(input);
        return responseEvent;
    }
}