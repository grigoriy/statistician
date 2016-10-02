package com.grigoriyalexeev.statistician.core;

public class WordFinderException extends Exception {
    public WordFinderException(String message) {
        super(message);
    }

    public WordFinderException(Throwable cause) {
        super(cause);
    }

    public WordFinderException(String message, Throwable cause) {
        super(message, cause);
    }
}
