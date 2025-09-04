package com.mrivals_builder.Mrivals_Builder.repositories;

import com.mrivals_builder.Mrivals_Builder.entities.Hero;
import com.mrivals_builder.Mrivals_Builder.entities.Synergie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SynergieRepository extends JpaRepository<Synergie, Long> {
    List<Synergie> findByHero(Hero hero);
}
