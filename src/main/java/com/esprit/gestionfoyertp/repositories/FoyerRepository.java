package com.esprit.gestionfoyertp.repositories;

import com.esprit.gestionfoyertp.entities.Foyer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface FoyerRepository extends JpaRepository<Foyer, Long> {
}
