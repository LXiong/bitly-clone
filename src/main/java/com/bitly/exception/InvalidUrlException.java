package com.bitly.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(value=HttpStatus.NOT_FOUND,reason="Invalid URL")
public class InvalidUrlException extends Exception {
    private static final long serialVersionUID = 100L;
    
    public InvalidUrlException(Exception e) {
        super(e);
    }
}