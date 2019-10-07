package com.my.urlshortener.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author MAHESH
 *
 */
public class URI {
 private String url;
	
	  @JsonCreator public URI(@JsonProperty("url") String url) {
	  this.url = url; }

  public String getUrl() { return url; }
  
  public void setUrl(String url) { this.url = url; } }
 
