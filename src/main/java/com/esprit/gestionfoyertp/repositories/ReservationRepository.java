package com.esprit.gestionfoyertp.repositories;

import com.esprit.gestionfoyertp.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation,Long> {
    List<Reservation> findByChambre_Bloc_Foyer_Universite_NomUniversiteAndAnneeUniversitaireBetween(
            String nomUniversite,
            Date startOfYear,
            Date endOfYear
    );
}
