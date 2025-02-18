package me.fagiolini.cinemapp.repository;

import io.micronaut.serde.annotation.SerdeImport;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import me.fagiolini.cinemapp.db.tables.daos.PrenotazioneDao;
import me.fagiolini.cinemapp.db.tables.pojos.Prenotazione;
import org.jooq.Configuration;
import org.jooq.DSLContext;

import java.util.List;
import java.util.Optional;

@Singleton
@SerdeImport(Prenotazione.class)
public class PrenotazioneRepository {
    @Inject
    private DSLContext dsl;
    private PrenotazioneDao prenotazioneDao;

    public PrenotazioneRepository(Configuration configuration) {
        this.prenotazioneDao = new PrenotazioneDao(configuration);
    }

    public List<Prenotazione> findAll() {
        return prenotazioneDao.findAll();
    }

    public Prenotazione findById(Long id) {
        return this.prenotazioneDao.findById(id);
    }

    public void save(Prenotazione prenotazione) {
        this.prenotazioneDao.insert(prenotazione);
    }

    public void update(Prenotazione prenotazione) {
        this.prenotazioneDao.update(prenotazione);
    }

    public void deleteById(Long id) {
        this.prenotazioneDao.deleteById(id);
    }

    public List<Prenotazione> findAllByUserId(Long userId) {
        return this.prenotazioneDao.fetchByUtenteId(userId);
    }
}
