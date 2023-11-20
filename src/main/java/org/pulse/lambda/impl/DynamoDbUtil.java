package org.pulse.lambda.impl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;

import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DynamoDbUtil {

    public static <T> HashMap<String, AttributeValue> pojoToDynamoDbItem(T pojo) {
        HashMap<String, AttributeValue> item = new HashMap<>();

        // Get the class of the POJO
        Class<?> pojoClass = pojo.getClass();

        // Iterate over the fields of the POJO
        for (Field field : pojoClass.getDeclaredFields()) {
            field.setAccessible(true); // Make private fields accessible

            String attributeName = field.getName();
            Object attributeValue;

            try {
                attributeValue = field.get(pojo);
            } catch (IllegalAccessException e) {
                throw new RuntimeException("Error accessing field: " + attributeName, e);
            }

            // Map the attribute name and value to DynamoDB format
            if (attributeValue != null) {
                AttributeValue dynamoDbAttributeValue = convertToDynamoDbAttributeValue(attributeValue);
                item.put(attributeName, dynamoDbAttributeValue);
            }
        }

        return item;
    }


    public static <T> HashMap<String, AttributeValue> pojoToPartitionKeyMap(T pojo, ArrayList<String> pKeyList) {
        HashMap<String, AttributeValue> item = new HashMap<>();

        // Get the class of the POJO
        Class<?> pojoClass = pojo.getClass();

        // Iterate over the fields of the POJO
        for (Field field : pojoClass.getDeclaredFields()) {
            field.setAccessible(true); // Make private fields accessible

            String attributeName = field.getName();
            Object attributeValue;
            if(pKeyList.contains(attributeName)){
                try 
                {
                    attributeValue = field.get(pojo);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException("Error accessing field: " + attributeName, e);
                }

                // Map the attribute name and value to DynamoDB format
                if (attributeValue != null) 
                {
                    AttributeValue dynamoDbAttributeValue = convertToDynamoDbAttributeValue(attributeValue);
                    item.put(attributeName, dynamoDbAttributeValue);
                }
            }
            
        }

        return item;
    }

    private static AttributeValue convertToDynamoDbAttributeValue(Object value) {
        // Determine the type of AttributeValue based on the data type of the field

        if (value instanceof String) {
            return AttributeValue.builder().s((String) value).build();
        } else if (value instanceof Number) {
            // Check if it's an integer or a decimal
            if (value instanceof Integer || value instanceof Long) {
                return AttributeValue.builder().n(String.valueOf(value)).build();
            } else if (value instanceof Float || value instanceof Double) {
                return AttributeValue.builder().n(String.valueOf(value)).build();
            } else if (value instanceof String) {
                return AttributeValue.builder().s(String.valueOf(value)).build();
            }else {
                // Handle other numeric types as needed
                throw new IllegalArgumentException("Unsupported numeric type: " + value.getClass());
            }
        } else if (value instanceof Boolean) {
            return AttributeValue.builder().bool((Boolean) value).build();
        } else {
            // Handle other data types as needed
            throw new IllegalArgumentException("Unsupported data type: " + value.getClass());
        }
    }
}