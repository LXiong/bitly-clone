package com.bitly;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.http.MediaType;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.http.HttpStatus;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import com.bitly.model.ShortenLink;
import com.bitly.exception.InvalidUrlException;
import com.bitly.service.BitlyService;

/**
 *  Controller to handle the restful http request
 */
@RestController
public class BitlyController {

    private static final Logger logger = LogManager.getLogger(BitlyController.class);
    
    @Autowired
    private BitlyService service;
    
    
    @RequestMapping(
        value=" /{hash}",
        method=RequestMethod.GET
    )
    public ModelAndView redirect(@PathVariable String hash) throws InvalidUrlException {
        try {
            final ShortenLink link = service.decode(hash);
            RedirectView red = new RedirectView(link.getOriginalUrl(), true);
            red.setStatusCode(HttpStatus.MOVED_PERMANENTLY);
            return new ModelAndView(red);
        } catch(Exception e) {
            logger.error("error occured!", e);
            throw new InvalidUrlException(e);
        }
         
    }

    @RequestMapping(
        value="/", 
        method=RequestMethod.POST
    ) 
    @ResponseBody
    public ShortenLink shorten(@RequestBody final String originalUrl) throws InvalidUrlException { 
        try {
            return service.encode(originalUrl);
        } catch(Exception e) {
            logger.error("error occured!", e);
            throw new InvalidUrlException(e);
        }
    }
    
    

}
