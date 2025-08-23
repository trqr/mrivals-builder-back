package com.mrivals_builder.Mrivals_Builder.repositories;

import com.mrivals_builder.Mrivals_Builder.entities.Map;
import com.mrivals_builder.Mrivals_Builder.entities.MapImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MapImageRepository extends JpaRepository<MapImage, Long> {
    Optional<MapImage> findByImageLinkAndMap(String imageLink, Map map);

}
