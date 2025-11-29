package org.enset.keynoteservice.repository;

import org.enset.keynoteservice.entities.Keynote;
import org.springframework.data.jpa.repository.JpaRepository;


public interface KeynoteRepository extends JpaRepository<Keynote, Long> {

}

