package me.fagiolini.cinemapp.controller;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.annotation.*;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import jakarta.inject.Inject;
import me.fagiolini.cinemapp.db.tables.pojos.Sala;
import me.fagiolini.cinemapp.exception.myException;
import me.fagiolini.cinemapp.service.SalaService;
import me.fagiolini.cinemapp.Auth.VerifyAdmin;


import java.util.List;

@Controller
public class SalaController {
    @Inject
    SalaService salaService;
    @Secured(SecurityRule.IS_ANONYMOUS)
    @Get(uri = "/sale")
    public List<Sala> getSale() {
        return salaService.getSale();
    }
    @Secured(SecurityRule.IS_ANONYMOUS)
    @Get(uri = "/sala/{id}")
    public Sala getSala(@PathVariable Long id) {
        return salaService.getSalaById(id);
    }
    @Secured(SecurityRule.IS_AUTHENTICATED)
    @Post(uri = "insertSala")
    public void insertSala(@Body Sala sala, HttpRequest<?> request) throws myException {
        if (VerifyAdmin.verify(request))
            this.salaService.save(sala);
    }

    @Put(uri = "/updateSala")
    public void updateSala(@Body Sala sala, HttpRequest<?> request) throws myException {
        if(VerifyAdmin.verify(request))
            this.salaService.update(sala);
    }

    @Delete(uri = "/deleteSala")
    public void deleteSala(long id) {
        this.salaService.deleteById(id);
    }
    @Secured(SecurityRule.IS_ANONYMOUS)
    @Get(uri = "/salaByCinema/{id}")
    public List<Sala> getSaleByCinema(@PathVariable Long id) {
        return salaService.getSalaByCinemaId(id);
    }

}
