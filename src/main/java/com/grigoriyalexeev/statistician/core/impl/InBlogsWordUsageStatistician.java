package com.grigoriyalexeev.statistician.core.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.grigoriyalexeev.statistician.core.WordFinder;
import com.grigoriyalexeev.statistician.core.WordUsageStatisticsAssembler;
import com.grigoriyalexeev.statistician.core.WordsUsageStatistician;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class InBlogsWordUsageStatistician implements WordsUsageStatistician {
    private static final Logger log = LoggerFactory.getLogger(InBlogsWordUsageStatistician.class);
    private final WordFinder wordFinder;
    private final WordUsageStatisticsAssembler wordUsageStatisticsAssembler;

    public InBlogsWordUsageStatistician(WordFinder wordFinder, WordUsageStatisticsAssembler wordUsageStatisticsAssembler) {
        this.wordFinder = wordFinder;
        this.wordUsageStatisticsAssembler = wordUsageStatisticsAssembler;
    }

    @Override
    public Map<String, Map<String,Long>> measure(List<String> words) throws JsonProcessingException {
        final Map<String, Future<Map<String, Long>>> futureStatistics = new HashMap<>(words.size() * 2);
        final ExecutorService executor = Executors.newFixedThreadPool(10);
        for (String word : words) {
            Future<Map<String, Long>> future = executor.submit(() -> {
                final List<String> rawData = wordFinder.find(word);
                return wordUsageStatisticsAssembler.assemble(rawData);
            });
            futureStatistics.put(word, future);
        }
        Map<String, Map<String, Long>> statistics = new HashMap<>(futureStatistics.size() * 2);
        for (Map.Entry<String, Future<Map<String, Long>>> futureWordStatistics : futureStatistics.entrySet()) {
            try {
                statistics.put(futureWordStatistics.getKey(), futureWordStatistics.getValue().get());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(String.format("Failed to get statistics for word [%s].", futureWordStatistics.getKey()), e);
            }
        }
        return statistics;
    }
}
