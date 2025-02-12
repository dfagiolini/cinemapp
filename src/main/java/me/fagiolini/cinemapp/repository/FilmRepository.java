package me.fagiolini.cinemapp.repository;

import io.micronaut.serde.annotation.SerdeImport;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import me.fagiolini.cinemapp.db.tables.daos.FilmDao;
import me.fagiolini.cinemapp.db.tables.daos.ProiezioneDao;
import me.fagiolini.cinemapp.db.tables.pojos.Film;
import me.fagiolini.cinemapp.db.tables.pojos.Proiezione;
import org.jooq.Configuration;
import org.jooq.DSLContext;

import java.util.List;

@Singleton
@SerdeImport(Film.class)
public class FilmRepository {
    @Inject
    private DSLContext dsl;
    private FilmDao filmDao;
    private ProiezioneDao proiezioneDao;

    public FilmRepository(Configuration configuration) {
        filmDao = new FilmDao(configuration);
        proiezioneDao = new ProiezioneDao(configuration);
    }

    public Film getFilmById(long id) {
        return this.filmDao.fetchOneById(id);
    }

    public List<Film> getAllFilms() {
        return this.filmDao.findAll();
    }

    public void deleteFilmById(long id) {
        this.filmDao.deleteById(id);
    }

    public void insertFilm(Film film) {
        this.filmDao.insert(film);
    }
    public void updateFilm(Film film) {
        this.filmDao.update(film);
    }

    public Film getFilmByProiezione(Long proiezioneId) {
        Proiezione proiezione = this.proiezioneDao.fetchOneById(proiezioneId);
        return this.filmDao.fetchOneById(proiezione.getFilmId());
    }
}
