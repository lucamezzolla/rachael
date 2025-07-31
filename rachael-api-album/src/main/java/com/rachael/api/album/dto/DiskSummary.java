package com.rachael.api.album.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DiskSummary {
	
    private Integer id;
    private String author;
    private String title;
    private String cover;
    
}