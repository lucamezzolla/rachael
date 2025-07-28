package cutalab.rachael.backend.dto.service;

import java.util.List;

import cutalab.rachael.backend.dto.album.DiskListRequestDTO;
import cutalab.rachael.backend.dto.album.DiskListResponseDTO;
import cutalab.rachael.backend.dto.album.DiskRequestDTO;
import cutalab.rachael.backend.dto.album.DiskResponseDTO;
import cutalab.rachael.backend.dto.album.DiskStatusDTO;

public interface DiskService {

    DiskListResponseDTO getAllDisks(DiskListRequestDTO request);
    DiskResponseDTO getDiskById(Integer id);
    void createDisk(DiskRequestDTO request);
    void updateDisk(Integer id, DiskRequestDTO request);
    List<DiskStatusDTO> getAllStatuses();
    
}