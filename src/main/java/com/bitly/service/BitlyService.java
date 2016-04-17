package com.bitly.service;

import java.net.URL;
import com.bitly.model.ShortenLink;
import java.net.MalformedURLException;

public class BitlyService {
    private URL domainUrl;
    private StringEncoder stringEncoder;
    
    public ShortenLink shorten(String originalUrl) throws MalformedURLException{
        originalUrl = new URL(originalUrl).toString();
        
        //check if the original url exists in the database
        
        
        //if not, create a new hash
        String hash = stringEncoder.encode(originalUrl);
        
        //put the shorten url to the database
        
        
        //output the result
        URL shortenUrl = new URL(domainUrl, hash);
        return new ShortenLink(originalUrl, shortenUrl.toString());
    }
    
    
    public void setStringEncoder(StringEncoder stringEncoder) {
        this.stringEncoder = stringEncoder;
    }
    
    public void setDomainUrl(URL domainUrl) {
        this.domainUrl = domainUrl;
    }
}
