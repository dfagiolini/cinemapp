package me.fagiolini.cinemapp.repository;

import io.micronaut.serde.annotation.SerdeImport;
import jakarta.inject.Singleton;
import me.fagiolini.cinemapp.db.tables.pojos.Utente;

@Singleton
@SerdeImport(Utente.class)
public class UtenteRepository {
}
