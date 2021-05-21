package fr.poitiers.univ.m1.s2.aaw.projet.online_bank.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class AuthToken {

    @Id
    private String token;
    private Integer userId;
    private Date expiredDate;

}
