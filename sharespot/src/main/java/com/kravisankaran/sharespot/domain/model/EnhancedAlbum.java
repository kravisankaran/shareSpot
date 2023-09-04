package com.kravisankaran.sharespot.domain.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EnhancedAlbum extends Album {
	@JsonProperty("label")
	String label;
	
	@JsonProperty("tracks")
	@JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
	List<Tracker> tracks;

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public List<Tracker> getTracks() {
		return tracks;
	}

	public void setTracks(List<Tracker> tracks) {
		this.tracks = tracks;
	}

	@Override
	public String toString() {
		return "EnhancedAlbum [label=" + label + ", tracks=" + tracks + ", albumType=" + albumType + ", name=" + name
				+ ", artists=" + artists + ", id=" + id + ", releaseDate=" + releaseDate + ", totalTracks="
				+ totalTracks + "]";
	}

	
}
