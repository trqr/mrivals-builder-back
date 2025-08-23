package com.mrivals_builder.Mrivals_Builder.mappers;


import com.mrivals_builder.Mrivals_Builder.dtos.ExternalApiDTOs.MapExternalApiDTO;
import com.mrivals_builder.Mrivals_Builder.entities.Map;
import com.mrivals_builder.Mrivals_Builder.entities.MapImage;
import com.mrivals_builder.Mrivals_Builder.repositories.MapImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MapMapper {

    @Autowired
    private MapImageRepository mapImageRepository;

    public Map mapToEntity(MapExternalApiDTO dto, Map map) {

        map.setExternalId(dto.getId());
        map.setName(dto.getName());
        map.setFullName(dto.getFullName());
        map.setLocation(dto.getLocation());
        map.setDescription(dto.getDescription());
        map.setGameMode(dto.getGameMode());
        map.setCompetitive(dto.isCompetitive());
        map.setVideoLink(dto.getVideo());

        List<MapImage> images = dto.getImages().stream()
                .map(imgUrl -> {
                    MapImage mapImage = mapImageRepository.findByImageLinkAndMap(imgUrl, map)
                            .orElseGet(MapImage::new);

                    mapImage.setImageLink(imgUrl);
                    mapImage.setMap(map);
                    return mapImage;
                })
                .toList();

        map.setMapImages(images);

        return map;
    }
}
