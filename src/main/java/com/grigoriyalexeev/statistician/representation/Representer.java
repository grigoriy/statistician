package com.grigoriyalexeev.statistician.representation;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface Representer {
    String format(Object rawData) throws JsonProcessingException;
}
