package com.my.urlshortener.service;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.my.urlshortener.repository.URLRepository;
import com.my.urlshortener.util.Dictonary;
/**
 * 
 * @author mahesh.joshi
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class URLShortenerServiceTest {
    @Mock
    URLRepository urlRepository;
    
    @InjectMocks
    URLShortenerService uRLShortenerService;
    
    @Before
    public void setUp(){
    	uRLShortenerService.processURLToSmaller("local", "google.com");
    	urlRepository.saveUrl("1", "google.com");
    }
    @Test
    public void processURLToSmallerTest() {
    	Mockito.when(urlRepository.incrementID()).thenReturn(1L);
    	String s = uRLShortenerService.processURLToSmaller("local", "");
    	assertEquals(s, "/b");
    }

    @Test
    public void getLongURLFromIDTest() throws Exception {
    	Dictonary.mapValuesToChars();
    	Dictonary.populateListIndexTable();
    	String s = uRLShortenerService.getLongURLFromID("1L");
    	assertEquals(s, null);
    }
}
