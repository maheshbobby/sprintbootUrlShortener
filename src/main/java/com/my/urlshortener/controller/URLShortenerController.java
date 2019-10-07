package com.my.urlshortener.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.my.urlshortener.model.URI;
import com.my.urlshortener.service.URLShortenerService;
/**
 * 
 * @author MAHESH
 *
 */

@RestController
public class URLShortenerController {
    private static final Logger LOGGER = LoggerFactory.getLogger(URLShortenerController.class);
    
    @Autowired
    private URLShortenerService uRLShortenerService;
    
    @RequestMapping(value = "/uri-shorten", method=RequestMethod.POST, consumes = {"application/json"})
    public ResponseEntity<String>  convertToShortURI(Model model, @RequestBody @Valid final URI url, HttpServletRequest request) throws Exception {
    	LOGGER.info("URI Received: " + url.getUrl());
            String smallURL = uRLShortenerService.processURLToSmaller(request.getRequestURL().toString(), url.getUrl());
            LOGGER.info("url shorted to : " + smallURL);
            model.addAttribute("url", smallURL);
            return new ResponseEntity<String>(smallURL,HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method=RequestMethod.GET)
    public RedirectView findActualURL(Model model, @PathVariable String id) throws Exception {
        LOGGER.info("Getting Actual Long URL for " + id);
        RedirectView view = new RedirectView();
        view.setUrl(uRLShortenerService.getLongURLFromID(id));
        LOGGER.info("Original URL was " + view.getUrl());
        return view;
    }
    
    
}

	

