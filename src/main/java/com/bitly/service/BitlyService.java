package com.bitly.service;

import java.util.List;
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
    public ShortenLink encode(String originalUrl) throws MalformedURLException{
        logger.info("encode url: {}", originalUrl);
        originalUrl = new URL(originalUrl).toString();
        
        //check if the original url exists in the database
        logger.debug("find {} in database", originalUrl);
        ShortenLink link = dao.findByOriginalUrl(originalUrl);
        if (link != null) return link;
        
        //if not, create a new hash
        logger.debug("cannot find {} in database", originalUrl);
        String hash = stringEncoder.encode(originalUrl);
        final URL shortenUrl = concatUrl(domainUrl, hash);
        link = new ShortenLinkImpl(originalUrl, shortenUrl.toString());
        
        //put the shorten url to the database
        logger.debug("insert {} -> {} into database", shortenUrl.toString(), originalUrl);
        dao.insertShortenLink(link);
        
        //output the result
        return link;
    }
    
    
    public ShortenLink decode(String hash) throws MalformedURLException {
        logger.info("decode hash: {}", hash);
        final URL shortenUrl = concatUrl(domainUrl, hash);
        
        ShortenLink link = dao.findByShortenUrl(shortenUrl.toString());
        logger.debug("find url: {}", link.getOriginalUrl());
        return link;
    }
    
    
    public List<ShortenLink> list() {
       logger.info("get all urls");
       return dao.getAllShortenLinks(); 
    }
    
    private static URL concatUrl(URL domainUrl, String sub) throws MalformedURLException {
        return new URL(domainUrl.toString() + "/" + sub);
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
