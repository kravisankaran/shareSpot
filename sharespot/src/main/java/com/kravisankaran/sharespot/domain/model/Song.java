package com.kravisankaran.sharespot.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Song {

	@JsonProperty("added_at")
	String addedAt;

	@JsonProperty("track")
	PlaylistTrack track;

	public String getAddedAt() {
		return addedAt;
	}

	public void setAddedAt(String addedAt) {
		this.addedAt = addedAt;
	}

	public PlaylistTrack getTrack() {
		return track;
	}

	public void setTrack(PlaylistTrack track) {
		this.track = track;
	}

	@Override
	public String toString() {
		return "Song [addedAt=" + addedAt + ", track=" + track + "]";
	}
	
}
