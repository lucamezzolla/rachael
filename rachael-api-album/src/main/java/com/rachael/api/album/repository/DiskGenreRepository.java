package com.rachael.api.album.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rachael.api.album.dto.DiskGenreDTO;
import com.rachael.api.album.model.DiskGenre;

public interface DiskGenreRepository extends JpaRepository<DiskGenre, Integer> {
	List<DiskGenreDTO> findAllByOrderByNameAsc();
}