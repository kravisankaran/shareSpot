package com.kravisankaran.sharespot.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PostRequest {
	@JsonProperty("uris")
	String[] uris;
	@JsonProperty("positions")
	String position;
	
	public String[] getUris() {
		return uris;
	}
	public void setUris(String[] uris) {
		this.uris = uris;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
}
