package com.grigoriyalexeev.statistician.core.impl;

import com.grigoriyalexeev.statistician.core.UrlsExtractor;
import com.grigoriyalexeev.statistician.core.UrlsExtractorException;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

class YandexRssUrlsExtractor implements UrlsExtractor {
    public List<String> extract(InputStream rss) throws UrlsExtractorException {
        SyndFeedInput input = new SyndFeedInput();
        SyndFeed feed;
        try {
            feed = input.build(new XmlReader(rss));
        } catch (FeedException e) {
            throw new UrlsExtractorException("Failed to parse the RSS feed.", e);
        } catch (IOException e) {
            throw new UrlsExtractorException("Failed to read the RSS feed.", e);
        }
        return feed
                .getEntries().stream()
                .map(SyndEntry::getLink)
                .collect(Collectors.toList());
    }
}
