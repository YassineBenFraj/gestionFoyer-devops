package com.esprit.gestionfoyertp.services;


import com.esprit.gestionfoyertp.entities.Bloc;

import java.util.List;

public interface BlocService {

    public Bloc addBloc(Bloc bloc);
    List<Bloc> getAllBlocs();
}
