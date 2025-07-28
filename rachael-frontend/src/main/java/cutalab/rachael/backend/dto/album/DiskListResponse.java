package cutalab.rachael.backend.dto.album;

import java.util.List;

import lombok.Data;

@Data
public class DiskListResponse {
	
    private List<DiskSummary> disks;
    
}