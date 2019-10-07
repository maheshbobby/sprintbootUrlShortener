package com.my.urlshortener.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.urlshortener.repository.URLRepository;
import com.my.urlshortener.util.Dictonary;
/**
 * 
 * @author mahesh.joshi
 *
 */
@Service
public class URLShortenerService {
    private static final Logger LOGGER = LoggerFactory.getLogger(URLShortenerService.class);

    @Autowired
    URLRepository urlRepository;

    public String processURLToSmaller(String dnsName, String toBeShorter) {
        Long id = urlRepository.incrementID();
		String uniqueID = Dictonary.generateUniqueNumber(id);
        urlRepository.saveUrl("url:"+id, toBeShorter);
        LOGGER.info("saved with key " + "url:"+id);
        return  getHostDetails(dnsName) + uniqueID;
    }

    public String getLongURLFromID(String uniqueID) throws Exception {
		Long dictionaryKey = Dictonary.getkey(uniqueID);
        LOGGER.info("Fetching with key " + "url:"+dictionaryKey);
        String longUrl = urlRepository.getUrl(dictionaryKey);
        LOGGER.info("Found long URL "+  longUrl);
        return longUrl;
    }

    private String getHostDetails(String uri) {
        String[] addressComponents = uri.split("/");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < addressComponents.length - 1; ++i) {
        	
        	sb.append(addressComponents[i]);
        }
        sb.append('/');
        return sb.toString();
    }

}
