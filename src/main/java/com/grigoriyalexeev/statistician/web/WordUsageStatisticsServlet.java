package com.grigoriyalexeev.statistician.web;

import com.grigoriyalexeev.statistician.core.WordsUsageStatistician;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class WordUsageStatisticsServlet {
    private static final Logger log = LoggerFactory.getLogger(WordUsageStatisticsServlet.class);
    private static final String QUERY_PARAMETER = "query";
    private final WordsUsageStatistician statistician;

    public WordUsageStatisticsServlet(WordsUsageStatistician statistician) {
        this.statistician = statistician;
    }

    @GetMapping(value = "/search", produces = APPLICATION_JSON_VALUE)
    @ResponseBody
    ResponseEntity<Map<String, Map<String, Long>>> doGet(@RequestParam(value = QUERY_PARAMETER) String[] words) throws IOException {
        List<String> wordsDistinct = Arrays.stream(words)
                                            .distinct()
                                            .collect(Collectors.toList());
        try {
            Map<String, Map<String, Long>> statistics = statistician.measure(wordsDistinct);
            return ResponseEntity.ok(statistics);
        } catch (Exception e) {
            log.error("Error while processing request [{}]", words, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
