package com.esprit.gestionfoyertp.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Data
public class Chambre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idChambre;
    private long numChambre;
    @Enumerated(EnumType.STRING)
    private TypeChambre typeC;



    @ManyToOne
    @JoinColumn(name = "bloc_id")
    @JsonIgnoreProperties("chambres")
    private Bloc bloc;

    @OneToMany(mappedBy = "chambre", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("chambre")
    private List<Reservation> reservations = new ArrayList<>();
}
