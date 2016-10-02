package com.grigoriyalexeev.statistician.representation.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.grigoriyalexeev.statistician.representation.Representer;

public class JsonRepresenter implements Representer {
    @Override
    public String format(Object rawData) throws JsonProcessingException {
//        return rawData.toString();
        return new ObjectMapper().
                writerWithDefaultPrettyPrinter().
                writeValueAsString(rawData);
    }
}
