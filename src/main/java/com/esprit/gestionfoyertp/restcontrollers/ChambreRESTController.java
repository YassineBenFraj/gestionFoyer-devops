package com.esprit.gestionfoyertp.restcontrollers;


import com.esprit.gestionfoyertp.entities.Chambre;
import com.esprit.gestionfoyertp.services.ChambreService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping(value="/chambre")
@Tag(name = "Gestion des chambres")
public class ChambreRESTController {

    @Autowired
    private ChambreService chambreService;

    @Operation(summary = "Ajouter une chambre")
    @RequestMapping(value = "/add" , method = RequestMethod.POST)
    public Chambre addChambre (@RequestBody Chambre chambre){
        return chambreService.addChambre(chambre);
    }

    @Operation(summary = "Mettre à jour une chambre")
    @RequestMapping(value = "/update/{idChambre}" , method = RequestMethod.PUT)
    public Chambre updateChambre(@PathVariable("idChambre") Long idChambre, @RequestBody Chambre chambre){
        chambre.setIdChambre(idChambre);
        return chambreService.updateChambre(chambre);
    }
}
