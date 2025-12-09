package com.esprit.gestionfoyertp.services;

import com.esprit.gestionfoyertp.entities.Chambre;
import com.esprit.gestionfoyertp.entities.TypeChambre;

import java.util.List;

public interface ChambreService {

    Chambre addChambre(Chambre chambre);
    Chambre updateChambre(Chambre chambre);
    void deleteChambreById(Long idChambre);
    Chambre getChambreById(Long idChambre);
    List<Chambre> getAllChambres();
}
