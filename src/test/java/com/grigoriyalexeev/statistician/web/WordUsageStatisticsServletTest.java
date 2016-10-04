package com.grigoriyalexeev.statistician.web;

import com.grigoriyalexeev.statistician.core.WordsUsageStatistician;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration("/WEB-INF/applicationContext.xml")
//@WebAppConfiguration("classpath:/WEB-INF/web.xml")
public class WordUsageStatisticsServletTest {
//    @Autowired
    private WordUsageStatisticsServlet wordUsageStatisticsServlet;

    @Ignore
    @Test
    public void doGet() throws Exception {
        HttpServletRequest requestMock = mock(HttpServletRequest.class);
        String[] queries = { "test", "java" };
        when(requestMock.getParameterValues("query")).thenReturn(queries);
        HttpServletResponse responseMock = mock(HttpServletResponse.class);
        when(responseMock.getWriter()).thenReturn(new PrintWriter(System.out));

        System.out.println(wordUsageStatisticsServlet.doGet(new String[]{"test"}));
//        wordUsageStatisticsServlet.doGet(requestMock, responseMock);
    }

    @Ignore
    @Test
    public void doGet_whenNoQueries_passesEmptyArrayToStatistician() throws IOException {
        HttpServletRequest requestMock = mock(HttpServletRequest.class);
        String[] queries = {};
        when(requestMock.getParameterValues("query")).thenReturn(queries);
        HttpServletResponse responseMock = mock(HttpServletResponse.class);
        when(responseMock.getWriter()).thenReturn(new PrintWriter(System.out));
        WordsUsageStatistician statistician = mock(WordsUsageStatistician.class);

//        wordUsageStatisticsServlet.doGet(requestMock, responseMock);

//        verify(statistician, );
    }
}