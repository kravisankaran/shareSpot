package com.kravisankaran.sharespot.domain.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class PlaylistTrackDto {
	@Id
	private String id;
	
	private String uri;
	
	private String albumName;
	
	private String name;

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

	public String getAlbumName() {
		return albumName;
	}

	public void setAlbumName(String album) {
		this.albumName = album;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	

}
