package me.fagiolini.cinemapp.repository;

import io.micronaut.serde.annotation.SerdeImport;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import me.fagiolini.cinemapp.db.tables.daos.FilmDao;
import me.fagiolini.cinemapp.db.tables.pojos.Film;
import org.jooq.Configuration;
import org.jooq.DSLContext;

import java.util.List;

@Singleton
@SerdeImport(Film.class)
public class FilmRepository {
    @Inject
    private DSLContext dsl;
    private FilmDao filmDao;

    public FilmRepository(Configuration configuration) {
        filmDao = new FilmDao(configuration);
    }

    public Film getFilmById(long id) {
        return this.filmDao.fetchOneById(id)
    }

    public List<Film> getAllFilms() {
        return this.filmDao.findAll();
    }

    public void deleteFilmById(long id) {
        this.filmDao.deleteById(id);
    }


}
