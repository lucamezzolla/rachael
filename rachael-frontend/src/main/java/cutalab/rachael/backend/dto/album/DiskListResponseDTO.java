package cutalab.rachael.backend.dto.album;

import java.util.List;
import lombok.Data;

@Data
public class DiskListResponseDTO {
    private List<DiskSummaryDTO> disks;
}