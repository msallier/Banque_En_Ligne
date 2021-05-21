package fr.poitiers.univ.m1.s2.aaw.projet.online_bank.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Account{
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private Long balance;

    @ManyToOne
    private User user;
}
