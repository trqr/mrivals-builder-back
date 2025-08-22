package com.mrivals_builder.Mrivals_Builder.repositories;

import com.mrivals_builder.Mrivals_Builder.entities.Ability;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AbilityRepository extends JpaRepository<Ability, Long> {
    Optional<Ability> findByExternalId(Long externalId);

}
