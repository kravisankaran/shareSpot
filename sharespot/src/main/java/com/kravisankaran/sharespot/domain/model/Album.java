package com.kravisankaran.sharespot.domain.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Album {
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@JsonProperty("album_type")
	String albumType;
	
	@JsonProperty("name")
	String name;

	@JsonProperty("artists")
	List<Artist> artists;

	@JsonProperty("id")
	String id;

	@JsonProperty("release_date")
	String releaseDate;

	@JsonProperty("total_tracks")
	Integer totalTracks;

	public String getAlbumType() {
		return albumType;
	}

	public void setAlbumType(String albumType) {
		this.albumType = albumType;
	}

	public List<Artist> getArtists() {
		return artists;
	}

	public void setArtists(List<Artist> artists) {
		this.artists = artists;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	public Integer getTotalTracks() {
		return totalTracks;
	}

	public void setTotalTracks(Integer totalTracks) {
		this.totalTracks = totalTracks;
	}

	@Override
	public String toString() {
		return "Album [albumType=" + albumType + ", artists=" + artists + ", id=" + id + ", releaseDate=" + releaseDate
				+ ", totalTracks=" + totalTracks + "]";
	}
	
}