package com.forum.exception;

public class SpringRedditException extends RuntimeException{
    public SpringRedditException(String message) {
        super(message);
    }

    public SpringRedditException(String message, Throwable cause) {
        super(message, cause);
    }
}
