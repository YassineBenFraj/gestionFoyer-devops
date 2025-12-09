package com.esprit.gestionfoyertp.restcontrollers;

import com.esprit.gestionfoyertp.entities.Reservation;
import com.esprit.gestionfoyertp.services.ReservationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;


@RestController
@AllArgsConstructor
@RequestMapping(value="/reservation")
@Tag(name = "Gestion des reservations")
public class ReservationRESTController {

    @Autowired
    ReservationService reservationService;


    @Operation(summary = "Ajouter une réservation")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Reservation addReservation(@RequestBody Reservation reservation) {
        return reservationService.addReservation(reservation);
    }

    @Operation(summary = "Mettre à jour une réservation existante")
    @RequestMapping(value = "/update/{idReservation}", method = RequestMethod.PUT)
    public Reservation updateReservation(@PathVariable("idReservation") Long idReservation, @RequestBody Reservation reservation) {
        reservation.setIdReservation(idReservation);
        return reservationService.updateReservation(reservation);
    }

    @Operation(summary = "Récupérer toutes les réservations")
    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public List<Reservation> getAllReservations() {
        return reservationService.getAllReservations();
    }

    @Operation(summary = "Récupérer une réservation par id")
    @RequestMapping(value = "/getReservationById/{idReservation}", method = RequestMethod.GET)
    public Reservation getReservation(@PathVariable("idReservation") Long idReservation) {
        return reservationService.getReservation(idReservation);
    }

    @Operation(summary = "Supprimer une réservation par id")
    @RequestMapping(value = "/{idReservation}", method = RequestMethod.DELETE)
    public void deleteReservation(@PathVariable("idReservation") Long idReservation) {
        reservationService.deleteReservation(idReservation);
    }
}