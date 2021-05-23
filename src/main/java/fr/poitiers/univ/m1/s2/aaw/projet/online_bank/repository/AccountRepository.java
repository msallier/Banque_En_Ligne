package fr.poitiers.univ.m1.s2.aaw.projet.online_bank.repository;

import fr.poitiers.univ.m1.s2.aaw.projet.online_bank.entity.Account;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface AccountRepository extends JpaRepository<Account, Long> {
    List<Account> findAllByUserId(Long user_id);
}
