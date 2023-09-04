package com.kravisankaran.sharespot.domain.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Tracker {
	@JsonProperty("items")
	List<AlbumTrack> items;

	public List<AlbumTrack> getItems() {
		return items;
	}

	public void setItems(List<AlbumTrack> items) {
		this.items = items;
	}

	@Override
	public String toString() {
		return "Tracker [items=" + items + "]";
	}
}
