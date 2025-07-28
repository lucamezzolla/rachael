package com.rachael.api.album.dto;

import java.math.BigDecimal;
import java.util.List;

import com.rachael.api.album.model.DiskGenre;
import com.rachael.api.album.model.DiskStatus;
import com.rachael.api.album.model.DiskStyle;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DiskRequest {

    private String author;
    private String cover;
    private String label;
    private String note;
    private boolean openable;
    private BigDecimal presumedValue;
    private String reprint;
    private String title;
    private Integer userId;
    private String year;
    private DiskStatus coverStatus;
    private DiskStatus diskStatus;
    private List<DiskGenre> genres;
    private List<DiskStyle> styles;
    
}