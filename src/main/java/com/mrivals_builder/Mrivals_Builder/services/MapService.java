package com.mrivals_builder.Mrivals_Builder.services;

import com.mrivals_builder.Mrivals_Builder.dtos.ExternalApiDTOs.MapExternalApiDTO;
import com.mrivals_builder.Mrivals_Builder.entities.Map;
import com.mrivals_builder.Mrivals_Builder.entities.MapImage;
import com.mrivals_builder.Mrivals_Builder.mappers.MapMapper;
import com.mrivals_builder.Mrivals_Builder.repositories.MapImageRepository;
import com.mrivals_builder.Mrivals_Builder.repositories.MapRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MapService {

    @Autowired
    private ExternalApiService extApiService;

    @Autowired
    private MapRepository mapRepository;

    @Autowired
    private MapImageRepository mapImageRepository;

    @Autowired
    private MapMapper mapMapper;

    public List<Map> getAllMapsFromApi() {
        List<MapExternalApiDTO> dtos = extApiService.getMapsFromApi();

        List<Map> maps = dtos.stream()
                .map(dto -> {
                    Map map = mapRepository.findByExternalId(dto.getId())
                            .orElseGet(Map::new);

                    Map saved = mapRepository.save(mapMapper.mapToEntity(dto, map));
                    List<MapImage> images = mapMapper.dtoToMapImage(dto, saved);
                    mapImageRepository.saveAll(images);

                    return saved;
                })
                .toList();

        return maps;
    }

    public List<Map> getAllMaps() {
        return mapRepository.findAll();
    }
}
