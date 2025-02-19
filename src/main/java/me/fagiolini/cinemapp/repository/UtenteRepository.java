package me.fagiolini.cinemapp.repository;

import io.micronaut.serde.annotation.SerdeImport;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import me.fagiolini.cinemapp.db.tables.daos.UtenteDao;
import me.fagiolini.cinemapp.db.tables.pojos.Utente;
import org.jooq.Configuration;

@Singleton
@SerdeImport(Utente.class)
public class UtenteRepository {

    private UtenteDao utenteDao;

    public UtenteRepository(Configuration configuration) {
        this.utenteDao = new UtenteDao(configuration);
    }

    public void save(Utente utente) {
        utenteDao.insert(utente);
    }

    public void update(Utente utente) {
        utenteDao.update(utente);
    }

    public void deleteById(long id) {
        utenteDao.deleteById(id);
    }

    public Utente findByNome(String nome) {
        return utenteDao.fetchOneByNome(nome);
    }

    public Utente findById(long id) {
        return utenteDao.fetchOneById(id);
    }
}
