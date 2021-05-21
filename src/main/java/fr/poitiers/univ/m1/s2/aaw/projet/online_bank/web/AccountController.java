package fr.poitiers.univ.m1.s2.aaw.projet.online_bank.web;

import fr.poitiers.univ.m1.s2.aaw.projet.online_bank.model.Account;
import fr.poitiers.univ.m1.s2.aaw.projet.online_bank.repository.AccountRepository;
import fr.poitiers.univ.m1.s2.aaw.projet.online_bank.model.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/account")
public class AccountController {

    private AccountRepository commentRepository;

    public AccountController(AccountRepository repo){
        this.commentRepository = repo;
    }

    @GetMapping
    Collection<Account> users() {
        return commentRepository.findAll();
    }

    @PostMapping
    void save(
            @RequestBody Account comment
    ) {
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        comment.setUser(principal);
        commentRepository.save(comment);
    }

}
