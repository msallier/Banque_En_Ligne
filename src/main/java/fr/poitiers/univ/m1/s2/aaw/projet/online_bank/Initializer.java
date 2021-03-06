package fr.poitiers.univ.m1.s2.aaw.projet.online_bank;


import fr.poitiers.univ.m1.s2.aaw.projet.online_bank.entity.Account;
import fr.poitiers.univ.m1.s2.aaw.projet.online_bank.entity.User;
import fr.poitiers.univ.m1.s2.aaw.projet.online_bank.entity.Virement;
import fr.poitiers.univ.m1.s2.aaw.projet.online_bank.repository.AccountRepository;
import fr.poitiers.univ.m1.s2.aaw.projet.online_bank.repository.UserRepository;
import fr.poitiers.univ.m1.s2.aaw.projet.online_bank.repository.VirementRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@Slf4j
class Initializer implements CommandLineRunner {

    @Autowired
    private UserRepository repository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private VirementRepository virementRepository;

    @Override
    public void run(String... strings) {

        for (int i = 1; i < 5; i++) {
            User user = new User("test" + i,
                    "test" + i + "@test.com",
                    this.bCryptPasswordEncoder.encode("test" + i));
            if(i==1){
                user.setAdmin(true);
            }
            repository.save(user);
            Account comment = new Account(null, user.getId(), "PEL", 10000.0);

            log.info("User " + i + " mdp : " + user.getPassword(), user);

            accountRepository.save(comment);
            for(int j = 0; j < 5; ++j) {
                Virement virement = Virement.builder()
                        .id(null)
                        .idCompteDepuis((long) i)
                        .idCompteVers((long) ((j + 1) % 4) + 1)
                        .montant(100)
                        .motif("Virement" + i)
                        .build();
                virementRepository.save(virement);

            }
        }

    }
}