package com.bitly.model;

public class ShortenLinkImpl implements ShortenLink {

    private final String originalUrl;
    private final String shortenUrl;

    public ShortenLinkImpl(ShortenLink link) {
        this.originalUrl = link.getOriginalUrl();
        this.shortenUrl = link.getShortenUrl();
    }
        
    public ShortenLinkImpl(String originalUrl, String shortenUrl) {
        this.originalUrl = originalUrl;
        this.shortenUrl = shortenUrl;
    }
    
    @Override
    public String getOriginalUrl() {
        return this.originalUrl;
    }
    
    @Override
    public String getShortenUrl() {
        return this.shortenUrl;
    }

}
