package com.kravisankaran.sharespot.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Playlist {
	@JsonProperty("name")
	String name;
	@JsonProperty("id")
	String id;
	@JsonProperty("uri")
	String uri;
	@JsonProperty("tracks")
	Tracks tracks;

	@Override
	public String toString() {
		return "Playlist [name=" + name + ", id=" + id + ", uri=" + uri + ", tracks=" + tracks + "]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public Tracks getTracks() {
		return tracks;
	}

	public void setTracks(Tracks tracks) {
		this.tracks = tracks;
	}
}
