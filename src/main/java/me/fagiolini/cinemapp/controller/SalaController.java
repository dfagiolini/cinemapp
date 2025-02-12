package me.fagiolini.cinemapp.controller;

import io.micronaut.http.annotation.*;
import jakarta.inject.Inject;
import me.fagiolini.cinemapp.db.tables.pojos.Sala;
import me.fagiolini.cinemapp.service.SalaService;

import java.util.List;

@Controller
public class SalaController {
    @Inject
    SalaService salaService;

    @Get(uri = "/sale")
    public List<Sala> getSale() {
        return salaService.getSale();
    }

    @Get(uri = "/sala/{id}")
    public Sala getSala(@PathVariable Long id) {
        return salaService.getSalaById(id);
    }

    @Post(uri = "insertSala")
    public void insertSala(@Body Sala sala) {
        this.salaService.save(sala);
    }

    @Put(uri = "/updateSala")
    public void updateSala(@Body Sala sala) {
        this.salaService.update(sala);
    }

    @Delete(uri = "/deleteSala")
    public void deleteSala(long id) {
        this.salaService.deleteById(id);
    }

    @Get(uri = "/salaByCinema/{id}")
    public List<Sala> getSaleByCinema(@PathVariable Long id) {
        return salaService.getSalaByCinemaId(id);
    }

}
