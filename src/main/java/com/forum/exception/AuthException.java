package com.forum.exception;

public class AuthException extends RuntimeException{
    public AuthException(String message) {
        super(message);
    }
}
