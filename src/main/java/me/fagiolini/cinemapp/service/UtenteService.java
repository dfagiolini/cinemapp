package me.fagiolini.cinemapp.service;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import me.fagiolini.cinemapp.db.tables.daos.UtenteDao;
import me.fagiolini.cinemapp.repository.UtenteRepository;


@Singleton
public class UtenteService {
    @Inject
    private UtenteRepository utenteRepository;

}
