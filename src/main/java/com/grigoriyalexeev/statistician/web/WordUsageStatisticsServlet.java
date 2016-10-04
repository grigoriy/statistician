package com.grigoriyalexeev.statistician.web;

import com.grigoriyalexeev.statistician.core.WordsUsageStatistician;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class WordUsageStatisticsServlet {
    private static final String QUERY_PARAMETER = "query";
    @Qualifier("wordUsageInBlogsStatistician")
    private final WordsUsageStatistician statistician;

    public WordUsageStatisticsServlet(WordsUsageStatistician statistician
//            , Representer representer
    ) {
//        super();
        this.statistician = statistician;
    }

    @GetMapping("/search")
//    Map<String, Map<String, Long>> doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    public Map<String, Map<String, Long>> doGet(@RequestParam(value=QUERY_PARAMETER) String[] words) throws IOException {
        List<String> wordsDistinct = Arrays.stream(words).distinct().collect(Collectors.toList());

//        String[] words = request.getParameterValues(QUERY_PARAMETER);
//        String statistics = statistician.measure(wordsDistinct);
//        try (PrintWriter out = response.getWriter()) {
//            out.write(statistics);
//        }

        return statistician.measure(wordsDistinct);
    }
}
