package com.kravisankaran.sharespot.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Item {
	@JsonProperty("album")
	EnhancedAlbum album;

	public EnhancedAlbum getAlbum() {
		return album;
	}

	public void setAlbum(EnhancedAlbum album) {
		this.album = album;
	}

	@Override
	public String toString() {
		return "Item [album=" + album + "]";
	}
	
}
