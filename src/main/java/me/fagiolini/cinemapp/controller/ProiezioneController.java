package me.fagiolini.cinemapp.controller;

import io.micronaut.http.annotation.*;
import jakarta.inject.Inject;
import me.fagiolini.cinemapp.db.tables.pojos.Proiezione;
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

    @Post(uri = "/insertSala")
    public void insertSala(@Body Proiezione proiezione) {
        this.proiezioneService.save(proiezione);
    }

    @Put(uri = "/updateSala")
    public void updateSala(@Body Proiezione proiezione) {
        this.proiezioneService.update(proiezione);
    }

    @Delete(uri = "/deleteSala/{id}")
    public void delete(@PathVariable long id) {
        this.proiezioneService.delete(id);
    }
    @Get(uri = "/getDisponibilita/{id}")
    public int getDisponibilita(@PathVariable long id) {
        return this.proiezioneService.getDisponibilita(id);
    }
}
