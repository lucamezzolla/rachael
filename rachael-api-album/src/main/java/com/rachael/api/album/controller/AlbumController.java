package com.rachael.api.album.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rachael.api.album.constant.APIConstant;
import com.rachael.api.album.dto.AlbumListResponse;
import com.rachael.api.album.dto.DiskListRequest;
import com.rachael.api.album.dto.DiskListResponse;
import com.rachael.api.album.service.DiskService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping(APIConstant.PATH)
@SecurityRequirement(name = APIConstant.SECURITY_REQUIREMENT_NAME)
@Tag(name = APIConstant.ALBUMCONTOLLER_TAG_NAME, description = APIConstant.ALBUMCONTROLLER_TAG_DESCRIPTION)
public class AlbumController {
	
	
	@Autowired
	private DiskService diskService;
	
	/**
	 * Restituisce tutti gli album.
	 *
	 * @return lista di {@link AlbumListResponse}
	 */
	@Operation(summary = APIConstant.ALBUM_GET_ALL_OPERATION_SUMMARY, description = APIConstant.ALBUM_GET_ALL_OPERATION_DESCRIPTION)
	@PostMapping(APIConstant.ALBUM_GET_ALL_MAPPING)
	public ResponseEntity<DiskListResponse> getAllDisks(@RequestBody DiskListRequest request) {
		return ResponseEntity.ok(diskService.getAllDisks(request));
	}
	
}