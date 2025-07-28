package com.rachael.api.album.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rachael.api.album.model.DiskGenre;

public interface DiskGenreRepository extends JpaRepository<DiskGenre, Integer> {}