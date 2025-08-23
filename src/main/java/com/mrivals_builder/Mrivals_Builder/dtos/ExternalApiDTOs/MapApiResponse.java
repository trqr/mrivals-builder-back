package com.mrivals_builder.Mrivals_Builder.dtos.ExternalApiDTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MapApiResponse {

    private List<MapExternalApiDTO> maps;

}
