package cutalab.rachael.backend.dto.album;

import java.math.BigDecimal;
import java.util.List;
import lombok.Data;

@Data
public class DiskRequestDTO {
	
    private String author;
    private String cover;
    private String label;
    private String note;
    private boolean openable;
    private BigDecimal presumedValue;
    private String reprint;
    private String title;
    private Long userId;
    private String year;
    private DiskStatusDTO coverStatus;
    private DiskStatusDTO diskStatus;
    private List<DiskGenreDTO> genres;
    private List<DiskStyleDTO> styles;
    
}