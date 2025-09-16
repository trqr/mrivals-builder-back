package com.mrivals_builder.Mrivals_Builder.services;

import com.mrivals_builder.Mrivals_Builder.dtos.MarvelRivalsAccountDTO;
import com.mrivals_builder.Mrivals_Builder.entities.MarvelRivalsAccount;
import com.mrivals_builder.Mrivals_Builder.entities.User;
import com.mrivals_builder.Mrivals_Builder.exceptions.NotFoundException;
import com.mrivals_builder.Mrivals_Builder.repositories.MarvelRivalsAccountRepository;
import com.mrivals_builder.Mrivals_Builder.repositories.UserRepository;
import com.mrivals_builder.Mrivals_Builder.security.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MarvelRivalsAccountService {

    @Autowired
    private ExternalApiService externalApiService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MarvelRivalsAccountRepository marvelRivalsAccountRepository;

    public MarvelRivalsAccountDTO addUserAccount(String mrivalsAccount){
    User currentUser = getCurrentUser();

        String statsRawJson = externalApiService.fetchUserPlayerStats(mrivalsAccount);

        MarvelRivalsAccount createdOrUpdated = marvelRivalsAccountRepository.findByMrivalsAccount(mrivalsAccount)
                .orElse(new MarvelRivalsAccount());
        createdOrUpdated.setUser(currentUser);
        createdOrUpdated.setMrivalsAccount(mrivalsAccount);
        createdOrUpdated.setStatsRawJson(statsRawJson);
        marvelRivalsAccountRepository.save(createdOrUpdated);

        return new MarvelRivalsAccountDTO(createdOrUpdated);
    }

    public String updateAccounts() {
        User currentUser = getCurrentUser();

        currentUser.getPlayerStats().forEach(account -> externalApiService.updatePlayerStats(account.getMrivalsAccount()));
        return "Updated in the next 30 minutes.";
    }

    public MarvelRivalsAccountDTO getUserAccount(Long id) {

        MarvelRivalsAccount account = marvelRivalsAccountRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Account with id " + id + " not found"));

        return new MarvelRivalsAccountDTO(account);
    }

    public List<MarvelRivalsAccountDTO> getUserAllAccounts() {
        User currentUser = getCurrentUser();

        List<MarvelRivalsAccount> accounts = marvelRivalsAccountRepository.findByUser(currentUser);

        return accounts.stream().map(account -> new MarvelRivalsAccountDTO(account)).toList();
    }

    private User getCurrentUser(){
        String email = SecurityUtils.getCurrentUserEmail();
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("User not Found!"));
    }

    public String deleteAccount(Long accountId) {
        MarvelRivalsAccount account = marvelRivalsAccountRepository.findById(accountId)
                .orElseThrow(() -> new NotFoundException("Account with id " + accountId + " not found"));

        if (account.getUser().getEmail().equals(SecurityUtils.getCurrentUserEmail())){
            marvelRivalsAccountRepository.delete(account);
        } else {
            throw new RuntimeException("You can't delete this account!");
        }

        return "Successfully deleted account with id " + accountId;
    }
}
