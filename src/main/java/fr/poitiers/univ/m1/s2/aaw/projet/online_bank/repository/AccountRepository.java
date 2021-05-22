package fr.poitiers.univ.m1.s2.aaw.projet.online_bank.repository;

import fr.poitiers.univ.m1.s2.aaw.projet.online_bank.entity.Account;

import org.springframework.data.jpa.repository.JpaRepository;


public interface AccountRepository extends JpaRepository<Account, Long> {
}
