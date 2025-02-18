package me.fagiolini.cinemapp.controller;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.annotation.*;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import jakarta.inject.Inject;
import me.fagiolini.cinemapp.auth.VerifyAdmin;
import me.fagiolini.cinemapp.db.tables.pojos.Proiezione;
import me.fagiolini.cinemapp.exception.myException;
import me.fagiolini.cinemapp.service.ProiezioneService;

import java.util.List;

@Controller
public class ProiezioneController {
    @Inject
    ProiezioneService proiezioneService;

    @Secured(SecurityRule.IS_ANONYMOUS)
    @Get(uri = "/proiezioni")
    public List<Proiezione> proiezioni() {
        return this.proiezioneService.getProiezioni();
    }
    @Secured(SecurityRule.IS_ANONYMOUS)
    @Get(uri = "/proiezione/{id}")
    public Proiezione proiezione(@PathVariable Integer id) {
        return this.proiezioneService.getProiezioneById(id);
    }
    @Secured(SecurityRule.IS_AUTHENTICATED)
    @Post(uri = "/insertProiezione")
    public void insertProiezione(@Body Proiezione proiezione, HttpRequest<?> request) throws myException {
        if(VerifyAdmin.verify(request))
            this.proiezioneService.save(proiezione);
    }
    @Secured(SecurityRule.IS_AUTHENTICATED)
    @Put(uri = "/updateProiezione")
    public void updateProiezione(@Body Proiezione proiezione, HttpRequest<?> request) throws myException {
        if(VerifyAdmin.verify(request))
            this.proiezioneService.update(proiezione);
    }
    @Secured(SecurityRule.IS_AUTHENTICATED)
    @Delete(uri = "/deleteProiezione/{id}")
    public void delete(@PathVariable long id, HttpRequest<?> request) throws myException {
        if(VerifyAdmin.verify(request))
            this.proiezioneService.delete(id);
    }
    @Secured(SecurityRule.IS_ANONYMOUS)
    @Get(uri = "/getDisponibilita/{id}")
    public int getDisponibilita(@PathVariable long id) {
        return this.proiezioneService.getDisponibilita(id);
    }
}
