package com.grigoriyalexeev.statistician.core.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.grigoriyalexeev.statistician.core.WordsUsageStatistician;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DummyStatistician implements WordsUsageStatistician {
    @Override
    public Map<String, Map<String, Long>> measure(List<String> queryParameters) throws JsonProcessingException {
        Map<String, Map<String, Long>> statistics = new HashMap<>();
        Map<String, Long> wordStatistics = new HashMap<>();
        wordStatistics.put("dummyBlog", 2L);
        statistics.put("dummyWord", wordStatistics);
        return statistics;
    }
}
