package com.esprit.gestionfoyertp.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idReservation;
    private String numReservation;
    private Date anneeUniversitaire;
    private boolean estValide;

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "chambre_id")
    private Chambre chambre;

    @ManyToMany
    @JsonManagedReference
    private List<Etudiant> etudiants;
}
