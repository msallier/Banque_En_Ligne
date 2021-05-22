package fr.poitiers.univ.m1.s2.aaw.projet.online_bank.service;

import fr.poitiers.univ.m1.s2.aaw.projet.online_bank.entity.Account;
import fr.poitiers.univ.m1.s2.aaw.projet.online_bank.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountService {

    @Autowired
    public AccountRepository accountRepository;

    public List<Account> getAllAccount(){
        return accountRepository.findAll();
    }

    public List<Account> getAllById(Long uuid){
        return accountRepository
                .findAll()
                .stream()
                .filter(account -> account.getId().equals(uuid))
                .collect(Collectors.toList());
    }
}
