package com.grigoriyalexeev.statistician.core;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;
import java.util.Map;

public interface WordsUsageStatistician {
//    String measure(List<String> queryParameters) throws JsonProcessingException;
    Map<String, Map<String, Long>> measure(List<String> queryParameters) throws JsonProcessingException;
}
