package com.grigoriyalexeev.statistician.core.impl;

import com.grigoriyalexeev.statistician.core.UrlsExtractorException;
import com.grigoriyalexeev.statistician.core.WordFinder;
import com.grigoriyalexeev.statistician.core.WordFinderException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Semaphore;

public class YandexBlogsWordFinder implements WordFinder {
    private static final Logger log = LoggerFactory.getLogger(YandexBlogsWordFinder.class);
    private final String yandexApiUrl;
    private final int maxUrls;
    private final Semaphore semaphore;
    private final YandexRssUrlsExtractor urlsExtractor;

    public YandexBlogsWordFinder(String yandexApiUrl, int maxUrls, int maxConnections, YandexRssUrlsExtractor urlsExtractor) {
        this.yandexApiUrl = yandexApiUrl;
        this.semaphore  = new Semaphore(maxConnections, true);
        this.maxUrls = maxUrls;
        this.urlsExtractor = urlsExtractor;
    }

    /**
     * Gets links to blogs that mention a given word.
     */
    @Override
    public List<String> find(String word) throws UrlsExtractorException, WordFinderException {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet request = new HttpGet(buildUrl(word));
            semaphore.acquire();
            CloseableHttpResponse response = httpClient.execute(request);
            // TODO move extraction logic out of the restricted area for better throughput. E.g. read to byte array and parse it outside.
            List<String> result = urlsExtractor.extract(response.getEntity().getContent());
            response.close();
            semaphore.release();
            return result;
        } catch (IOException ioe) {
            log.error("Failed to acquire data from Yandex API for word [{}].", word, ioe);
            throw new WordFinderException(ioe);
        } catch (InterruptedException ie) {
            log.error("Was interrupted.", ie);
            Thread.currentThread().interrupt();
            return Collections.EMPTY_LIST;
        }
    }

    private String buildUrl(String word) {
        return String.format("%s?text=%s&numdoc=%s", yandexApiUrl, word, maxUrls);
    }
}