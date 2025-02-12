package me.fagiolini.cinemapp.repository;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import me.fagiolini.cinemapp.db.tables.daos.CinemaDao;
import me.fagiolini.cinemapp.db.tables.pojos.Cinema;
import org.jooq.Configuration;
import org.jooq.DSLContext;

import java.util.List;

@Singleton
public class CinemaRepository {
    @Inject
    private DSLContext dsl;
    private CinemaDao cinemaDao;

    public CinemaRepository(Configuration configuration) {
        this.cinemaDao = new CinemaDao(configuration);
    }

    public List<Cinema> findAll() {
        return cinemaDao.findAll();
    }
    public Cinema findById(long id) {
        return cinemaDao.findById(id);
    }
    public void save(Cinema cinema) {
        this.cinemaDao.insert(cinema);
    }

    public void update(Cinema cinema) {
        this.cinemaDao.update(cinema);
    }

    public void deleteById(long id) {
        this.cinemaDao.deleteById(id);
    }

}
