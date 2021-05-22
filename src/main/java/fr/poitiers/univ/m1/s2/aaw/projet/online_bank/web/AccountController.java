package fr.poitiers.univ.m1.s2.aaw.projet.online_bank.web;

import fr.poitiers.univ.m1.s2.aaw.projet.online_bank.model.Account;
import fr.poitiers.univ.m1.s2.aaw.projet.online_bank.repository.AccountRepository;
import fr.poitiers.univ.m1.s2.aaw.projet.online_bank.model.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/account")
public class AccountController {

    private AccountRepository accountRepository;

    public AccountController(AccountRepository repo){
        this.accountRepository = repo;
    }

    @PostMapping
    public void save(
            @RequestBody Account account
    ) {
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        account.setUser(principal);
        accountRepository.save(account);
    }

}
