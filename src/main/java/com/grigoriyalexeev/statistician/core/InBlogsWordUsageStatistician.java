package com.grigoriyalexeev.statistician.core;

import com.fasterxml.jackson.core.JsonProcessingException;
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
//    private final Representer representer;
    private final ExecutorService executor = Executors.newFixedThreadPool(10);

    public InBlogsWordUsageStatistician(WordFinder wordFinder,
                                        WordUsageStatisticsAssembler wordUsageStatisticsAssembler
//            , Representer representer
    ) {
        this.wordFinder = wordFinder;
        this.wordUsageStatisticsAssembler = wordUsageStatisticsAssembler;
//        this.representer = representer;
    }

    @Override
//    public String measure(List<String> words) throws JsonProcessingException {
    public Map<String, Map<String,Long>> measure(List<String> words) throws JsonProcessingException {
        final Map<String, Future<Map<String, Long>>> futureStatistics = new HashMap<>(words.size() * 2);
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
                log.error("Was interrupted", e);
                Thread.currentThread().interrupt();
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                log.error("Failed to get statistics for word [{}].", futureWordStatistics.getKey(), e);
                // TODO handle the missing word statistics somehow
            }
        }
//        return representer.format(statistics);
        return statistics;
    }
}
