package com.hellosign.sdk;

/**
 * This class wraps all hellosign-java-sdk exceptions. This allows a developer to determine where
 * the exception is coming from. It also allows us to store the HTTP code and error type from API
 * calls.
 */
public class HelloSignException extends Exception {

    private static final long serialVersionUID = -2011208957359154626L;

    private Integer httpCode;
    private String type;

    public HelloSignException(Exception e) {
        super(e);
    }

    public HelloSignException(String message) {
        super(message);
    }

    public HelloSignException(String message, Throwable e) {
        super(message, e);
    }

    public HelloSignException(String message, Integer httpCode) {
        this(message, httpCode, null, null);
    }

    public HelloSignException(String message, Integer httpCode, String type) {
        this(message, httpCode, type, null);
    }

    public HelloSignException(String message, Integer httpCode, String type, Exception e) {
        super(message, e);
        this.httpCode = httpCode;
        this.type = type;
    }

    /**
     * Returns the HTTP code associated with a HelloSign API call. This may be null if the exception
     * does not involve an API request.
     *
     * @return Integer or null if an HTTP code does not exist
     */
    public Integer getHttpCode() {
        return httpCode;
    }

    /**
     * Returns the HelloSign API error type. This may be null if the exception does not involve an
     * API request.
     *
     * @return String or null if the error type does not exist
     */
    public String getType() {
        return type;
    }
}
