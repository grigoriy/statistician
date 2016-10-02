package com.grigoriyalexeev.statistician.core;

import java.util.List;

public interface WordFinder {
    /**
     * @param word
     * @return locations where the word is used
     * @throws
     */
    List<String> find(String word) throws WordFinderException, UrlsExtractorException;
}
