package com.mrivals_builder.Mrivals_Builder.dtos.ExternalApiDTOs;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MapExternalApiDTO {

    private Long id;
    private String name;
    private String fullName;
    private String location;
    private String description;
    private String gameMode;
    private boolean competitive;
    private String video;
    private List<String> images;
}
