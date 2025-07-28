package com.rachael.api.album.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rachael.api.album.dto.DiskListRequest;
import com.rachael.api.album.dto.DiskListResponse;
import com.rachael.api.album.dto.DiskSummary;
import com.rachael.api.album.repository.DiskRepository;
import com.rachael.api.album.service.DiskService;

@Service
public class DiskServiceImpl implements DiskService {
	
	@Autowired
	private DiskRepository diskRepository;

	@Override
	public DiskListResponse getAllDisks(DiskListRequest request) {
	    List<DiskSummary> disks = diskRepository.findAllByUserId(request.getUserId());
	    return DiskListResponse.builder()
            .disks(disks)
            .build();
	}

}