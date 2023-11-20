package org.pulse.lambda.impl;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

@ApplicationScoped
@Named("apiGwCamelInputLoader")
public class ApiGWCamelInputLoader implements Processor{
    
    @Override
    public void process(Exchange exchange) throws Exception {
        APIGatewayProxyRequestEvent request = exchange.getIn().getBody(APIGatewayProxyRequestEvent.class);
        exchange.getIn().setBody(request.getBody());
        exchange.getIn().setHeader("inQueryParameters",request.getQueryStringParameters());
        exchange.getIn().setHeader("inPathParameters", request.getPathParameters());
        exchange.getIn().setHeader("inPath",request.getPath());
        exchange.getIn().setHeader("inHeaders",request.getHeaders());
    }
}
