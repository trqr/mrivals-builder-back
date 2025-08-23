package com.mrivals_builder.Mrivals_Builder.entities;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Map {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long externalId;
    private String name;
    private String fullName;
    private String location;
    @Column(columnDefinition = "TEXT")
    private String description;
    private String gameMode;
    private boolean competitive;
    private String videoLink;

    @OneToMany(mappedBy = "map")
    @JsonManagedReference
    private List<MapImage> mapImages;
}
