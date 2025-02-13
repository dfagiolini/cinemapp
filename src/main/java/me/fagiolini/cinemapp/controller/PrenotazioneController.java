package me.fagiolini.cinemapp.controller;

import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.*;
import io.micronaut.http.annotation.Error;
import jakarta.inject.Inject;
import me.fagiolini.cinemapp.db.tables.pojos.Prenotazione;
import me.fagiolini.cinemapp.exception.myException;
import me.fagiolini.cinemapp.service.PrenotazioneService;

import java.util.List;

@Controller
public class PrenotazioneController {
    @Inject
    PrenotazioneService prenotazioneService;

    @Get(uri = "/prenotazioni")
    public List<Prenotazione> prenotazioni()
    {
        return this.prenotazioneService.getPrenotazioni();
    }

    @Get(uri = "/prenotazione/{id}")
    public Prenotazione getPrenotazione(@PathVariable long id) {
        return this.prenotazioneService.getPrenotazione(id);
    }

    @Post(uri = "/insertPrenotazione")
    public void insertPrenotazione(@Body Prenotazione p) throws myException {
        this.prenotazioneService.save(p);
    }

    @Put(uri = "/updatePrenotazione")
    public void updatePrenotazione(@Body Prenotazione p) throws myException {
        this.prenotazioneService.update(p);
    }

    @Delete(uri = "/deletePrenotazione/{id}")
    public void deletePrenotazione(@PathVariable long id) {
        this.prenotazioneService.delete(id);
    }


}
