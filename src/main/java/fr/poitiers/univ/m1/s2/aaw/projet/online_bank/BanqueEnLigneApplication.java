package fr.poitiers.univ.m1.s2.aaw.projet.online_bank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "fr.poitiers.univ.m1.s2.aaw.projet.online_bank")
@SpringBootApplication
public class BanqueEnLigneApplication {

	public static void main(String[] args) {
		SpringApplication.run(BanqueEnLigneApplication.class, args);
	}

}
