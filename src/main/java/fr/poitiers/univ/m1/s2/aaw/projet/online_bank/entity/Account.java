package fr.poitiers.univ.m1.s2.aaw.projet.online_bank.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "balance")
    private Long balance;

    @Column(name = "USER_ID")
    private Long userId;

    public Account(Long balance, Long user){
        this.balance = balance;
        this.userId = user;
    }
}
