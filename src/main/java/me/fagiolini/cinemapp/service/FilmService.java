package me.fagiolini.cinemapp.service;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import me.fagiolini.cinemapp.db.tables.daos.FilmDao;
import me.fagiolini.cinemapp.db.tables.pojos.Film;
import me.fagiolini.cinemapp.repository.FilmRepository;
import me.fagiolini.cinemapp.repository.ProiezioneRepository;

import java.util.List;
@Singleton
public class FilmService {

    @Inject
    private FilmRepository filmRepository;


    public List<Film> getAllFilms() {
        return this.filmRepository.getAllFilms();
    }
    public Film getFilmById(long id) {
        return this.filmRepository.getFilmById(id);
    }

    public void insertFilm(Film film) {
        this.filmRepository.insertFilm(film);
    }
    public void updateFilm(Film film) {
        this.filmRepository.updateFilm(film);
    }
    public void deleteFilmById(long id) {
        this.filmRepository.deleteFilmById(id);
    }

    public Film getFilmByProiezioneId(long proiezioneId) {
        return this.filmRepository.getFilmByProiezione(proiezioneId);
    }


}
