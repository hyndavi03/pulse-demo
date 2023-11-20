package org.pulse.java;


import org.junit.jupiter.api.Test;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.quarkus.test.junit.QuarkusTest;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class Tester {

    @Test
    public void testSimpleLambdaSuccess() throws Exception {
        // you test your lambdas by invoking on http://localhost:8081
        // this works in dev mode too
        String payload ="{"
        + "\"accountNumber\": \"1234\","
        + "\"emailAddress\": \"12455\","
        + "\"brand\": \"s\","
        + "\"amount\": \"20\","
        + "\"accountName\": \"asdasd\","
        + "\"actionedBy\": \"Sdasdas\","
        + "\"statusCode\": \"40\""
        + "}";
APIGatewayProxyRequestEvent request = new APIGatewayProxyRequestEvent();
ObjectMapper mapper = new ObjectMapper();
System.out.println("input - "+mapper.writeValueAsString(request));
request.setBody(payload);
        
        given()
                .contentType("application/json")
                .accept("application/json")
                .body(request)
                .when()
                .post()
                .then()
                .statusCode(200);
    }

}
