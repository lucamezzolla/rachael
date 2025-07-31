package com.rachael.api.album.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.rachael.api.album.dto.DiskSummary;
import com.rachael.api.album.model.Disk;

public interface DiskRepository extends JpaRepository<Disk, Integer> {
	
	@Query("SELECT new com.rachael.api.album.dto.DiskSummary(d.id, d.author, d.title, d.cover) FROM Disk d WHERE d.userId = :userId")
	List<DiskSummary> findAllByUserId(@Param("userId") long userId);

	
}