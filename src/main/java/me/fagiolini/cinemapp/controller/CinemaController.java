package me.fagiolini.cinemapp.controller;

import io.micronaut.http.annotation.*;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import jakarta.inject.Inject;
import me.fagiolini.cinemapp.db.tables.pojos.Cinema;
import me.fagiolini.cinemapp.service.CinemaService;

import java.util.List;

@Controller
public class CinemaController {
    @Inject
    private CinemaService cinemaService;
    @Secured(SecurityRule.IS_ANONYMOUS)
    @Get(uri = "/cinema")
    public List<Cinema> getAllCinema() {
        return this.cinemaService.getAllCinema();
    }
    @Secured(SecurityRule.IS_ANONYMOUS)
    @Get(uri = "/cinema/{id}")
    public Cinema getCinemaById(@PathVariable Integer id) {
        return this.cinemaService.getCinemaById(id);
    }
    @Post(uri = "/insertCinema")
    public void insertCinema(@Body Cinema cinema) {
        this.cinemaService.saveCinema(cinema);
    }
    @Put(uri = "/updateCinema")
    public void updateCinema(@Body Cinema cinema) {
        this.cinemaService.updateCinema(cinema);
    }

    @Delete(uri = "/deleteCinema/{id}")
    public void deleteCinema(@PathVariable long id) {
        this.cinemaService.deleteCinema(id);
    }


}
