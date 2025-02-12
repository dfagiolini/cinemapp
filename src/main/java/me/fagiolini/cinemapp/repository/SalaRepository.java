package me.fagiolini.cinemapp.repository;

import io.micronaut.serde.annotation.SerdeImport;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import me.fagiolini.cinemapp.db.tables.daos.SalaDao;
import me.fagiolini.cinemapp.db.tables.pojos.Sala;
import org.jooq.Configuration;
import org.jooq.DSLContext;

import java.util.List;

@Singleton
@SerdeImport(Sala.class)
public class SalaRepository {
    @Inject
    private DSLContext dsl;

    private SalaDao salaDao;

    public SalaRepository(Configuration configuration) {
        this.salaDao = new SalaDao(configuration);
    }

    public Sala getSalaById(long id) {
        return this.salaDao.fetchOneById(id);
    }

    public List<Sala> getAllSala() {
        return this.salaDao.findAll();
    }

    public void insertSala(Sala sala) {
        this.salaDao.insert(sala);
    }

    public void updateSala(Sala sala) {
        this.salaDao.update(sala);
    }

    public void deleteSala(long id) {
        this.salaDao.deleteById(id);
    }

    public List<Sala> getSalaByCinemaId(long cinemaId) {
        return this.salaDao.fetchByCinemaId(cinemaId);
    }


}
