package me.fagiolini.cinemapp.controller;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Produces;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import me.fagiolini.cinemapp.model.ProgrammazioneCinemaModel;
import me.fagiolini.cinemapp.model.ProgrammazioneFilmModel;
import me.fagiolini.cinemapp.service.ProgrammazioneService;

import java.util.List;

@Controller
public class ProgrammazioneController {
    @Inject
    private ProgrammazioneService programmazioneService;
    @Secured(SecurityRule.IS_ANONYMOUS)
    @Get(uri = "/ProgrammazioneCinema/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<ProgrammazioneCinemaModel> programmazioneCinema(@PathVariable long id) {
        return this.programmazioneService.getProgrammazioneCinema(id);
    }
    @Secured(SecurityRule.IS_ANONYMOUS)
    @Get(uri = "/ProgrammazioneFilm/{id}")
    public List<ProgrammazioneFilmModel> programmazioneFilm(@PathVariable long id) {
        return this.programmazioneService.getProgrammazioneFilm(id);
    }
}
