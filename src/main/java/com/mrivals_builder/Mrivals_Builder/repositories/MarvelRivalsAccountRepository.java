package com.mrivals_builder.Mrivals_Builder.repositories;

import com.mrivals_builder.Mrivals_Builder.entities.MarvelRivalsAccount;
import com.mrivals_builder.Mrivals_Builder.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MarvelRivalsAccountRepository extends JpaRepository<MarvelRivalsAccount, Long> {
    List<MarvelRivalsAccount> findByUser(User user);

    Optional<MarvelRivalsAccount> findByMrivalsAccount(String mrivalsAccount);
}
