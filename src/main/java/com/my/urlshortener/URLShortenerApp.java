package com.my.urlshortener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
/**
 * 
 * @author mahesh.joshi
 *
 */
@SpringBootApplication
@ComponentScan({"com.my.urlshortener.*"})
public class URLShortenerApp {
    public static void main(final String[] args) {
        SpringApplication.run(URLShortenerApp.class, args);
    }
}
