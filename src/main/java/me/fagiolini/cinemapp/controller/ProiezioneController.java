package me.fagiolini.cinemapp.controller;

import io.micronaut.http.annotation.*;
import jakarta.inject.Inject;
import me.fagiolini.cinemapp.db.tables.pojos.Proiezione;
import me.fagiolini.cinemapp.exception.myException;
import me.fagiolini.cinemapp.service.ProiezioneService;

import java.util.List;

@Controller
public class ProiezioneController {
    @Inject
    ProiezioneService proiezioneService;

    @Get(uri = "/proiezioni")
    public List<Proiezione> proiezioni() {
        return this.proiezioneService.getProiezioni();
    }

    @Get(uri = "/proiezione/{id}")
    public Proiezione proiezione(@PathVariable Integer id) {
        return this.proiezioneService.getProiezioneById(id);
    }

    @Post(uri = "/insertProiezione")
    public void insertProiezione(@Body Proiezione proiezione) throws myException {
        this.proiezioneService.save(proiezione);
    }

    @Put(uri = "/updateProiezione")
    public void updateProiezione(@Body Proiezione proiezione) throws myException {
        this.proiezioneService.update(proiezione);
    }

    @Delete(uri = "/deleteProiezione/{id}")
    public void delete(@PathVariable long id) {
        this.proiezioneService.delete(id);
    }
    @Get(uri = "/getDisponibilita/{id}")
    public int getDisponibilita(@PathVariable long id) {
        return this.proiezioneService.getDisponibilita(id);
    }
}
