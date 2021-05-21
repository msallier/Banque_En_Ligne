package fr.poitiers.univ.m1.s2.aaw.projet.online_bank.repository;

import fr.poitiers.univ.m1.s2.aaw.projet.online_bank.model.AuthToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthTokenRepository extends JpaRepository<AuthToken, String> {

    List<AuthToken> findByUserId(Integer user);
}