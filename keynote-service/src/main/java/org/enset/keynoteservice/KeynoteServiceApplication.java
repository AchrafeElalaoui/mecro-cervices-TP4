package org.enset.keynoteservice;

import org.enset.keynoteservice.entities.Keynote;
import org.enset.keynoteservice.repository.KeynoteRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class KeynoteServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(KeynoteServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(KeynoteRepository keynoteRepository) {
		return args -> {

			// Keynote 1
			Keynote k1 = Keynote.builder()
					.lastName("Doe")
					.firstName("John")
					.email("john.doe@example.com")
					.functionTitle("Professeur")
					.build();
			keynoteRepository.save(k1);

			// Keynote 2
			Keynote k2 = Keynote.builder()
					.lastName("Smith")
					.firstName("Anna")
					.email("anna.smith@example.com")
					.functionTitle("Ingénieur R&D")
					.build();
			keynoteRepository.save(k2);

			// Keynote 3
			Keynote k3 = Keynote.builder()
					.lastName("El Amrani")
					.firstName("Youssef")
					.email("youssef.elamrani@example.com")
					.functionTitle("Conférencier international")
					.build();
			keynoteRepository.save(k3);

		};
	}

}
