package org.pulse.lambda.impl;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

@ApplicationScoped
@Named("apiGwResponseBuilder")
public class ApiGWResponseBuilder implements Processor{
    ObjectMapper mapper = new ObjectMapper();
    @Override
    public void process(Exchange exchange) throws Exception {
        APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent();
        response.setBody(mapper.writeValueAsString(exchange.getIn().getBody()));
        String statusCode = (String) exchange.getIn().getHeader("outStatusCode");
        if(statusCode == null)
            response.setStatusCode(200);
        else
            response.setStatusCode(Integer.parseInt(statusCode));
        exchange.getIn().setBody(response,APIGatewayProxyResponseEvent.class);
    }
}
