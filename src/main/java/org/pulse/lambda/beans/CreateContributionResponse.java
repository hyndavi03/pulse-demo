package org.pulse.lambda.beans;


public class CreateContributionResponse {
    private String message;
    private boolean isSuccess;

    public void setMessage(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }

    public void setSuccess(boolean isSuccess){
        this.isSuccess = isSuccess;
    }

    public boolean isSuccess(){
        return isSuccess;
    }

    public CreateContributionResponse setResponse(){
        CreateContributionResponse resp = new CreateContributionResponse();
        resp.setMessage("success");
        resp.isSuccess = true;
        return resp;
    }
}
