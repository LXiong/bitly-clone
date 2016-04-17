package com.bitly.service;

import org.junit.Assert;
import org.junit.Test;
import junit.framework.*;

import java.security.MessageDigest;

public class StringEncoderImplTest extends TestCase {
    private StringEncoderImpl encoder;
    
    public StringEncoderImplTest(String name) {
        super( name );
    }
    
    @Override
    protected void setUp() throws Exception {
        this.encoder = new StringEncoderImpl(); 
        
        MessageDigest md = MessageDigest.getInstance("MD5");
        encoder.setMessageDigest(md);
    }
    
    
    @Test
    public void testEncode() throws Exception {
        String hash = encoder.encode("");
        Assert.assertNotNull(hash);
        Assert.assertTrue(hash.length() > 0);
        
        hash = encoder.encode("http://1lk42jl124.124o124l12.1241.2dsalkfasd.com");
        Assert.assertNotNull(hash);
        Assert.assertTrue(hash.length() > 0);
        
        hash = encoder.encode("http://www.helloworld.com");
        Assert.assertNotNull(hash);
        Assert.assertTrue(hash.length() > 0);
    }
}