package com.mrivals_builder.Mrivals_Builder.services;

import com.mrivals_builder.Mrivals_Builder.dtos.SynergieDTO;
import com.mrivals_builder.Mrivals_Builder.dtos.SynergieDTOs.SynergieRequestDTO;
import com.mrivals_builder.Mrivals_Builder.entities.Hero;
import com.mrivals_builder.Mrivals_Builder.entities.Synergie;
import com.mrivals_builder.Mrivals_Builder.exceptions.NotFoundException;
import com.mrivals_builder.Mrivals_Builder.mappers.SynergieMapper;
import com.mrivals_builder.Mrivals_Builder.repositories.HeroRepository;
import com.mrivals_builder.Mrivals_Builder.repositories.SynergieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SynergieService {

    @Autowired
    private SynergieRepository synergieRepository;

    @Autowired
    private HeroRepository heroRepository;

    @Autowired
    private SynergieMapper synergieMapper;

    public SynergieDTO addSynergie(SynergieRequestDTO requestDTO){
        Hero hero = heroRepository.findById(requestDTO.heroId())
                .orElseThrow(() -> new NotFoundException("Hero with id " + requestDTO.heroId() + " not found"));

        Hero allyHero = heroRepository.findById(requestDTO.allyId())
                .orElseThrow(() -> new NotFoundException("Hero with id " + requestDTO.allyId() + " not found"));

        Synergie created = synergieMapper.RequestDTOtoEntity(requestDTO, hero, allyHero);

        synergieRepository.save(created);

        return SynergieMapper.entityToDTO(created);
    }

    public SynergieDTO updateSynergie(Long id, SynergieRequestDTO requestDTO){
        Synergie updated = synergieRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Synergie with id " + id + " not found"));

        Hero hero = heroRepository.findById(requestDTO.heroId())
                .orElseThrow(() -> new NotFoundException("Hero with id " + requestDTO.heroId() + " not found"));

        Hero allyHero = heroRepository.findById(requestDTO.allyId())
                .orElseThrow(() -> new NotFoundException("Hero with id " + requestDTO.allyId() + " not found"));


        updated.setHero(hero);
        updated.setAlly(allyHero);
        updated.setValue(requestDTO.value());
        updated.setTeamUp(requestDTO.isTeamup());

        synergieRepository.save(updated);

        return SynergieMapper.entityToDTO(updated);
    }
}
