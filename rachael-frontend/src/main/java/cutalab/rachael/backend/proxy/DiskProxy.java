package cutalab.rachael.backend.proxy;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import cutalab.rachael.backend.dto.album.DiskGenreListResponse;
import cutalab.rachael.backend.dto.album.DiskListRequestDTO;
import cutalab.rachael.backend.dto.album.DiskListResponseDTO;
import cutalab.rachael.backend.dto.album.DiskRequestDTO;
import cutalab.rachael.backend.dto.album.DiskResponseDTO;
import cutalab.rachael.backend.dto.album.DiskStatusDTO;
import cutalab.rachael.backend.dto.album.DiskStyleListResponse;
import cutalab.rachael.config.FeignAuthInterceptor;
import cutalab.rachael.config.FeignConfig;

@FeignClient(name = "rachael-api-album", configuration = {FeignAuthInterceptor.class, FeignConfig.class})
public interface DiskProxy {

    @PostMapping("/api/disk/find")
    ResponseEntity<DiskListResponseDTO> getAllDisk(@RequestBody DiskListRequestDTO request);

    @GetMapping("/api/disk/{id}")
    ResponseEntity<DiskResponseDTO> getDiskById(@PathVariable("id") Integer id);

    @PostMapping("/api/disk")
    ResponseEntity<Void> createDisk(@RequestBody DiskRequestDTO request);

    @PutMapping("/api/disk/{id}")
    ResponseEntity<Void> updateDisk(@PathVariable("id") Integer id, @RequestBody DiskRequestDTO request);
    
    @GetMapping("/api/disk/status")
    ResponseEntity<List<DiskStatusDTO>> getAllStatuses();
    
    @GetMapping("/api/disk/genres")
    ResponseEntity<DiskGenreListResponse> getAllGenres();
    
    @GetMapping("/api/disk/styles")
    ResponseEntity<DiskStyleListResponse> getAllStyles();
    
}