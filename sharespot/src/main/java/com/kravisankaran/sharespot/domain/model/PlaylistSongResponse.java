package com.kravisankaran.sharespot.domain.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PlaylistSongResponse {
	@JsonProperty("items")
	List<Song> items;

	public List<Song> getItems() {
		return items;
	}

	public void setItems(List<Song> items) {
		this.items = items;
	}

	@Override
	public String toString() {
		return "PlaylistSongResponse [items=" + items + "]";
	}
	
}
