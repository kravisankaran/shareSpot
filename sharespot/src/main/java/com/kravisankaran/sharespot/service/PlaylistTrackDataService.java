package com.kravisankaran.sharespot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kravisankaran.sharespot.domain.model.PlaylistTrack;
import com.kravisankaran.sharespot.domain.model.entity.PlaylistTrackDto;
import com.kravisankaran.sharespot.repositories.PlaylistTrackRepository;

@Service
public class PlaylistTrackDataService {
	@Autowired
	private PlaylistTrackRepository playlistTrackRepository;

	public List<PlaylistTrackDto> readPlaylistTrackDtos() {
		return playlistTrackRepository.findAll();
	}
	
	@jakarta.transaction.Transactional
	public String savePlaylistTrack(PlaylistTrack playlistTrack) {
		try {
			PlaylistTrackDto playlistTrackDto = mapToEntity(playlistTrack);
			if (!playlistTrackRepository.existsById(playlistTrackDto.getId())) {
				playlistTrackRepository.save(playlistTrackDto);
				return "Playlist Track saved successfully";
			} else {
				return "Playlist Track with same name already exists";
			}

		} catch (Exception e) {
			e.printStackTrace();
			return "Exception occurred while persisting playlist track";
		}
	}

	private PlaylistTrackDto mapToEntity(PlaylistTrack playlistTrack) {
		PlaylistTrackDto playlistTrackDto = new PlaylistTrackDto();
		playlistTrackDto.setId(playlistTrack.getId());
		playlistTrackDto.setAlbumName(playlistTrack.getAlbum().getName());
		playlistTrackDto.setName(playlistTrack.getName());
		playlistTrackDto.setUri(playlistTrack.getUri());
		return playlistTrackDto;
	}

}
