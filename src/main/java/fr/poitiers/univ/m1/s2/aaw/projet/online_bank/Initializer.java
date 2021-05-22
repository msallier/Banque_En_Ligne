package fr.poitiers.univ.m1.s2.aaw.projet.online_bank;


import fr.poitiers.univ.m1.s2.aaw.projet.online_bank.model.Account;
import fr.poitiers.univ.m1.s2.aaw.projet.online_bank.model.User;
import fr.poitiers.univ.m1.s2.aaw.projet.online_bank.repository.AccountRepository;
import fr.poitiers.univ.m1.s2.aaw.projet.online_bank.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Profile("dev")
@Component
class Initializer implements CommandLineRunner {

    private final UserRepository repository;
    private final AccountRepository accountRepository;

    @Autowired
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public Initializer(UserRepository repository, AccountRepository accountRepository, BCryptPasswordEncoder encoder) {
        this.repository = repository;
        this.accountRepository = accountRepository;
        this.bCryptPasswordEncoder = encoder;
    }

    @Override
    public void run(String... strings) {

        for (int i = 1; i < 5; i++) {
            User user = new User("test" + i, this.bCryptPasswordEncoder.encode("test" + i), "test" + i + "@test.com");
            repository.save(user);
            Account comment = new Account(null, (long) (i * 100), user);
            accountRepository.save(comment);
        }

    }
}