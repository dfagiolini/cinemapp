package me.fagiolini.cinemapp.controller;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import jakarta.inject.Inject;
import me.fagiolini.cinemapp.db.tables.pojos.Cinema;
import me.fagiolini.cinemapp.service.CinemaService;

import java.util.List;

@Controller
public class CinemaController {
    @Inject
    private CinemaService cinemaService;
    @Get(uri = "/cinema")
    public List<Cinema> getAllCinema() {
        return this.cinemaService.getAllCinema();
    }
}
