package me.fagiolini.cinemapp.controller;

import io.micronaut.http.annotation.*;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import jakarta.inject.Inject;
import me.fagiolini.cinemapp.db.tables.pojos.Film;
import me.fagiolini.cinemapp.repository.FilmRepository;
import me.fagiolini.cinemapp.service.FilmService;

import java.util.List;


@Controller
public class FilmController {
    @Inject
    FilmService filmService;
    @Secured(SecurityRule.IS_ANONYMOUS)
    @Get(uri = "/film")
    public List<Film> getFilms() {
        return this.filmService.getAllFilms();
    }
    @Secured(SecurityRule.IS_ANONYMOUS)
    @Get(uri = "/film/{id}")
    public Film getFilm(@PathVariable Long id) {
        return this.filmService.getFilmById(id);
    }

    @Post(uri = "/insertFilm")
    public void insertFilm(@Body Film film) {
        this.filmService.insertFilm(film);
    }

    @Put(uri = "/updateFilm")
    public void updateFilm(@Body Film film) {
        this.filmService.updateFilm(film);
    }

    @Delete(uri = "/deleteFilm/{id}")
    public void deleteFilm(@PathVariable Long id) {
        this.filmService.deleteFilmById(id);
    }
    @Secured(SecurityRule.IS_ANONYMOUS)
    @Get(uri = "/getFilmByProiezione/{id}")
    public Film getFilmByProiezione(@PathVariable Long id) {
        return this.filmService.getFilmByProiezioneId(id);
    }


}
