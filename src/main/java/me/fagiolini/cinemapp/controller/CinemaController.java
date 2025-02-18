package me.fagiolini.cinemapp.controller;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.annotation.*;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import jakarta.inject.Inject;
import me.fagiolini.cinemapp.auth.VerifyAdmin;
import me.fagiolini.cinemapp.db.tables.pojos.Cinema;
import me.fagiolini.cinemapp.exception.myException;
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
    @Secured(SecurityRule.IS_AUTHENTICATED)
    @Post(uri = "/insertCinema")
    public void insertCinema(@Body Cinema cinema, HttpRequest<?> request) throws myException {
        if(VerifyAdmin.verify(request))
            this.cinemaService.saveCinema(cinema);
    }
    @Secured(SecurityRule.IS_AUTHENTICATED)
    @Put(uri = "/updateCinema")
    public void updateCinema(@Body Cinema cinema, HttpRequest<?> request) throws myException {
        if(VerifyAdmin.verify(request))
            this.cinemaService.updateCinema(cinema);
    }
    @Secured(SecurityRule.IS_AUTHENTICATED)
    @Delete(uri = "/deleteCinema/{id}")
    public void deleteCinema(@PathVariable long id, HttpRequest<?> request) throws myException {
        if(VerifyAdmin.verify(request))
            this.cinemaService.deleteCinema(id);
    }


}
