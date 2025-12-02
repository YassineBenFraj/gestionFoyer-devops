package com.esprit.gestionfoyertp.restcontrollers;

import com.esprit.gestionfoyertp.entities.Foyer;
import com.esprit.gestionfoyertp.services.FoyerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value="/foyer")
@Tag(name = "Gestion des foyers")
public class FoyerRESTController {

    @Autowired
    FoyerService foyerService;

    @Operation(summary = "Ajouter un foyer")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Foyer addFoyer(@RequestBody Foyer foyer) {
        return foyerService.addFoyer(foyer);
    }

    @Operation(summary = "Mettre à jour un foyer existant")
    @RequestMapping(value = "/update/{idFoyer}", method = RequestMethod.PUT)
    public Foyer updateFoyer(@PathVariable("idFoyer") Long idFoyer, @RequestBody Foyer foyer) {
        foyer.setIdFoyer(idFoyer);
        return foyerService.updateFoyer(foyer);
    }

    @Operation(summary = "Récupérer tous les foyers")
    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public List<Foyer> getAllFoyers() {
        return foyerService.getAllFoyers();
    }

    @Operation(summary = "Récupérer un foyer par id")
    @RequestMapping(value = "/getFoyerById/{idFoyer}", method = RequestMethod.GET)
    public Foyer getFoyer(@PathVariable("idFoyer") Long idFoyer) {
        return foyerService.getFoyer(idFoyer);
    }

    @Operation(summary = "Supprimer un foyer par id")
    @RequestMapping(value = "/{idFoyer}", method = RequestMethod.DELETE)
    public void deleteFoyer(@PathVariable("idFoyer") Long idFoyer) {
        foyerService.deleteFoyerById(idFoyer);
    }

    @Operation(summary = "Ajouter un foyer avec ses blocs et l'affecter à une université")
    @PostMapping("/ajouterAvecUniversite")
    public Foyer ajouterFoyerEtAffecterAUniversite(
            @RequestBody Foyer foyer,
            @RequestParam long idUniversite) {
        return foyerService.ajouterFoyerEtAffecterAUniversite(foyer, idUniversite);
    }

}
