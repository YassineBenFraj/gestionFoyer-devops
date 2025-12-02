package com.esprit.gestionfoyertp.restcontrollers;

import com.esprit.gestionfoyertp.entities.Bloc;
import com.esprit.gestionfoyertp.services.BlocService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value="/bloc")
@Tag(name = "Gestion des blocs")
public class BlocRESTController {

    @Autowired
    BlocService blocService;

    @Operation(summary = "Ajouter un nouveau bloc")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Bloc addBloc(@RequestBody Bloc bloc) {
        bloc.setIdBloc(0);
        return blocService.addBloc(bloc);
    }

    @Operation(summary = "Mettre à jour un bloc")
    @PutMapping("/update/{idBloc}")
    public Bloc updateBloc(@PathVariable int idBloc, @RequestBody Bloc bloc) {
        bloc.setIdBloc(idBloc);
        return blocService.updateBloc(bloc);
    }

    @Operation(summary = "Récupérer tous les blocs")
    @GetMapping("/getAll")
    public List<Bloc> getAll() {
        return blocService.getAllBlocs();
    }

    @Operation(summary = "Récupérer un bloc par id")
    @GetMapping("/getBlocById/{idBloc}")
    public Bloc getBlocById(@PathVariable int idBloc) {
        return blocService.getBlocById(idBloc);
    }

    @Operation(summary = "Supprimer un bloc par id")
    @DeleteMapping("/{idBloc}")
    public void deleteBloc(@PathVariable int idBloc) {
        blocService.deleteBlocById(idBloc);
    }

    @Operation(summary = "Affecter des chambres à un bloc")
    @PutMapping("/affecterChambres")
    public Bloc affecterChambresABloc(
            @RequestParam List<Long> numChambreIds,
            @RequestParam long idBloc) {
        return blocService.affecterChambresABloc(numChambreIds, idBloc);
    }

}