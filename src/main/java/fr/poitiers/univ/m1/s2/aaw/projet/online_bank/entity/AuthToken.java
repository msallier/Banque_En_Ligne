package fr.poitiers.univ.m1.s2.aaw.projet.online_bank.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class AuthToken {

    @Id
    private String token;

    @Column(name = "USER_ID")
    private Long userId;

    @Column(name = "EXPIRED_DATE")
    private Date expiredDate;

}
