package com.kravisankaran.sharespot.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kravisankaran.sharespot.domain.model.AlbumResponse;
import com.kravisankaran.sharespot.domain.model.PlaylistResponse;
import com.kravisankaran.sharespot.domain.model.PlaylistSongResponse;
import com.kravisankaran.sharespot.service.SpotifyApiService;

@RestController
public class SpotifyApiController {

	@Autowired
	private SpotifyApiService apiService;

	// Endpoint to create a playlist
	@GetMapping(value = "/sharespot/user/playlist/create", produces = "application/json")
	public ResponseEntity<Object> createPlaylist(@RequestParam("name") String name) {
		return ResponseEntity.ok(apiService.createPlaylist(name));
	}

	// Endpoint to get playlist by user
	@GetMapping(value = "/sharespot/user/playlists", produces = "application/json")
	public PlaylistResponse getPlaylistByUser(@RequestParam("userName") String userName) {
		return apiService.getPlaylistByUser(userName);
	}
	
	// Endpoint to get songs from a user's playlist

	@GetMapping(value = "/sharespot/user/songs", produces = "application/json")
	public PlaylistSongResponse getSongsByPlaylist(@RequestParam("playlistName") String playlistName,
			@RequestParam("userName") String userName) {
		return apiService.getSongsByPlaylist(playlistName, userName);
	}
	
	// Endpoint to get all the songs available in saved albums of current user
	@GetMapping(value = "/sharespot/user/albums/saved", produces = "application/json")
	public AlbumResponse getUserSavedAlbums() {
		return apiService.getAvialbleSongsForCurrentUser();
	}
	
	// Endpoint to get all song uri mappings available songs in saved albums of current user
	@GetMapping(value = "/sharespot/user/albums/saved/songs/uri", produces = "application/json")
	public Map<String, Pair<String,String>> getSavedSongUri() {
		return apiService.extractSongUri();
	}

	// Endpoint to add songs to a playlist
	@GetMapping(value = "/sharespot/user/playlist/add", produces = "application/json")
	public ResponseEntity<Object> addSongs(@RequestParam("playlistName") String playlistName, @RequestParam("songs") String[] songs, @RequestParam("userName") String userName) {
		return ResponseEntity.ok(apiService.addSongsToPlaylist(playlistName, songs, userName));
	}
	
}
