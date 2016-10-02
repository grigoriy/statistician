package com.grigoriyalexeev.statistician.core;

import java.io.InputStream;
import java.util.List;

public interface UrlsExtractor {
    List<String> extract(InputStream data) throws UrlsExtractorException;
}
