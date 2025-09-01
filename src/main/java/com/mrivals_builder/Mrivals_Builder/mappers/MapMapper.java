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

        map.setExternalId(dto.id());
        map.setName(dto.name());
        map.setFullName(dto.fullName());
        map.setLocation(dto.location());
        map.setDescription(dto.description());
        map.setGameMode(dto.gameMode());
        map.setCompetitive(dto.competitive());
        map.setVideoLink(dto.video());

        return map;
    }

    public List<MapImage> dtoToMapImage(MapExternalApiDTO dto, Map map) {
        List<MapImage> images = dto.images().stream()
                .map(imgUrl -> {
                    MapImage mapImage = mapImageRepository.findByImageLinkAndMap(imgUrl, map)
                            .orElseGet(MapImage::new);

                    mapImage.setImageLink(imgUrl);
                    mapImage.setMap(map);
                    return mapImage;
                })
                .toList();

        map.setMapImages(images);
        return images;
    }
}
