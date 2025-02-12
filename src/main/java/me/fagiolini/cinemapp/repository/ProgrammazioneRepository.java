package me.fagiolini.cinemapp.repository;

import io.micronaut.serde.annotation.SerdeImport;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import me.fagiolini.cinemapp.db.tables.pojos.Cinema;
import me.fagiolini.cinemapp.db.tables.pojos.Film;
import me.fagiolini.cinemapp.db.tables.pojos.Proiezione;
import me.fagiolini.cinemapp.model.ProgrammazioneCinemaModel;
import me.fagiolini.cinemapp.model.ProgrammazioneFilmModel;
import org.jooq.DSLContext;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static me.fagiolini.cinemapp.db.Tables.*;
import static me.fagiolini.cinemapp.db.Tables.PROIEZIONE;

@Singleton
@SerdeImport(ProgrammazioneCinemaModel.class)
@SerdeImport(ProgrammazioneFilmModel.class)
public class ProgrammazioneRepository {
    @Inject
    private DSLContext create;

    public List<ProgrammazioneCinemaModel> getProgrammazioneCinema(long cinemaId) {
        Map<Film, List<Proiezione>> result = this.create.select(FILM.fields())
                .select(PROIEZIONE.fields())
                .from(SALA)
                .join(PROIEZIONE).on(PROIEZIONE.SALA_ID.eq(SALA.ID))
                .join(FILM).on(PROIEZIONE.FILM_ID.eq(FILM.ID))
                .where(SALA.CINEMA_ID.eq(cinemaId))
                .fetch()
                .stream()
                .collect(Collectors.groupingBy(
                        record -> {
                            Film film = new Film();
                            film.setId(record.get(FILM.ID));
                            film.setTitolo(record.get(FILM.TITOLO));
                            film.setCopertina(record.get(FILM.COPERTINA));
                            film.setDescrizione(record.get(FILM.DESCRIZIONE));
                            film.setDurata(record.get(FILM.DURATA));
                            film.setGenere(record.get(FILM.GENERE));
                            film.setRegia(record.get(FILM.REGIA));
                            return film;
                        },
                        Collectors.mapping(record -> {
                            Proiezione proiezione = new Proiezione();
                            proiezione.setId(record.get(PROIEZIONE.ID));
                            proiezione.setSalaId(record.get(PROIEZIONE.SALA_ID));
                            proiezione.setFilmId(record.get(PROIEZIONE.FILM_ID));
                            proiezione.setDataOraInizio(record.get(PROIEZIONE.DATA_ORA_INIZIO));
                            proiezione.setDataOraFine(record.get(PROIEZIONE.DATA_ORA_FINE));
                            proiezione.setPrezzo(record.get(PROIEZIONE.PREZZO));

                            return proiezione;
                        }, Collectors.toList())
                ));
        List<ProgrammazioneCinemaModel> modelList = result.entrySet().stream()
                .map(entry -> new ProgrammazioneCinemaModel(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());

        return modelList;
    }
    public List<ProgrammazioneFilmModel> getProgrammazioneFilm(long filmId) {
        Map<Cinema, List<Proiezione>> result = this.create.select(CINEMA.fields())
                .select(PROIEZIONE.fields())
                .from(SALA)
                .join(PROIEZIONE).on(PROIEZIONE.SALA_ID.eq(SALA.ID))
                .join(CINEMA).on(CINEMA.ID.eq(SALA.CINEMA_ID))
                .where(PROIEZIONE.FILM_ID.eq(filmId))
                .fetch()
                .stream()
                .collect(Collectors.groupingBy(
                                record -> {
                                    Cinema cinema = new Cinema();
                                    cinema.setId(record.get(CINEMA.ID));
                                    cinema.setIndirizzo(record.get(CINEMA.INDIRIZZO));
                                    cinema.setNome(record.get(CINEMA.NOME));
                                    cinema.setTelefono(record.get(CINEMA.TELEFONO));
                                    return cinema;
                                },
                                Collectors.mapping(
                                        record -> {
                                            Proiezione proiezione = new Proiezione();
                                            proiezione.setId(record.get(PROIEZIONE.ID));
                                            proiezione.setSalaId(record.get(PROIEZIONE.SALA_ID));
                                            proiezione.setFilmId(record.get(PROIEZIONE.FILM_ID));
                                            proiezione.setDataOraInizio(record.get(PROIEZIONE.DATA_ORA_INIZIO));
                                            proiezione.setDataOraFine(record.get(PROIEZIONE.DATA_ORA_FINE));
                                            proiezione.setPrezzo(record.get(PROIEZIONE.PREZZO));
                                            // set other Proiezione fields
                                            return proiezione;
                                        }, Collectors.toList())
                        )
                );
        List<ProgrammazioneFilmModel> modelList = result.entrySet().stream()
                .map(entry -> new ProgrammazioneFilmModel(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
        return modelList;

    }
//    public Result<Record2<CinemaRecord, Result<Record1<ProiezioneRecord>>>> getProgrammazioneFilm(long filmId) {
//        return this.create.select(Cinema.CINEMA, multisetAgg(PROIEZIONE)).
//                from(Cinema.CINEMA, Sala.SALA, PROIEZIONE, Film.FILM)
//                .where(Cinema.CINEMA.ID.eq(Sala.SALA.CINEMA_ID))
//                .and(PROIEZIONE.SALA_ID.eq(Sala.SALA.ID))
//                .and(PROIEZIONE.FILM_ID.eq(Film.FILM.ID))
//                .and(Film.FILM.ID.eq(filmId)).groupBy(Cinema.CINEMA.ID).fetch();
//    }

//    public Result<Record2<FilmRecord, Result<Record1<ProiezioneRecord>>>> getProgrammazioneCinema(long cinemaId) {
//        return this.create.select(FILM, multisetAgg(PROIEZIONE))
//                .from(SALA, PROIEZIONE, FILM)
//                .where(SALA.CINEMA_ID.eq(cinemaId))
//                .and(PROIEZIONE.SALA_ID.eq(SALA.ID))
//                .and(PROIEZIONE.FILM_ID.eq(FILM.ID))
//                .groupBy(FILM.ID).fetch();
//    }

}
