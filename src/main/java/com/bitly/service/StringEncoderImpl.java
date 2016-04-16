package com.bitly.service;

import java.security.MessageDigest;

public class StringEncoderImpl implements StringEncoder {
   private MessageDigest messageDigest;
    
   @Override
   public String encode(String data) {
        messageDigest.update(data.getBytes());
        
        byte[] digest=messageDigest.digest();
        StringBuffer sb = new StringBuffer();
        
        for (byte b : digest) {
            sb.append(Integer.toHexString((int) (b & 0xff)));
        }
        return sb.toString();
   }
   
   public void setMessageDigest(MessageDigest messageDigest) {
       this.messageDigest = messageDigest;
   }
   
}
