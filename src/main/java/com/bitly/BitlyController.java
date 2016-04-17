package com.bitly;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.http.MediaType;

import com.bitly.model.ShortenLink;
import com.bitly.exception.ShortenLinkException;
import com.bitly.service.BitlyService;

/**
 *  Controller to handle the restful http request
 */
@RestController
public class BitlyController {

    private static final Logger logger = LogManager.getLogger(BitlyController.class.getName());
    
    @Autowired
    private BitlyService service;

    @RequestMapping(
        value="/shorten", 
        method=RequestMethod.POST
    ) 
    @ResponseBody
    public ShortenLink shorten(@RequestBody final String originalUrl) throws ShortenLinkException { try {
            return service.shorten(originalUrl);
        } catch(Exception e) {
            logger.error("error occured!", e);
            throw new ShortenLinkException(e);
        }
    }

}
