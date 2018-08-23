package com.minipaypal.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.minipaypal.api.beans.TransactionResponse;

public class ApiUtil {

    public static String constructResponseString(String type, int id) {
        TransactionResponse transaction = new TransactionResponse(type, id);
        ObjectMapper mapper = new ObjectMapper();
        String res = null;
        try {
            res = mapper.writeValueAsString(transaction);
        } catch (
                JsonProcessingException e) {
            e.printStackTrace();
        }
        return res;
    }

}
