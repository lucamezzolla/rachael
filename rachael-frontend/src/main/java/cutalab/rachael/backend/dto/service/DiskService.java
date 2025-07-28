package cutalab.rachael.backend.dto.service;

import cutalab.rachael.backend.dto.album.DiskListRequest;
import cutalab.rachael.backend.dto.album.DiskListResponse;

public interface DiskService {

	DiskListResponse getAllDisks(DiskListRequest request);
	
}
