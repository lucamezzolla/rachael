package com.rachael.api.album.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "disks")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Disk {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String author;
    private String cover;
    private String label;

    @Lob
    private String note;

    private boolean openable;

    @Column(name = "presumed_value", precision = 10, scale = 2)
    private BigDecimal presumedValue;

    private String reprint;
    private String title;

    @Column(name = "user_id")
    private Integer userId;

    private String year;

    @ManyToOne
    @JoinColumn(name = "cover_status")
    private DiskStatus coverStatus;

    @ManyToOne
    @JoinColumn(name = "disk_status")
    private DiskStatus diskStatus;

    @ManyToMany
    @JoinTable(
        name = "disk_genre_list",
        joinColumns = @JoinColumn(name = "disk_id"),
        inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private List<DiskGenre> genres;

    @ManyToMany
    @JoinTable(
        name = "disk_style_list",
        joinColumns = @JoinColumn(name = "disk_id"),
        inverseJoinColumns = @JoinColumn(name = "style_id")
    )
    private List<DiskStyle> styles;
}
