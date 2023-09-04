package com.kravisankaran.sharespot.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kravisankaran.sharespot.config.AuthenticationConfig;
import com.kravisankaran.sharespot.domain.model.AlbumResponse;
import com.kravisankaran.sharespot.domain.model.Playlist;
import com.kravisankaran.sharespot.domain.model.PlaylistResponse;
import com.kravisankaran.sharespot.domain.model.PlaylistSongResponse;

public class SpotifyApiService extends AuthenticationConfig {

	private Map<String, Playlist> playlistMap;
	private Map<String, Pair<String, String>> songUriMap;

	public SpotifyApiService(String accessToken) {
		super(accessToken);
		playlistMap = new HashMap<>();
		songUriMap = new HashMap<>();
	}

	@Autowired
	private PlaylistDataService playlistDataService;

	@Autowired
	private PlaylistTrackDataService playlistTrackDataService;
	

	public Object createPlaylist(String name) {
		Map<String, String> playlist = new HashMap<String, String>();
		playlist.put("name", name);

		Object response = this.restTemplate.postForObject("https://api.spotify.com/v1/me/playlists", playlist,
				Object.class);

		return response;
	}
	
	// This will save all the playslists of a user

	public PlaylistResponse getPlaylistByUser(String userName) {
		Map<String, String> vars = new HashMap<>();
		vars.put("user_id", userName);
		ResponseEntity<String> response = this.restTemplate
				.getForEntity("https://api.spotify.com/v1/users/{user_id}/playlists", String.class, vars);
		System.out.println(response.toString());
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		PlaylistResponse playlistResponse = null;
		try {
			playlistResponse = objectMapper.readValue(response.getBody(), PlaylistResponse.class);
			extractPlaylistIDToPlaylistNameMapping(playlistResponse);
			playlistResponse.getItems().forEach(item -> {
				playlistDataService.savePlaylist(item);
			});

		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return playlistResponse;
	}

	private void extractPlaylistIDToPlaylistNameMapping(PlaylistResponse playlistResponse) {
		playlistMap = playlistResponse.getItems().stream()
				.collect(Collectors.toMap(Playlist::getId, Function.identity()));

	}
	
	// This will only save songs of a playlist that we query

	public PlaylistSongResponse getSongsByPlaylist(String playlistName, String userName) {
		System.out.println(playlistName);

		Map<String, String> vars = new HashMap<>();

		if (playlistMap.isEmpty()) {
			PlaylistResponse playlistResponse = getPlaylistByUser(userName);
			System.out.println("Printing playlist response" + playlistResponse.toString());
		}

		ResponseEntity<PlaylistSongResponse> response = null;
		PlaylistSongResponse playlistSongResponse = null;
		for (Entry<String, Playlist> entry : playlistMap.entrySet()) {
			vars.put("playlist_id", entry.getKey());
			if (playlistName.equals(entry.getValue().getName())) {
				System.out.println("Key: " + entry.getKey());
				response = this.restTemplate.getForEntity("https://api.spotify.com/v1/playlists/{playlist_id}/tracks",
						PlaylistSongResponse.class, vars);
				if (response != null) {
					playlistSongResponse = response.getBody();
					// extractSongUri(playlistSongResponse);
					playlistSongResponse.getItems().forEach(item -> {
						playlistTrackDataService.savePlaylistTrack(item.getTrack());
					});

				}

				System.out.println(playlistSongResponse);
			}

		}

		return playlistSongResponse;

	}

	public Map<String, Pair<String, String>> extractSongUri() {

		AlbumResponse albumResponse = getAvialbleSongsForCurrentUser();
		albumResponse.getItems().forEach(item -> {
			item.getAlbum().getTracks().forEach(track -> {
				track.getItems().forEach(b -> {
					if (songUriMap.isEmpty()) {
						songUriMap.put(b.getName(), Pair.of(b.getUri(), b.getId()));
					} else {
						if (songUriMap.containsKey(b.getName())) {
							Pair<String, String> pair = songUriMap.get(b.getName());
							if (!b.getId().equals(pair.getSecond())) {
								songUriMap.put(b.getName(), Pair.of(b.getUri(), b.getId()));
							}
						} else {
							songUriMap.put(b.getName(), Pair.of(b.getUri(), b.getId()));
						}
					}

				});

			});

		});
		return songUriMap;
		// songMap = playlistSongResponse.getItems().stream().map(c -> c.getTrack())
		// .collect(Collectors.toMap(PlaylistTrack::getUri, Function.identity()));
	}

	// Based on a user's saved albums we can add songs to the playlist
	public AlbumResponse getAvialbleSongsForCurrentUser() {
		ResponseEntity<AlbumResponse> response = null;
		AlbumResponse responseBody = null;
		response = this.restTemplate.getForEntity("https://api.spotify.com/v1/me/albums", AlbumResponse.class);
		if (response != null) {
			responseBody = response.getBody();
			System.out.println(responseBody);
			return responseBody;
		} else {
			return null;
		}

	}

	public Object addSongsToPlaylist(String playlistName, String[] songs, String userName) {
		//ResponseEntity<AlbumResponse> response = null;
		//AlbumResponse responseBody = null;
		Map<String, String> vars = new HashMap<>();
		// Refresh playlist incase we have new entries
		String[] uris = new String[songs.length];
		extractSongUri();
		PlaylistResponse playlistByUser = getPlaylistByUser(userName);
		int i = 0;
		for(String song: songs) {
			System.out.println("Song value: " + song);
			songUriMap.keySet().forEach(System.out::println);
			System.out.println(songUriMap.get(song).getFirst());
			uris[i] = songUriMap.get(song).getFirst();
			i++;
			
		}
		System.out.println("New playlist response: " + playlistByUser);
		for (Entry<String, Playlist> entry : playlistMap.entrySet()) {
			if (playlistName.equals(entry.getValue().getName())) {
				vars.put("playlist_id", entry.getKey());
				break;
			}
		}
		
		
		String response = restTemplate.postForObject("https://api.spotify.com/v1/playlists/{playlist_id}/tracks" , 
		        uris, String.class, vars);
		System.out.println(response);
		
		return response!= null ? response: null;


	}

}
