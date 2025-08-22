package com.mrivals_builder.Mrivals_Builder.repositories;

import com.mrivals_builder.Mrivals_Builder.entities.Hero;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HeroRepository extends JpaRepository<Hero, Long> {
    Optional<Hero> findByExternalId(Long externalId);

}
