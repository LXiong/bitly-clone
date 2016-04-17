package com.bitly.service;

import java.net.URL;
import java.net.MalformedURLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.bitly.model.ShortenLink;

/**
 * Main business logic for the URL shorten service
 */
public class BitlyService {
    private static final Logger logger = LogManager.getLogger(BitlyService.class.getName());

    private URL domainUrl;
    private StringEncoder stringEncoder;
    
    /**
     * Return an ShortenLink object containing the original url and the shorten one.
     * The originalUrl argument must be in formal url format or exception will be thrown.
     * 
     * A mapping between the originalUrl and the shorten one will be set in the database
     *
     * @param   originalUrl an absolue Http Url to be shorten
     * @return              The shorten url together with the original one for reference
     */
    public ShortenLink shorten(String originalUrl) throws MalformedURLException{
        logger.debug(originalUrl);
        originalUrl = new URL(originalUrl).toString();
        
        //check if the original url exists in the database
        
        
        //if not, create a new hash
        String hash = stringEncoder.encode(originalUrl);
        
        //put the shorten url to the database
        
        
        //output the result
        URL shortenUrl = new URL(domainUrl, hash);
        return new ShortenLink(originalUrl, shortenUrl.toString());
    }
    
    //--------------------------------------------------
    //setters
    
    public void setStringEncoder(StringEncoder stringEncoder) {
        this.stringEncoder = stringEncoder;
    }
    
    public void setDomainUrl(URL domainUrl) {
        this.domainUrl = domainUrl;
    }
}
