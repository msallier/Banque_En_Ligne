package fr.poitiers.univ.m1.s2.aaw.projet.online_bank;


import fr.poitiers.univ.m1.s2.aaw.projet.online_bank.entity.Account;
import fr.poitiers.univ.m1.s2.aaw.projet.online_bank.entity.User;
import fr.poitiers.univ.m1.s2.aaw.projet.online_bank.repository.AccountRepository;
import fr.poitiers.univ.m1.s2.aaw.projet.online_bank.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Profile("dev")
@Component
class Initializer implements CommandLineRunner {

    private final UserRepository repository;
    private final AccountRepository accountRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public Initializer(UserRepository repository, AccountRepository repo, BCryptPasswordEncoder encoder) {
        this.repository = repository;
        this.accountRepository = repo;
        this.bCryptPasswordEncoder = encoder;
    }

    @Override
    public void run(String... strings) {

        User user = new User("test1",
                "test1@test.com",
                this.bCryptPasswordEncoder.encode("test1"));

        repository.save(user);

        Account account = new Account((long) (100000), user.getUser_id());
        accountRepository.save(account);
    }

}