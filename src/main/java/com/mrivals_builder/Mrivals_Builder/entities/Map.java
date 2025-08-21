package com.mrivals_builder.Mrivals_Builder.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Map {

    @Id
    private Long id;
    private String name;
    private String fullName;
    private String location;
    private String description;
    private String gameMode;
    private boolean isCompetitive;
    private String videoLink;

    @OneToMany(mappedBy = "map")
    private List<MapImage> mapImages;
}
