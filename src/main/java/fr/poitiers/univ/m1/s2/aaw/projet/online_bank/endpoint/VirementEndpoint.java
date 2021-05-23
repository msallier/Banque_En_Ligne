package fr.poitiers.univ.m1.s2.aaw.projet.online_bank.endpoint;

import fr.poitiers.univ.m1.s2.aaw.projet.online_bank.entity.Account;
import fr.poitiers.univ.m1.s2.aaw.projet.online_bank.entity.DTO.VirementDto;
import fr.poitiers.univ.m1.s2.aaw.projet.online_bank.entity.User;
import fr.poitiers.univ.m1.s2.aaw.projet.online_bank.entity.Virement;
import fr.poitiers.univ.m1.s2.aaw.projet.online_bank.repository.AccountRepository;
import fr.poitiers.univ.m1.s2.aaw.projet.online_bank.repository.UserRepository;
import fr.poitiers.univ.m1.s2.aaw.projet.online_bank.repository.VirementRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/virement")
public class VirementEndpoint {
    @Autowired
    private VirementRepository virementRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    List<Virement> getVirements() {
        List<Virement> all = virementRepository.findAll();
        return all;
    }

    @GetMapping("/recu/{id}")
    List<VirementDto> getVirementRecu(@PathVariable("id") Long idUser) {
        List<VirementDto> all = new ArrayList<>();
        List<Virement> virements = virementRepository.findAll();
        virements.forEach(virement ->  {
            Account accVers = accountRepository.findById(virement.getIdCompteVers()).orElseThrow(IllegalArgumentException::new);
            User userVers = userRepository.findById(idUser).orElseThrow(IllegalArgumentException::new);


            Account accDepuis = accountRepository.findById(virement.getIdCompteDepuis()).orElseThrow(IllegalArgumentException::new);
            User userDepuis = userRepository.findById(accDepuis.getUserId()).orElseThrow(IllegalArgumentException::new);

            if (accVers.getUserId().equals(idUser)) {
                VirementDto cpAccount = VirementDto
                        .builder()
                        .id(virement.getId())
                        .motif(virement.getMotif())
                        .montant(virement.getMontant())
                        .nomCompteVers(accVers.getName())
                        .nameUserCompteDepuis(userDepuis.getName())
                        .nameUserCompteVers(userVers.getName())
                        .nomCompteDepuis(accDepuis.getName())
                        .nomCompteVers(accVers.getName())
                        .build();

                all.add(cpAccount);
            }
        });
        return all;
    }

    @GetMapping("/effectue/{id}")
    List<VirementDto> getVirementEffectue(@PathVariable("id") Long idUser) {
        List<VirementDto> all = new ArrayList<>();
        List<Virement> virements = virementRepository.findAll();
        virements.forEach(virement ->  {
            Account accVers = accountRepository.findById(virement.getIdCompteVers()).orElseThrow(IllegalArgumentException::new);
            User userVers = userRepository.findById(idUser).orElseThrow(IllegalArgumentException::new);


            Account accDepuis = accountRepository.findById(virement.getIdCompteDepuis()).orElseThrow(IllegalArgumentException::new);
            User userDepuis = userRepository.findById(accDepuis.getUserId()).orElseThrow(IllegalArgumentException::new);

            if (accVers.getUserId().equals(idUser)) {
                VirementDto cpAccount = VirementDto
                        .builder()
                        .id(virement.getId())
                        .motif(virement.getMotif())
                        .montant(virement.getMontant())
                        .nomCompteVers(accVers.getName())
                        .nameUserCompteDepuis(userDepuis.getName())
                        .nameUserCompteVers(userVers.getName())
                        .nomCompteDepuis(accDepuis.getName())
                        .nomCompteVers(accVers.getName())
                        .build();
                all.add(cpAccount);
            }
        });
        return all;
    }

}
