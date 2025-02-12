package me.fagiolini.cinemapp.repository;

import io.micronaut.serde.annotation.SerdeImport;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import me.fagiolini.cinemapp.db.tables.daos.ProiezioneDao;
import me.fagiolini.cinemapp.db.tables.pojos.Proiezione;
import org.jooq.Configuration;
import org.jooq.DSLContext;

import java.util.List;

@Singleton
@SerdeImport(Proiezione.class)
public class ProiezioneRepository {
    @Inject
    private DSLContext dsl;
    private ProiezioneDao proiezioneDao;

    public ProiezioneRepository(Configuration configuration) {
        this.proiezioneDao = new ProiezioneDao(configuration);
    }

    public Proiezione getProiezioneById(long id) {
        return this.proiezioneDao.fetchOneById(id);
    }

    public List<Proiezione> getAllProiezioni() {
        return this.proiezioneDao.findAll();
    }

    public void insertProiezione(Proiezione proiezione) {
        this.proiezioneDao.insert(proiezione);
    }

    public void updateProiezione(Proiezione proiezione) {
        this.proiezioneDao.update(proiezione);
    }

    public void deleteProiezione(long id) {
        this.proiezioneDao.deleteById(id);
    }
}
