package cutalab.rachael.backend.proxy;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import cutalab.rachael.backend.dto.album.DiskListRequest;
import cutalab.rachael.backend.dto.album.DiskListResponse;
import cutalab.rachael.config.FeignAuthInterceptor;
import cutalab.rachael.config.FeignConfig;

@FeignClient(name = "rachael-api-album", configuration = {FeignAuthInterceptor.class, FeignConfig.class})
public interface DiskProxy {


    @PostMapping("/api/disk/find")
    ResponseEntity<DiskListResponse> getAllDisk(@RequestBody DiskListRequest request);

}