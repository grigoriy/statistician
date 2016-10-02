package com.grigoriyalexeev.statistician.core;

public class UrlsExtractorException extends Exception {
    public UrlsExtractorException(String message) {
        super(message);
    }

    public UrlsExtractorException(Throwable cause) {
        super(cause);
    }

    public UrlsExtractorException(String message, Throwable cause) {
        super(message, cause);
    }
}
