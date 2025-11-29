package org.enset.conferenceservice;

import org.enset.conferenceservice.entities.Conference;
import org.enset.conferenceservice.entities.ConferenceType;
import org.enset.conferenceservice.entities.Review;
import org.enset.conferenceservice.repository.ConferenceRepository;
import org.enset.conferenceservice.repository.ReviewRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@EnableFeignClients
@SpringBootApplication
public class ConferenceServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConferenceServiceApplication.class, args);
    }


    @Bean
    CommandLineRunner start(ConferenceRepository conferenceRepository,
                            ReviewRepository reviewRepository) {
        return args -> {

            // -------- CONF 1 --------
            Conference c1 = Conference.builder()
                    .title("Spring Boot & Microservices")
                    .type(ConferenceType.ACADEMIQUE)
                    .keynoteId(1L)
                    .date(new Date())
                    .duration(120)
                    .numberOfRegistrations(180)
                    .score(4.7)
                    .build();

            conferenceRepository.save(c1);

            Review c1r1 = Review.builder()
                    .conference(c1)
                    .date(new Date())
                    .text("Excellent talk, very clear explanations.")
                    .stars(5)
                    .build();

            Review c1r2 = Review.builder()
                    .conference(c1)
                    .date(new Date())
                    .text("Good content but too long for me.")
                    .stars(4)
                    .build();

            reviewRepository.save(c1r1);
            reviewRepository.save(c1r2);


            // -------- CONF 2 --------
            Conference c2 = Conference.builder()
                    .title("Cloud & DevOps in Action")
                    .type(ConferenceType.COMMERCIALE)
                    .keynoteId(2L)
                    .date(new Date())
                    .duration(90)
                    .numberOfRegistrations(220)
                    .score(4.3)
                    .build();

            conferenceRepository.save(c2);

            Review c2r1 = Review.builder()
                    .conference(c2)
                    .date(new Date())
                    .text("Learned a lot about CI/CD pipelines!")
                    .stars(5)
                    .build();

            Review c2r2 = Review.builder()
                    .conference(c2)
                    .date(new Date())
                    .text("Good presentation but lacked demos.")
                    .stars(3)
                    .build();

            reviewRepository.save(c2r1);
            reviewRepository.save(c2r2);


            // -------- CONF 3 --------
            Conference c3 = Conference.builder()
                    .title("AI & Distributed Systems")
                    .type(ConferenceType.ACADEMIQUE)
                    .keynoteId(3L)
                    .date(new Date())
                    .duration(150)
                    .numberOfRegistrations(140)
                    .score(4.9)
                    .build();

            conferenceRepository.save(c3);

            Review c3r1 = Review.builder()
                    .conference(c3)
                    .date(new Date())
                    .text("One of the best AI talks I've attended.")
                    .stars(5)
                    .build();

            Review c3r2 = Review.builder()
                    .conference(c3)
                    .date(new Date())
                    .text("Amazing content but very advanced!")
                    .stars(4)
                    .build();

            reviewRepository.save(c3r1);
            reviewRepository.save(c3r2);

            System.out.println(">>> Conferences + Reviews inserted successfully ✔");
        };
    }

}
