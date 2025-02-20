package me.fagiolini.cinemapp.controller;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.*;
import io.micronaut.http.annotation.Error;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import jakarta.inject.Inject;
import me.fagiolini.cinemapp.auth.VerifyAdmin;
import me.fagiolini.cinemapp.db.tables.pojos.Prenotazione;
import me.fagiolini.cinemapp.exception.myException;
import me.fagiolini.cinemapp.service.PrenotazioneService;
import me.fagiolini.cinemapp.utils.JwtUtil;

import java.awt.print.Pageable;
import java.time.LocalDate;
import java.util.List;

@Controller
public class PrenotazioneController {
    @Inject
    PrenotazioneService prenotazioneService;
    @Secured(SecurityRule.IS_AUTHENTICATED)
    @Get(uri = "/prenotazioni")
    public List<Prenotazione> prenotazioni(HttpRequest request) throws myException {

            return this.prenotazioneService.getPrenotazioniByUserId(JwtUtil.getUserIdFromRequest(request));

    }
    @Secured(SecurityRule.IS_AUTHENTICATED)
    @Get(uri = "/prenotazione/{id}")
    public Prenotazione getPrenotazione(@PathVariable long id, HttpRequest request) throws myException {
        if(VerifyAdmin.verify(request) || JwtUtil.getUserIdFromRequest(request) == this.prenotazioneService.getPrenotazione(id).getUtenteId())
            return this.prenotazioneService.getPrenotazione(id);
        else
            throw new myException("Accesso negato");
    }
    @Secured(SecurityRule.IS_AUTHENTICATED)
    @Post(uri = "/insertPrenotazione")
    public void insertPrenotazione(@Body Prenotazione p, HttpRequest<?> request) throws myException {

            System.out.println(JwtUtil.getUserIdFromRequest(request));
            p.setUtenteId(JwtUtil.getUserIdFromRequest(request));
            p.setDataPrenotazione(LocalDate.now());
            this.prenotazioneService.save(p);

    }

    @Secured(SecurityRule.IS_AUTHENTICATED)
    @Put(uri = "/updatePrenotazione")
    public void updatePrenotazione(@Body Prenotazione p, HttpRequest<?> request) throws myException {
        if(VerifyAdmin.verify(request))
            this.prenotazioneService.update(p);
        else {
            p.setUtenteId(JwtUtil.getUserIdFromRequest(request));
            p.setDataPrenotazione(LocalDate.now());
            this.prenotazioneService.update(p);
        }
    }
    @Secured(SecurityRule.IS_AUTHENTICATED)
    @Delete(uri = "/deletePrenotazione/{id}")
    public void deletePrenotazione(@PathVariable long id, HttpRequest<?> request) throws myException {
        if(VerifyAdmin.verify(request) || JwtUtil.getUserIdFromRequest(request) == this.prenotazioneService.getPrenotazione(id).getUtenteId())
            this.prenotazioneService.delete(id);
    }


}
