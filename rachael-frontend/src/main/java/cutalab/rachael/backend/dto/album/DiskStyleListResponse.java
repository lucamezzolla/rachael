package cutalab.rachael.backend.dto.album;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class DiskStyleListResponse {
    private List<DiskStyleDTO> styles;
}