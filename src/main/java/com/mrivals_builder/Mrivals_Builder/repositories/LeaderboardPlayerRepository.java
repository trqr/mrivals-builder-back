package com.mrivals_builder.Mrivals_Builder.repositories;

import com.mrivals_builder.Mrivals_Builder.entities.Hero;
import com.mrivals_builder.Mrivals_Builder.entities.LeaderboardPlayer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeaderboardPlayerRepository extends JpaRepository<LeaderboardPlayer, Long> {
    Page<LeaderboardPlayer> findByHero(Hero hero, Pageable pageable);
}
