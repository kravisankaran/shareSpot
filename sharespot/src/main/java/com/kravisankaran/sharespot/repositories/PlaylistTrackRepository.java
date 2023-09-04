package com.kravisankaran.sharespot.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kravisankaran.sharespot.domain.model.entity.PlaylistTrackDto;

@Repository
public interface PlaylistTrackRepository extends JpaRepository<PlaylistTrackDto, String> {
	public Optional<PlaylistTrackDto> findById(String id);

	public boolean existsByUri(String uri);

}
