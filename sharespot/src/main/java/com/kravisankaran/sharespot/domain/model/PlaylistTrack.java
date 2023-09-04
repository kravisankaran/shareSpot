package com.kravisankaran.sharespot.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PlaylistTrack {
	@JsonProperty("album")
	Album album;
	@JsonProperty("name")
	String name;
	@JsonProperty("popularity")
	Integer popularity;
	@JsonProperty("uri")
	String uri;

	@JsonProperty("id")
	String id;

	public String getUri() {
		return uri;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public Album getAlbum() {
		return album;
	}

	public void setAlbum(Album album) {
		this.album = album;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPopularity() {
		return popularity;
	}

	public void setPopularity(Integer popularity) {
		this.popularity = popularity;
	}

	@Override
	public String toString() {
		return "PlaylistTrack [album=" + album + ", name=" + name + ", popularity=" + popularity + "]";
	}

}
