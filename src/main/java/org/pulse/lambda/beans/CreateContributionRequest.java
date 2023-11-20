package org.pulse.lambda.beans;

import java.util.Date;
import java.util.UUID;

public class CreateContributionRequest {
    private String accountNumber;
    private String amount;
    private String actionedBy;
    private String brand;
    private String accountName;
    private String emailAddress;
    private String statusCode;
    private String id = Long.toString(System.currentTimeMillis());
    private String isCancelled;

    public void setIsCancelled(String isCancelled){
        this.isCancelled =  isCancelled;
    }

    public String isCancelled(){
        return this.isCancelled;
    }
    public void setAccountNumber(String accountNumber){
        this.accountNumber = accountNumber;
    }
    public String getAccountNumber(){
        return this.accountNumber;
    }
    
    public void setAmount(String amount){
        this.amount = amount;
    }

    public String getAmount(){
        return this.amount;
    }

    public void setActionedBy(String actionedBy){
        this.actionedBy = actionedBy;
    }

    public String getActionedBy(){
        return this.actionedBy;
    }

    public void setBrand(String brand){
        this.brand = brand;
    }

    public String getBrand(){
        return this.brand;
    }

    public void setAccountName(String accountName){
        this.accountName = accountName;
    }

    public String getAccountName(){
        return this.accountName;
    }

    public void setEmailAddress(String emailAddress){
        this.emailAddress = emailAddress;
    }

    public String getEmailAddress(){
        return this.emailAddress;
    }    

    public void setStatusCode(String statusCode){
        this.statusCode = statusCode;
    }

    public String getStatusCode(){
        return this.statusCode;
    }   

    public void setId(String id){
        this.id = id;
    }
    public String getId(){
        return this.id;
    }
}
