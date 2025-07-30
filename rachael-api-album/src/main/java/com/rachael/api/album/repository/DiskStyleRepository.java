package com.rachael.api.album.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rachael.api.album.dto.DiskStyleDTO;
import com.rachael.api.album.model.DiskStyle;

public interface DiskStyleRepository extends JpaRepository<DiskStyle, Integer> {
	List<DiskStyleDTO> findAllByOrderByNameAsc();
}