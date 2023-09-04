package com.kravisankaran.sharespot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;

import com.kravisankaran.sharespot.domain.model.Playlist;
import com.kravisankaran.sharespot.domain.model.entity.PlaylistDto;
import com.kravisankaran.sharespot.repositories.PlaylistRepository;

@Service
public class PlaylistDataService {

	@Autowired
	private PlaylistRepository playlistRepository;

	@jakarta.transaction.Transactional
	public String savePlaylist(Playlist playlist) {
		try {
			PlaylistDto playlistDto = mapToEntity(playlist);
			if (!playlistRepository.existsByName(playlistDto.getName())) {
				playlistRepository.save(playlistDto);
				return "Playlist saved successfully";
			} else {
				return "Playlist with same name already exists";
			}

		} catch (Exception e) {
			e.printStackTrace();
			return "Exception occurred while persisting playlist";
		}
	}

	public List<PlaylistDto> readPlaylists() {
		return playlistRepository.findAll();
	}

	public PlaylistDto mapToEntity(Playlist playlist) {
		PlaylistDto playlistDto = new PlaylistDto();
		playlistDto.setId(playlist.getId());
		playlistDto.setName(playlist.getName());
		playlistDto.setLink(playlist.getUri());
		return playlistDto;
	}

}
