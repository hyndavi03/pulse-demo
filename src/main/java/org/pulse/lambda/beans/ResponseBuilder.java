package org.pulse.lambda.beans;

public class ResponseBuilder {
    public static CreateContributionResponse successResponse(){
        CreateContributionResponse resp = new CreateContributionResponse();
        resp.setMessage("Success");
        resp.setSuccess(true);
        return resp;
    }

    public static CreateContributionResponse failureResponse(String error){
        CreateContributionResponse resp = new CreateContributionResponse();
        resp.setMessage(error == null ? "Default Error": error);
        resp.setSuccess(false);
        return resp;
        

    }

}


