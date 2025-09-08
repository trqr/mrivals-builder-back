package com.mrivals_builder.Mrivals_Builder.dtos.ExternalApiDTOs.MapDTOs;

import java.util.List;

public record MapExternalApiDTO(
        Long id,
        String name,
        String fullName,
        String location,
        String description,
        String gameMode,
        boolean competitive,
        String video,
        List<String> images
) {}
