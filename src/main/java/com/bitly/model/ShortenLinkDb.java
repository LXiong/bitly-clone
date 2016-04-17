package com.bitly.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "SHORTEN")
public class ShortenLinkDb implements ShortenLink {
    
    @Id
    @Column(name = "ORIGINAL_URL")
    private String originalUrl;
    
    @Column(name = "SHORTEN_URL")
    private String shortenUrl;
    
    @Column(name = "CREATE_TIME")
    private Date date;
    
    public ShortenLinkDb() {
    }
    
    public ShortenLinkDb(ShortenLink link) {
        this.originalUrl = link.getOriginalUrl();
        this.shortenUrl = link.getShortenUrl();
        this.date = new Date();
    }
    
    @Override
    public String getOriginalUrl() {
        return this.originalUrl;
    }
    
    @Override
    public String getShortenUrl() {
        return this.shortenUrl;
    }
    
    public Date getDate() {
        return this.date;
    }
    
    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }
    
    public void setShortenUrl(String shortenUrl) {
        this.shortenUrl = shortenUrl;
    }
    
    public void setDate(Date date) {
        this.date = date;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (null == obj) return false;
        if (false == obj instanceof ShortenLinkDb) return false;
        
        final ShortenLinkDb link = (ShortenLinkDb) obj;
        if (false == link.originalUrl.equals(this.originalUrl)) return false;
        if (false == link.shortenUrl.equals(this.shortenUrl)) return false;
        if (false == link.date.equals(this.date)) return false;
        return true;
    }
}