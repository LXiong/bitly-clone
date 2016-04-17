package com.bitly.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND,reason="Invalid url input")
public class ShortenLinkException extends Exception {
    private static final long serialVersionUID = 100L;
    
    public ShortenLinkException(Exception e) {
        super(e);
    }
}