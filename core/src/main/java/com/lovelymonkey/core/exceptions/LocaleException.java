package com.lovelymonkey.core.exceptions;

/**
 * Locale exception used for international handling logic.
 */
@SuppressWarnings("serial")
public class LocaleException extends Exception {

    /**
     * {@inheritDoc}
     */
    public LocaleException(String message) {
        super(message);
    }

    /**
     * {@inheritDoc}
     */
    public LocaleException(Throwable t) {
        super(t);
    }

    /**
     * {@inheritDoc}
     */
    public LocaleException(String message, Throwable t) {
        super(message, t);
    }
}
