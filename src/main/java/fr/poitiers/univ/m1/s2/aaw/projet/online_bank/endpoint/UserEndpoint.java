package fr.poitiers.univ.m1.s2.aaw.projet.online_bank.endpoint;

import fr.poitiers.univ.m1.s2.aaw.projet.online_bank.entity.Account;
import fr.poitiers.univ.m1.s2.aaw.projet.online_bank.entity.AuthToken;
import fr.poitiers.univ.m1.s2.aaw.projet.online_bank.entity.User;
import fr.poitiers.univ.m1.s2.aaw.projet.online_bank.repository.AuthTokenRepository;
import fr.poitiers.univ.m1.s2.aaw.projet.online_bank.repository.UserRepository;
import fr.poitiers.univ.m1.s2.aaw.projet.online_bank.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.server.Session;
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
import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping("/api/user")
@Slf4j
class UserEndpoint {


    @Autowired
    private AuthTokenRepository authTokenRepository;

    @Autowired
    private UserRepository userRepository;

    @Value("${com.auth.token}")
    private String authToken;

    @Value("${com.auth.expired}")
    private int expiredTime;


    @Autowired
    private AuthenticationManager authenticationManager;


    @GetMapping("/current")
    ResponseEntity<User> getUserConnected(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return ResponseEntity.ok().body(user);
    }

    @GetMapping("/{id}")
    ResponseEntity<User> getUserConnected(@PathVariable("id") Long id) {
        User user = userRepository.findById(id).orElse(new User());
        return ResponseEntity.ok().body(user);
    }


    @PostMapping("/login")
    public void login(@RequestParam String username, @RequestParam String password, HttpServletResponse response) throws IOException {
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
            response.addCookie(tokenCookie);
            response.sendRedirect("/account");
        } catch (Exception e) {
            log.info("Couple login/password incorrect");
            response.sendError(HttpStatus.LOCKED.value());

        }

    }
}
