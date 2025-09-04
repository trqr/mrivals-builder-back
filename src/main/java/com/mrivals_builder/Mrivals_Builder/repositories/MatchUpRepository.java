package com.mrivals_builder.Mrivals_Builder.repositories;

import com.mrivals_builder.Mrivals_Builder.entities.Hero;
import com.mrivals_builder.Mrivals_Builder.entities.MatchUp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MatchUpRepository extends JpaRepository<MatchUp, Long> {
    List<MatchUp> findByHero(Hero hero);

}
