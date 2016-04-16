package com.bitly.model;

public class ShortenLink {

    private final String originalUrl;
    private final String shortenUrl;

    public ShortenLink(String originalUrl, String shortenUrl) {
        this.originalUrl = originalUrl;
        this.shortenUrl = shortenUrl;
    }
    
    public String getOriginalUrl() {
        return this.originalUrl;
    }
    
    public String getShortenUrl() {
        return this.shortenUrl;
    }

}
