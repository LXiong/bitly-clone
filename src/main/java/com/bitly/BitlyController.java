package com.bitly;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bitly.model.ShortenLink;
import com.bitly.exception.ShortenLinkException;
import com.bitly.service.BitlyService;

@RestController
public class BitlyController {

    @Autowired
    private BitlyService service;

    @RequestMapping(value="/shorten", method=RequestMethod.POST)
    public ShortenLink shorten(@RequestBody final String originalUrl) throws ShortenLinkException {
        try {
            return service.shorten(originalUrl);
        } catch(Exception e) {
            throw new ShortenLinkException(e);
        }
    }

}
