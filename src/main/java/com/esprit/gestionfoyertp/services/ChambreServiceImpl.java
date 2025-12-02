package com.esprit.gestionfoyertp.services;


import com.esprit.gestionfoyertp.entities.Chambre;
import com.esprit.gestionfoyertp.entities.TypeChambre;
import com.esprit.gestionfoyertp.repositories.ChambreRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ChambreServiceImpl implements ChambreService {

    @Autowired
    private ChambreRepository chambreRepository;

    @Override
    public Chambre addChambre(Chambre chambre) {
        return chambreRepository.save(chambre);
    }

    @Override
    public Chambre updateChambre(Chambre chambre) {
        return chambreRepository.save(chambre);
    }

    @Override
    public void deleteChambreById(Long idChambre) {
        chambreRepository.deleteById(idChambre);
    }

    @Override
    public Chambre getChambreById(Long idChambre) {
        return chambreRepository.findById(idChambre).get();
    }

    @Override
    public List<Chambre> getAllChambres() {
        return chambreRepository.findAll();
    }

    @Override
    public List<Chambre> getChambresParNomUniversite(String nomUniversite) {
        return chambreRepository.findByBlocFoyerUniversiteNomUniversite(nomUniversite);
    }

    @Override
    public List<Chambre> getChambresParBlocEtType(long idBloc, TypeChambre typeC) {
        return chambreRepository.findByBlocIdBlocAndTypeC(idBloc, typeC);
    }

    @Override
    public List<Chambre> getChambresNonReserveParNomUniversiteEtTypeChambre(String nomUniversite, TypeChambre type) {
        // 1. Récupérer toutes les chambres du type et université
        List<Chambre> chambres = chambreRepository.findByTypeCAndBloc_Foyer_Universite_NomUniversiteAndReservations_EstValideFalse(type, nomUniversite);

        // 2. Filtrer celles qui n'ont pas de réservation pour l'année universitaire actuelle
        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);

        return chambres.stream().filter(ch -> {
            return ch.getReservations().stream().noneMatch(res -> {
                cal.setTime(res.getAnneeUniversitaire());
                int year = cal.get(Calendar.YEAR);
                return res.isEstValide() && year == currentYear;
            });
        }).collect(Collectors.toList());
    }
}
