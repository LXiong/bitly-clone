package com.bitly.service;

import java.net.URL;
import java.net.MalformedURLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.bitly.model.ShortenLink;
import com.bitly.model.ShortenLinkImpl;
import com.bitly.db.ShortenDao;

/**
 * Main business logic for the URL shorten service
 */
public class BitlyService {
    private static final Logger logger = LogManager.getLogger(BitlyService.class);

    private URL domainUrl;
    private StringEncoder stringEncoder;
    private ShortenDao dao;
    
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
        logger.info("shorten url: {}", originalUrl);
        originalUrl = new URL(originalUrl).toString();
        
        //check if the original url exists in the database
        logger.debug("find {} in database", originalUrl);
        ShortenLink link = dao.findByOriginalUrl(originalUrl);
        if (link != null) return link;
        
        //if not, create a new hash
        logger.debug("cannot find {} in database", originalUrl);
        String hash = stringEncoder.encode(originalUrl);
        URL shortenUrl = new URL(domainUrl, hash);
        link = new ShortenLinkImpl(originalUrl, shortenUrl.toString());
        
        //put the shorten url to the database
        logger.debug("insert {} -> {} into database", shortenUrl.toString(), originalUrl);
        dao.insertShortenLink(link);
        
        //output the result
        return link;
    }
    
    //--------------------------------------------------
    //setters
    
    public void setStringEncoder(StringEncoder stringEncoder) {
        this.stringEncoder = stringEncoder;
    }
    
    public void setDomainUrl(URL domainUrl) {
        this.domainUrl = domainUrl;
    }
    
    public void setShortenDao(ShortenDao dao) {
        this.dao = dao;
    }
}
