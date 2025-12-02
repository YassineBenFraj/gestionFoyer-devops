package com.esprit.gestionfoyertp.restcontrollers;


import com.esprit.gestionfoyertp.entities.Chambre;
import com.esprit.gestionfoyertp.entities.TypeChambre;
import com.esprit.gestionfoyertp.services.ChambreService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @Operation(summary = "Récupérer toutes les chambres")
    @RequestMapping(value = "/getAll",method = RequestMethod.GET)
    public List<Chambre> getAllChambres() {
        return chambreService.getAllChambres();
    }

    @Operation(summary = "Récupérer une chambre par id")
    @RequestMapping(value = "/getChambreById/{idChambre}",method = RequestMethod.GET)
    public Chambre getChambreById(@PathVariable("idChambre") Long idChambre) {
        return chambreService.getChambreById(idChambre);
    }

    @Operation(summary = "Supprimer une chambre par id")
    @RequestMapping(value = "/{idChambre}" , method = RequestMethod.DELETE)
    public void deleteChambreById(@PathVariable("idChambre") Long idChambre){
        chambreService.deleteChambreById(idChambre);
    }

    @Operation(summary = "Récupérer les chambres par nom d'université")
    @GetMapping("/parUniversite/{nom}")
    public List<Chambre> getChambresParNomUniversite(@PathVariable("nom") String nomUniversite) {
        return chambreService.getChambresParNomUniversite(nomUniversite);
    }

    @Operation(summary = "Récupérer les chambres d’un bloc selon un type")
    @GetMapping("/parBlocEtType/{idBloc}/{type}")
    public List<Chambre> getChambresParBlocEtType(
            @PathVariable long idBloc,
            @PathVariable TypeChambre type) {

        return chambreService.getChambresParBlocEtType(idBloc, type);
    }

    @Operation(summary = "Récupérer les chambres non réservées d'une université selon un type de chambre")
    @GetMapping("/nonReserve")
    public List<Chambre> getChambresNonReserve(
            @RequestParam String nomUniversite,
            @RequestParam TypeChambre type) {
        return chambreService.getChambresNonReserveParNomUniversiteEtTypeChambre(nomUniversite, type);
    }
}
