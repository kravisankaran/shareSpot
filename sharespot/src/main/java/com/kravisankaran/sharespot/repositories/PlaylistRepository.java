package com.kravisankaran.sharespot.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kravisankaran.sharespot.domain.model.entity.PlaylistDto;

@Repository
public interface PlaylistRepository extends JpaRepository<PlaylistDto, String> {
	public boolean existsByName(String name);
	
	public Optional<PlaylistDto> findById(String id);

}
