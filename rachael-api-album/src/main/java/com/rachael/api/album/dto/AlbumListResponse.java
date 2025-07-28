package com.rachael.api.album.dto;

import java.util.List;

import com.rachael.api.album.model.Disk;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class AlbumListResponse extends GenericResponse {
	
    private List<Disk> disks;
    
}