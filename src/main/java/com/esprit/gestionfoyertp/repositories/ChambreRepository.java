package com.esprit.gestionfoyertp.repositories;

import com.esprit.gestionfoyertp.entities.Chambre;
import com.esprit.gestionfoyertp.entities.TypeChambre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChambreRepository extends JpaRepository<Chambre,Long> {
    List<Chambre> findByBlocFoyerUniversiteNomUniversite(String nomUniversite);
    List<Chambre> findByBlocIdBlocAndTypeC(long idBloc, TypeChambre typeC);
    List<Chambre> findByTypeCAndBloc_Foyer_Universite_NomUniversiteAndReservations_EstValideFalse(TypeChambre type, String nomUniversite);

}
