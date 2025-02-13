package me.fagiolini.cinemapp.service;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Error;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import me.fagiolini.cinemapp.db.tables.daos.PrenotazioneDao;
import me.fagiolini.cinemapp.db.tables.pojos.Prenotazione;
import me.fagiolini.cinemapp.exception.myException;
import me.fagiolini.cinemapp.repository.PrenotazioneRepository;
import org.jooq.DSLContext;


import java.util.List;

@Singleton
public class PrenotazioneService {
    @Inject
    private PrenotazioneRepository prenotazioneRepository;
    @Inject
    private ProiezioneService proiezioneService;

    private final DSLContext create;

    public PrenotazioneService(DSLContext dslContext) {
        this.create = dslContext;
    }

    public List<Prenotazione> getPrenotazioni() {
        return this.prenotazioneRepository.findAll();
    }

    public Prenotazione getPrenotazione(long id) {
        return this.prenotazioneRepository.findById(id);
    }

    public void save(Prenotazione prenotazione) throws myException {
        if (checkAvailability(prenotazione))
            this.prenotazioneRepository.save(prenotazione);
        else
            throw new myException("Errore: non ci sono posti sufficienti");

    }

    public void delete(long id) {
        this.prenotazioneRepository.deleteById(id);
    }

    public void update(Prenotazione prenotazione) throws myException {
        if (checkAvailability(prenotazione))
            this.prenotazioneRepository.update(prenotazione);
        else
            throw new myException("Errore: non ci sono posti sufficienti");
    }

    private boolean checkAvailability(Prenotazione prenotazione) {
        return (this.proiezioneService.getDisponibilita(prenotazione.getProiezioneId()) - prenotazione.getNumeroBiglietti()) > 0;
    }

    @Error(exception = myException.class)
    public HttpResponse<String> error(HttpRequest<?> request, myException e) {
        return HttpResponse.notModified().body(e.getMessage());
    }


}
