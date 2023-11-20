package org.pulse.lambda.impl;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

@ApplicationScoped
@Named("filterActiveContributionsDBEntries")
public class FilterActiveContributionsDBEntries implements Processor{
    
    @Override
    public void process(Exchange exchange) throws Exception {
        @SuppressWarnings("unchecked")
        List<HashMap<String, AttributeValue>> req =
                (List<HashMap<String, AttributeValue>>) exchange.getIn().getBody(List.class);
        if (req == null ){
            exchange.getIn().setHeader("CamelAwsDdbItem", "[]");
        }
        else{
            List<HashMap<String, AttributeValue>> filteredEntries = new ArrayList<>();
            for(HashMap<String,AttributeValue> entry : req){
                if(entry.get("isCancelled").s() == "false")
                    filteredEntries.add(entry);
            }
            exchange.getIn().setHeader("CamelAwsDdbItem", filteredEntries);
        }
    }  
    
}

