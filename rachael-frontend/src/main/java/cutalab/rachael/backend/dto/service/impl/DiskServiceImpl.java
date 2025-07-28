package cutalab.rachael.backend.dto.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cutalab.rachael.backend.dto.album.DiskListRequest;
import cutalab.rachael.backend.dto.album.DiskListResponse;
import cutalab.rachael.backend.dto.service.DiskService;
import cutalab.rachael.backend.proxy.DiskProxy;



@Service
public class DiskServiceImpl implements DiskService {
	
	@Autowired
	private DiskProxy diskProxy;

	@Override
	public DiskListResponse getAllDisks(DiskListRequest request) {
		return diskProxy.getAllDisk(request).getBody();
	}

}