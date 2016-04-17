package com.bitly.service;

import java.net.URL;

import org.junit.Assert;
import org.junit.Test;
import junit.framework.*;

import org.jmock.Mockery;
import org.jmock.Expectations;

import com.bitly.db.ShortenDao;
import com.bitly.model.ShortenLink;
import com.bitly.model.ShortenLinkImpl;


public class BitlyServiceTest extends TestCase {
    private BitlyService service;
    private StringEncoder stringEncoder;
    private ShortenDao dao;

    
    final static String LONG_URL = "https://chrome.google.com/webstore/detail/longurl/oldnehmjgfcannmkgkojafngdkhfkdpd?hl=en";
    URL SHORT_URL;
    private Mockery context = new Mockery();
    
    public BitlyServiceTest(String name) {
        super( name );
    }
    
    @Override
    protected void setUp() throws Exception {
        SHORT_URL = new URL("https://bitly-clone.com");
        this.stringEncoder = context.mock(StringEncoder.class);
        this.dao = context.mock(ShortenDao.class);
        
        this.service = new BitlyService();
        service.setDomainUrl(SHORT_URL);
        service.setStringEncoder(stringEncoder);
        service.setShortenDao(dao);
    }
    
    
    @Test
    public void testEncodeFoundInDb() throws Exception {
        final String hash = "test1";
        final String short_url = SHORT_URL.toString() + "/" + hash;
        final ShortenLink expected = new ShortenLinkImpl(LONG_URL, short_url);
        
        context.checking(new Expectations() {{
            oneOf(dao).findByOriginalUrl(LONG_URL);
            will(returnValue(expected));
        }});
        
        ShortenLink actual = service.encode(LONG_URL);
        Assert.assertEquals(expected, actual);
        
        context.assertIsSatisfied();
    }
    
    
    @Test
    public void testEncodeNotFoundInDb() throws Exception {
        final String hash = "test2";
        
        context.checking(new Expectations() {{
            oneOf(dao).findByOriginalUrl(LONG_URL);
            will(returnValue(null));
            
            oneOf(dao).insertShortenLink(with(any(ShortenLink.class)));
            
            oneOf(stringEncoder).encode(LONG_URL);
            will(returnValue(hash));
            
        }});
        
        ShortenLink actual = service.encode(LONG_URL);
        Assert.assertNotNull(actual);
        Assert.assertEquals(SHORT_URL.toString() + "/" + hash, actual.getShortenUrl());
        Assert.assertEquals(LONG_URL, actual.getOriginalUrl());
        
        context.assertIsSatisfied();
    }
    
    
    @Test
    public void testDecode() throws Exception {
        //TODO
    }
    
    
    @Test
    public void testList() throws Exception {
        //TOOD
    }
}