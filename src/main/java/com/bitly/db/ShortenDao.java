package com.bitly.db;

import com.bitly.model.ShortenLink;
import java.util.List;

public interface ShortenDao {
    ShortenLink findByShortenUrl(String shortenUrl);
    ShortenLink findByOriginalUrl(String originalUrl);
    void insertShortenLink(ShortenLink link);
    List<ShortenLink> getAllShortenLinks();
}