package com.rachael.api.album.service;

import java.util.List;

import com.rachael.api.album.dto.DiskListRequest;
import com.rachael.api.album.dto.DiskListResponse;
import com.rachael.api.album.dto.DiskRequest;
import com.rachael.api.album.dto.DiskResponse;
import com.rachael.api.album.model.DiskStatus;

public interface DiskService {

    DiskListResponse getAllDisks(DiskListRequest request);
    DiskResponse getDiskById(Integer id);
    void createDisk(DiskRequest request);
    void updateDisk(Integer id, DiskRequest request);
    List<DiskStatus> getAllStatuses();
    
}