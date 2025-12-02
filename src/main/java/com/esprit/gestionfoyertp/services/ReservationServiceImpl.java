package com.esprit.gestionfoyertp.services;


import com.esprit.gestionfoyertp.entities.Chambre;
import com.esprit.gestionfoyertp.entities.Etudiant;
import com.esprit.gestionfoyertp.entities.Reservation;
import com.esprit.gestionfoyertp.repositories.ChambreRepository;
import com.esprit.gestionfoyertp.repositories.EtudiantRepository;
import com.esprit.gestionfoyertp.repositories.ReservationRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private ChambreRepository chambreRepository;

    @Autowired
    private EtudiantRepository etudiantRepository;

    @Override
    public Reservation addReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    @Override
    public Reservation updateReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    @Override
    public void deleteReservation(Long idReservation) {
        reservationRepository.deleteById(idReservation);
    }

    @Override
    public Reservation getReservation(Long idReservation) {
        return reservationRepository.findById(idReservation).get();
    }

    @Override
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    @Override
    public Reservation ajouterReservation(long idBloc, long cinEtudiant) {
        // 1. Récupérer l'étudiant
        Etudiant etudiant = etudiantRepository.findEtudiantByCin(cinEtudiant);
        if (etudiant == null) return null;

        // 2. Trouver une chambre disponible dans le bloc
        List<Chambre> chambres = chambreRepository.findAll()
                .stream()
                .filter(c -> c.getBloc().getIdBloc() == idBloc)
                .toList();

        Chambre chambreDisponible = null;
        for (Chambre c : chambres) {
            int maxCapacity = switch (c.getTypeC()) {
                case SIMPLE -> 1;
                case DOUBLE -> 2;
                case TRIPLE -> 3;
            };
            if (c.getReservations() == null || c.getReservations().size() < maxCapacity) {
                chambreDisponible = c;
                break;
            }
        }

        if (chambreDisponible == null) {
            // Aucune chambre disponible
            return null;
        }

        // 3. Créer la réservation
        Reservation reservation = new Reservation();
        reservation.setAnneeUniversitaire(new java.util.Date());
        reservation.setEstValide(true);

        // 4. Ajouter l'étudiant à la réservation (ManyToMany)
        reservation.setEtudiants(List.of(etudiant));

        // 5. Affecter la chambre
        if (chambreDisponible.getReservations() == null) {
            chambreDisponible.setReservations(new java.util.ArrayList<>());
        }
        chambreDisponible.getReservations().add(reservation);
        reservation.setChambre(chambreDisponible);

        // 6. Générer numReservation : numChambre-nomBloc-anneeUniversitaire
        String annee = String.valueOf(java.time.Year.now().getValue());
        String numReservation = chambreDisponible.getNumChambre() + "-"
                + chambreDisponible.getBloc().getNomBloc() + "-"
                + annee;
        reservation.setNumReservation(numReservation);

        // 7. Sauvegarder la réservation
        return reservationRepository.save(reservation);
    }

    @Override
    public Reservation annulerReservation(long cinEtudiant) {

        // 1. Récupérer l'étudiant par CIN
        Etudiant etudiant = etudiantRepository.findEtudiantByCin(cinEtudiant);
        if (etudiant == null) return null;

        // 2. Récupérer sa réservation active
        Reservation reservation = reservationRepository.findAll()
                .stream()
                .filter(r -> r.isEstValide() && r.getEtudiants().contains(etudiant))
                .findFirst()
                .orElse(null);

        if (reservation == null) return null;

        // 3. Mettre estValide = false
        reservation.setEstValide(false);

        // 4. Désaffecter l'étudiant (ManyToMany)
        reservation.getEtudiants().remove(etudiant);

        // 5. Désaffecter la chambre
        Chambre chambre = reservation.getChambre();
        if (chambre != null) {
            // retirer la réservation de la chambre
            if (chambre.getReservations() != null) {
                chambre.getReservations().remove(reservation);
            }
            // mettre à jour la relation
            reservation.setChambre(null);
        }

        // 6. Sauvegarder la réservation mise à jour
        return reservationRepository.save(reservation);
    }

    @Override
    public List<Reservation> getReservationParAnneeUniversitaireEtNomUniversite(Date anneeUniversitaire, String nomUniversite) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(anneeUniversitaire);
        int year = cal.get(Calendar.YEAR);

        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, Calendar.JANUARY);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        Date startOfYear = cal.getTime();

        cal.set(Calendar.MONTH, Calendar.DECEMBER);
        cal.set(Calendar.DAY_OF_MONTH, 31);
        Date endOfYear = cal.getTime();

        return reservationRepository.findByChambre_Bloc_Foyer_Universite_NomUniversiteAndAnneeUniversitaireBetween(
                nomUniversite,
                startOfYear,
                endOfYear
        );
    }
}
