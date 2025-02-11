package me.fagiolini.cinemapp.service;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import me.fagiolini.cinemapp.db.tables.daos.UtenteDao;


@Singleton
public class UtenteService {
    @Inject
    private UtenteDao utenteDao;

}
