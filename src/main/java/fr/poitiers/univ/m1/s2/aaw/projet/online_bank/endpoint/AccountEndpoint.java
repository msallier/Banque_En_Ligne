package fr.poitiers.univ.m1.s2.aaw.projet.online_bank.endpoint;

import fr.poitiers.univ.m1.s2.aaw.projet.online_bank.entity.Account;
import fr.poitiers.univ.m1.s2.aaw.projet.online_bank.entity.AuthToken;
import fr.poitiers.univ.m1.s2.aaw.projet.online_bank.entity.User;
import fr.poitiers.univ.m1.s2.aaw.projet.online_bank.repository.AccountRepository;
import fr.poitiers.univ.m1.s2.aaw.projet.online_bank.repository.AuthTokenRepository;
import fr.poitiers.univ.m1.s2.aaw.projet.online_bank.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/accounts")
public class AccountEndpoint {

    @Autowired
    private AccountRepository accountRepository;

    @GetMapping
    Collection<Account> getAccounts() {
        List<Account> all = accountRepository.findAll();
        return all;
    }

    @PostMapping
    void save(@RequestBody Account account) {
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        account.setUserId(principal.getUser_id());
        System.out.println(account);
        accountRepository.save(account);
    }

    @GetMapping("/{id}")
    Collection<Account> getUserAccounts(@PathVariable("id") Long id) {
        List<Account> all = accountRepository
                .findAll()
                .stream()
                .filter(account -> account.getId().equals(id))
                .collect(Collectors.toList());
        return all;
    }



}