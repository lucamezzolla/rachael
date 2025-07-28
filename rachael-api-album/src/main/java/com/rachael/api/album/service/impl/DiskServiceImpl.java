package com.rachael.api.album.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springdoc.api.ErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rachael.api.album.constant.ErrorMessages;
import com.rachael.api.album.dto.DiskListRequest;
import com.rachael.api.album.dto.DiskListResponse;
import com.rachael.api.album.dto.DiskRequest;
import com.rachael.api.album.dto.DiskResponse;
import com.rachael.api.album.dto.DiskSummary;
import com.rachael.api.album.model.Disk;
import com.rachael.api.album.model.DiskStatus;
import com.rachael.api.album.repository.DiskRepository;
import com.rachael.api.album.repository.DiskStatusRepository;
import com.rachael.api.album.service.DiskService;

@Service
public class DiskServiceImpl implements DiskService {
	
    @Autowired
    private DiskRepository diskRepository;
    
    @Autowired
    private DiskStatusRepository diskStatusRepository;

    @Override
    public DiskListResponse getAllDisks(DiskListRequest request) {
        List<DiskSummary> disks = diskRepository.findAllByUserId(request.getUserId());
        return DiskListResponse.builder()
            .disks(disks)
            .build();
    }

    @Override
    public DiskResponse getDiskById(Integer id) {
        Disk disk = diskRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException(ErrorMessages.ALBUM_NOT_FOUND + id));

        return DiskResponse.builder()
            .id(disk.getId())
            .title(disk.getTitle())
            .author(disk.getAuthor())
            .label(disk.getLabel())
            .year(disk.getYear())
            .reprint(disk.getReprint())
            .note(disk.getNote())
            .cover(disk.getCover())
            .openable(disk.isOpenable())
            .presumedValue(disk.getPresumedValue())
            .diskStatus(disk.getDiskStatus())
            .coverStatus(disk.getCoverStatus())
            .genres(disk.getGenres())
            .styles(disk.getStyles())
            .userId(disk.getUserId())
            .build();
    }

    @Override
    public void createDisk(DiskRequest request) {
    	
    	DiskStatus diskStatus = diskStatusRepository.findById(request.getDiskStatus().getId())
		    .orElseThrow(() -> new IllegalArgumentException(ErrorMessages.INVALID_ALBUM_STATUS));

		DiskStatus coverStatus = diskStatusRepository.findById(request.getCoverStatus().getId())
		    .orElseThrow(() -> new IllegalArgumentException(ErrorMessages.INVALID_ALBUM_COVER_STATUS));
		
        Disk disk = Disk.builder()
            .title(request.getTitle())
            .author(request.getAuthor())
            .label(request.getLabel())
            .year(request.getYear())
            .reprint(request.getReprint())
            .note(request.getNote())
            .cover(request.getCover())
            .openable(request.isOpenable())
            .presumedValue(request.getPresumedValue())
            .diskStatus(diskStatus)
            .coverStatus(coverStatus)
            .genres(request.getGenres())
            .styles(request.getStyles())
            .userId(request.getUserId())
            .build();

        diskRepository.save(disk);
    }

    @Override
    public void updateDisk(Integer id, DiskRequest request) {
    	
    	DiskStatus diskStatus = diskStatusRepository.findById(request.getDiskStatus().getId())
    			.orElseThrow(() -> new IllegalArgumentException(ErrorMessages.INVALID_ALBUM_STATUS));

		DiskStatus coverStatus = diskStatusRepository.findById(request.getCoverStatus().getId())
    		    .orElseThrow(() -> new IllegalArgumentException(ErrorMessages.INVALID_ALBUM_COVER_STATUS));
        
		Disk existing = diskRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException(ErrorMessages.ALBUM_NOT_FOUND + id));

        existing.setTitle(request.getTitle());
        existing.setAuthor(request.getAuthor());
        existing.setLabel(request.getLabel());
        existing.setYear(request.getYear());
        existing.setReprint(request.getReprint());
        existing.setNote(request.getNote());
        existing.setCover(request.getCover());
        existing.setOpenable(request.isOpenable());
        existing.setPresumedValue(request.getPresumedValue());
        existing.setDiskStatus(diskStatus);
        existing.setCoverStatus(coverStatus);
        existing.setGenres(request.getGenres());
        existing.setStyles(request.getStyles());
        existing.setUserId(request.getUserId());

        diskRepository.save(existing);
    }
    
    @Override
    public List<DiskStatus> getAllStatuses() {
        return diskStatusRepository.findAll()
           .stream()
           .map(status -> DiskStatus.builder()
               .id(status.getId())
               .name(status.getName())
               .build())
           .collect(Collectors.toList());
    }

}