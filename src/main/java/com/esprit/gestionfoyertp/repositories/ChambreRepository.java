package com.esprit.gestionfoyertp.repositories;

import com.esprit.gestionfoyertp.entities.Chambre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChambreRepository extends JpaRepository<Chambre,Long> {
}
