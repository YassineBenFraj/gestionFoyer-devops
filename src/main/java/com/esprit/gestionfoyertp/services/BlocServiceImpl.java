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

    @Autowired
    private ChambreRepository chambreRepository;

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

    @Override
    @Transactional
    public Bloc affecterChambresABloc(List<Long> numChambreIds, long idBloc) {
        // 1. Récupérer le bloc
        Bloc bloc = blocRepository.findById((int)idBloc).orElse(null);
        if (bloc == null) return null;

        // 2. Récupérer les chambres
        List<Chambre> chambres = numChambreIds.stream()
                .map(id -> chambreRepository.findById(id).orElse(null))
                .filter(ch -> ch != null)
                .collect(Collectors.toList());

        // 3. Affecter le bloc à chaque chambre
        chambres.forEach(ch -> ch.setBloc(bloc));

        // 4. Sauvegarder toutes les chambres
        chambreRepository.saveAll(chambres);

        // 5. Ajouter les chambres au bloc si tu as une liste de chambres dans l'entité Bloc
        bloc.setChambres(chambres);

        // 6. Sauvegarder et retourner le bloc
        return blocRepository.save(bloc);
    }
}
