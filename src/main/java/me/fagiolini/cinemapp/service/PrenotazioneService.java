package me.fagiolini.cinemapp.service;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import me.fagiolini.cinemapp.db.tables.daos.PrenotazioneDao;
import me.fagiolini.cinemapp.db.tables.pojos.Prenotazione;
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

    public void save(Prenotazione prenotazione) {
        if (this.proiezioneService.getDisponibilita(prenotazione.getProiezioneId()) > 0)
            this.prenotazioneRepository.save(prenotazione);

    }

    public void delete(long id) {
        this.prenotazioneRepository.deleteById(id);
    }

    public void update(Prenotazione prenotazione) {
        this.prenotazioneRepository.update(prenotazione);
    }




}
