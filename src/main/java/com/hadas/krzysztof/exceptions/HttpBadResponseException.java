package com.hadas.krzysztof.exceptions;

public class HttpBadResponseException extends RuntimeException {

    public HttpBadResponseException() {
    }

    public HttpBadResponseException(String message) {
        super(message);
    }
}
