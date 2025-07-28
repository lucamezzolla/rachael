package com.rachael.api.album.service;

import com.rachael.api.album.dto.DiskListRequest;
import com.rachael.api.album.dto.DiskListResponse;

public interface DiskService {

	DiskListResponse getAllDisks(DiskListRequest request);
	
}
