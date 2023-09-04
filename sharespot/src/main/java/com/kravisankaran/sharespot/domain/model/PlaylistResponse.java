package com.kravisankaran.sharespot.domain.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonIgnoreProperties(ignoreUnknown = true)
public class PlaylistResponse {
	@Override
	public String toString() {
		return "PlaylistResponse [items=" + items + "]";
	}

	public List<Playlist> getItems() {
		return items;
	}

	public void setItems(List<Playlist> items) {
		this.items = items;
	}

	@JsonProperty("items")
	List<Playlist> items;
}
