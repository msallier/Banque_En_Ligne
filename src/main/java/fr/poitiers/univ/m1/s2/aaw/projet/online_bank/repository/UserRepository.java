package fr.poitiers.univ.m1.s2.aaw.projet.online_bank.repository;

import fr.poitiers.univ.m1.s2.aaw.projet.online_bank.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
