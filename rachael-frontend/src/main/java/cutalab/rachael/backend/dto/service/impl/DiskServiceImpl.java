package cutalab.rachael.backend.dto.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cutalab.rachael.backend.dto.album.*;
import cutalab.rachael.backend.dto.service.DiskService;
import cutalab.rachael.backend.proxy.DiskProxy;

@Service
public class DiskServiceImpl implements DiskService {

    @Autowired
    private DiskProxy diskProxy;

    @Override
    public DiskListResponseDTO getAllDisks(DiskListRequestDTO request) {
        return diskProxy.getAllDisk(request).getBody();
    }

    @Override
    public DiskResponseDTO getDiskById(Integer id) {
        return diskProxy.getDiskById(id).getBody();
    }

    @Override
    public void createDisk(DiskRequestDTO request) {
        diskProxy.createDisk(request);
    }

    @Override
    public void updateDisk(Integer id, DiskRequestDTO request) {
        diskProxy.updateDisk(id, request);
    }

    @Override
    public List<DiskStatusDTO> getAllStatuses() {
        return diskProxy.getAllStatuses().getBody();
    }

    
}