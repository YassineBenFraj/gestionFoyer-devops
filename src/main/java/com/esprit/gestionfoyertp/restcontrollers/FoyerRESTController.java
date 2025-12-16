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

}
