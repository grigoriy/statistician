package com.grigoriyalexeev.statistician.model;

import java.util.Map;

public class WordsUsageByDomain {
    private final Map<String, Map<String, Integer>> wordsUsageByDomain;

    public WordsUsageByDomain(Map<String, Map<String, Integer>> wordsUsageByDomain) {
        this.wordsUsageByDomain = wordsUsageByDomain;
    }
}
