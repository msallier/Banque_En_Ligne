package fr.poitiers.univ.m1.s2.aaw.projet.online_bank.entity.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VirementDto {
    private Long id;
    private String nomCompteDepuis;
    private String nomCompteVers;
    private String nameUserCompteDepuis;
    private String nameUserCompteVers;
    private double montant;
    private String motif;
}




