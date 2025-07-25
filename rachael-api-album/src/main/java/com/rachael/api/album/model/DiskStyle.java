package com.rachael.api.album.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "disks_style")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DiskStyle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
    private String name;
}
