package fr.poitiers.univ.m1.s2.aaw.projet.online_bank.web;

import fr.poitiers.univ.m1.s2.aaw.projet.online_bank.model.Account;
import fr.poitiers.univ.m1.s2.aaw.projet.online_bank.model.AuthToken;
import fr.poitiers.univ.m1.s2.aaw.projet.online_bank.repository.AuthTokenRepository;
import fr.poitiers.univ.m1.s2.aaw.projet.online_bank.model.User;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
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
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/user")
class UserController {


    @Autowired
    private AuthTokenRepository authTokenRepository;

    @Value("${com.auth.token}")
    private String authToken;

    @Value("${com.auth.expired}")
    private int expiredTime;

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/current")
    ResponseEntity<User> getUserConnected(
            Authentication authentication
    ) {
        User user = (User) authentication.getPrincipal();
        return ResponseEntity.ok().body(user);
    }

    @PostMapping("/login")
    public ResponseEntity<List<Account>> login(
            @RequestParam String username,
            @RequestParam String password
    ) throws IOException {
        try {
            final Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            username,
                            password
                    )
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            final User user = (User) authentication.getPrincipal();
            String sessionId = UUID.randomUUID().toString();
            Date expiredDate = new Date(System.currentTimeMillis() + expiredTime);
            AuthToken token = new AuthToken(sessionId, user.getId(), expiredDate);
            authTokenRepository.save(token);
            log.info("new session : {} expired in {} user {}", token.getToken(), token.getExpiredDate().toString(), user.getUsername());

            Cookie tokenCookie = new Cookie(authToken, token.getToken());
            tokenCookie.setPath("/");
            tokenCookie.setHttpOnly(true);
            tokenCookie.setMaxAge(expiredTime);
            ResponseEntity.ok().body(user.getAccount());
        } catch (Exception e) {
            log.error("Erreur de connexion", e);
            ResponseEntity.status(HttpStatus.LOCKED.value());
        }
        return null;
    }

}