package com.mrivals_builder.Mrivals_Builder.repositories;

import com.mrivals_builder.Mrivals_Builder.entities.Ability;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AbilityRepository extends JpaRepository<Ability, Long> {
    List<Ability> findAllByExternalId(Long externalId);
}
