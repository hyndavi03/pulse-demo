package org.pulse.lambda;

import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;

import jakarta.inject.Named;

@Named("createContributionAwsLambdaHandler")
public class CreateContributionLambda implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    @Produce("direct:main")
    ProducerTemplate producerTemplate;

    @Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent input, Context context) {
        
        APIGatewayProxyResponseEvent response = producerTemplate.requestBody("direct:main", input,APIGatewayProxyResponseEvent.class);
        System.out.println("Response - "+response.getBody());
        return response;
    }
}
