package com.mrivals_builder.Mrivals_Builder.controllers;

import com.mrivals_builder.Mrivals_Builder.dtos.MarvelRivalsAccountDTOs.MarvelRivalsAccountDTO;
import com.mrivals_builder.Mrivals_Builder.services.MarvelRivalsAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mr-accounts")
public class MarvelRivalsAccountController {

    @Autowired
    private MarvelRivalsAccountService marvelRivalsAccountService;

    @PostMapping("/add/{mrivalsAccount}")
    public ResponseEntity<MarvelRivalsAccountDTO> saveUserPlayerStats(@PathVariable String mrivalsAccount){
        return new ResponseEntity<>(marvelRivalsAccountService.addUserAccount(mrivalsAccount), HttpStatus.CREATED);
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<MarvelRivalsAccountDTO> getAccountById(@PathVariable Long accountId){
        return new ResponseEntity<>(marvelRivalsAccountService.getUserAccount(accountId), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<MarvelRivalsAccountDTO>> getAllAccounts(){
        return new ResponseEntity<>(marvelRivalsAccountService.getUserAllAccounts(), HttpStatus.CREATED);
    }

    @PostMapping("/update")
    public ResponseEntity<String> updateAccounts(){
        return new ResponseEntity<>(marvelRivalsAccountService.updateAccounts(), HttpStatus.CREATED);
    }

    @DeleteMapping("/{accountId}")
    public ResponseEntity<String> deleteAccount(@PathVariable Long accountId){
        return new ResponseEntity<>(marvelRivalsAccountService.deleteAccount(accountId), HttpStatus.NO_CONTENT);
    }
}
