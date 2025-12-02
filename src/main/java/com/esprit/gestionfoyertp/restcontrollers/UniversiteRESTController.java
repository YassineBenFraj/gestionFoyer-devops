package com.esprit.gestionfoyertp.restcontrollers;

import com.esprit.gestionfoyertp.entities.Universite;
import com.esprit.gestionfoyertp.services.UniversiteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value="/universite")
@Tag(name = "Gestion des universites")
public class UniversiteRESTController {

    @Autowired
    UniversiteService universiteService;

    @Operation(summary = "Ajouter une université")
    @PostMapping("/add")
    public Universite addUniversite(@RequestBody Universite universite) {
        return universiteService.addUniversite(universite);
    }

    @Operation(summary = "Mettre à jour une université existante")
    @RequestMapping(value = "/update/{idUniversite}", method = RequestMethod.PUT)
    public Universite updateUniversite(@PathVariable("idUniversite") Long idUniversite, @RequestBody Universite universite) {
        universite.setIdUniversite(idUniversite);
        return universiteService.updateUniversite(universite);
    }

    @Operation(summary = "Récupérer toutes les universités")
    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public List<Universite> getAllUniversites() {
        return universiteService.getAllUniversite();
    }

    @Operation(summary = "Récupérer une université par id")
    @RequestMapping(value = "/getUniversiteById/{idUniversite}", method = RequestMethod.GET)
    public Universite getUniversite(@PathVariable("idUniversite") Long idUniversite) {
        return universiteService.getUniversite(idUniversite);
    }

    @Operation(summary = "Supprimer une université par id")
    @RequestMapping(value = "/{idUniversite}", method = RequestMethod.DELETE)
    public void deleteUniversite(@PathVariable("idUniversite") Long idUniversite) {
        universiteService.deleteUniversiteById(idUniversite);
    }


    @Operation(summary = "Affecter un foyer à une université")
    @RequestMapping(value = "/affecterFoyer", method = RequestMethod.PUT)
    public Universite affecterFoyerAUniversite(
            @RequestParam long idFoyer,
            @RequestParam String nomUniversite) {
        return universiteService.affecterFoyerAUniversite(idFoyer, nomUniversite);
    }

    @Operation(summary = "Désaffecter un foyer d'une université")
    @RequestMapping(value = "/desaffecterFoyer/{idUniversite}", method = RequestMethod.PUT)
    public Universite desaffecterFoyerAUniversite(@PathVariable long idUniversite) {
        return universiteService.desaffecterFoyerAUniversite(idUniversite);
    }

}