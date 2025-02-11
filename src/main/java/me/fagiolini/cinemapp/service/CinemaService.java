package me.fagiolini.cinemapp.service;

import io.micronaut.serde.annotation.SerdeImport;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import me.fagiolini.cinemapp.db.tables.daos.CinemaDao;
import me.fagiolini.cinemapp.db.tables.pojos.Cinema;
import me.fagiolini.cinemapp.repository.CinemaRepository;

import java.util.List;

@Singleton
@SerdeImport(Cinema.class)
public class CinemaService {
    @Inject
    private CinemaRepository cinemaRepository;

    public List<Cinema> getAllCinema() {
       return this.cinemaRepository.findAll();
    }

    public Cinema getCinemaById(long id) {
        return this.cinemaRepository.findById(id);
    }

    public void saveCinema(Cinema cinema) {
        this.cinemaRepository.save(cinema);
    }

    public void updateCinema(Cinema cinema) {
        this.cinemaRepository.update(cinema);
    }

    public void deleteCinema(long id) {
        this.cinemaRepository.deleteById(id);
    }
}
