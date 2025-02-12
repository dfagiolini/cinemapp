package me.fagiolini.cinemapp.service;


import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import me.fagiolini.cinemapp.db.tables.pojos.Cinema;
import me.fagiolini.cinemapp.db.tables.pojos.Film;
import me.fagiolini.cinemapp.db.tables.pojos.Proiezione;
import me.fagiolini.cinemapp.model.ProgrammazioneCinemaModel;
import me.fagiolini.cinemapp.model.ProgrammazioneFilmModel;
import me.fagiolini.cinemapp.repository.ProgrammazioneRepository;
import org.jooq.DSLContext;


import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static me.fagiolini.cinemapp.db.Tables.*;



@Singleton
public class ProgrammazioneService {
    @Inject
    private ProgrammazioneRepository programmazioneRepository;



    public List<ProgrammazioneCinemaModel> getProgrammazioneCinema(long cinemaId) {
       return this.programmazioneRepository.getProgrammazioneCinema(cinemaId);
    }

    public List<ProgrammazioneFilmModel> getProgrammazioneFilm(long filmId) {
        return this.programmazioneRepository.getProgrammazioneFilm(filmId);

    }
}
