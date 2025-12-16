package com.esprit.gestionfoyertp.services;


import com.esprit.gestionfoyertp.entities.Bloc;
import com.esprit.gestionfoyertp.entities.Foyer;
import com.esprit.gestionfoyertp.repositories.FoyerRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FoyerServiceImpl implements FoyerService {

    @Autowired
    private FoyerRepository foyerRepository;

    @Override
    public Foyer addFoyer(Foyer foyer) {
        return foyerRepository.save(foyer);
    }
}
