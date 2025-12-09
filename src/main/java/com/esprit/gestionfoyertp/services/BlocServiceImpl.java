package com.esprit.gestionfoyertp.services;


import com.esprit.gestionfoyertp.entities.Bloc;
import com.esprit.gestionfoyertp.entities.Chambre;
import com.esprit.gestionfoyertp.entities.Foyer;
import com.esprit.gestionfoyertp.repositories.BlocRepository;
import com.esprit.gestionfoyertp.repositories.ChambreRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BlocServiceImpl implements BlocService {

    @Autowired
    private BlocRepository blocRepository;

    @Override
    public Bloc addBloc(Bloc bloc) {
        return blocRepository.save(bloc);
    }

    @Override
    public Bloc updateBloc(Bloc bloc) {
        return blocRepository.save(bloc);
    }

    @Override
    public void deleteBlocById(int idBloc) {
        blocRepository.deleteById(idBloc);
    }

    @Override
    public Bloc getBlocById(int id) {
        return blocRepository.findById(id).get();
    }

    @Override
    public List<Bloc> getAllBlocs() {
        return blocRepository.findAll();
    }

}
