package com.rachael.api.album.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rachael.api.album.model.DiskStatus;

public interface DiskStatusRepository extends JpaRepository<DiskStatus, Integer> {}