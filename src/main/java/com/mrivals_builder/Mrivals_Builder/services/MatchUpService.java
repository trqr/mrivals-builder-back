package com.mrivals_builder.Mrivals_Builder.services;

import com.mrivals_builder.Mrivals_Builder.dtos.MatchUpDTO;
import com.mrivals_builder.Mrivals_Builder.dtos.MatchUpDTOs.MatchUpRequestDTO;
import com.mrivals_builder.Mrivals_Builder.entities.Hero;
import com.mrivals_builder.Mrivals_Builder.entities.MatchUp;
import com.mrivals_builder.Mrivals_Builder.exceptions.NotFoundException;
import com.mrivals_builder.Mrivals_Builder.mappers.MatchUpMapper;
import com.mrivals_builder.Mrivals_Builder.repositories.HeroRepository;
import com.mrivals_builder.Mrivals_Builder.repositories.MatchUpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class MatchUpService {

    @Autowired
    private MatchUpRepository matchUpRepository;
    @Autowired
    private HeroRepository heroRepository;


    public ResponseEntity<Void> deleteMatchUp(Long id) {
        if (!matchUpRepository.existsById(id)) {return ResponseEntity.notFound().build();}
        matchUpRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    public MatchUpDTO addMatchUp(MatchUpRequestDTO requestDTO) {
        Hero hero = heroRepository.findById(requestDTO.heroId())
                .orElseThrow(() -> new NotFoundException("Hero with ID " +  requestDTO.heroId() + " not found"));
        Hero counterPickhero = heroRepository.findById(requestDTO.counterPickId())
                .orElseThrow(() -> new NotFoundException("Hero with ID " +  requestDTO.counterPickId() + " not found"));

        MatchUp created = new MatchUp();
        created.setHero(hero);
        created.setCounterPick(counterPickhero);
        created.setValue(requestDTO.value());
        matchUpRepository.save(created);

        return MatchUpMapper.entityToDTO(created);
    }

    public MatchUpDTO updateMatchUp(Long id, MatchUpRequestDTO requestDTO) {
        MatchUp updated = matchUpRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("MatchUp with id " + id + " not found"));

        Hero hero = heroRepository.findById(requestDTO.heroId())
                .orElseThrow(() -> new NotFoundException("Hero with id " + requestDTO.heroId() + " not found"));

        Hero counterPickHero = heroRepository.findById(requestDTO.counterPickId())
                .orElseThrow(() -> new NotFoundException("Hero with id " + requestDTO.counterPickId() + " not found"));

        updated.setHero(hero);
        updated.setCounterPick(counterPickHero);
        updated.setValue(requestDTO.value());
        matchUpRepository.save(updated);

        return MatchUpMapper.entityToDTO(updated);
    }
}
