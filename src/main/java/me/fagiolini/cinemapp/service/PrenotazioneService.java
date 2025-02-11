package me.fagiolini.cinemapp.service;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import me.fagiolini.cinemapp.db.tables.daos.PrenotazioneDao;
import me.fagiolini.cinemapp.db.tables.daos.ProiezioneDao;
import me.fagiolini.cinemapp.db.tables.daos.SalaDao;
import me.fagiolini.cinemapp.db.tables.pojos.Prenotazione;
import org.jooq.DSLContext;


import java.util.List;

@Singleton
public class PrenotazioneService {
    @Inject
    private PrenotazioneDao prenotazioneDao;
    @Inject
    private ProiezioneService proiezioneService;

    private final DSLContext create;

    public PrenotazioneService(DSLContext dslContext) {
        this.create = dslContext;
    }

    public List<Prenotazione> getPrenotazioni() {
        return this.prenotazioneDao.findAll();
    }

    public Prenotazione getPrenotazione(long id) {
        return this.prenotazioneDao.findById(id);
    }

    public void save(Prenotazione prenotazione) {
        if (this.proiezioneService.getDisponibilita(prenotazione.getProiezioneId()) > 0)
            this.prenotazioneDao.insert(prenotazione);

    }

    public void delete(long id) {
        this.prenotazioneDao.deleteById(id);
    }

    public void update(Prenotazione prenotazione) {
        this.prenotazioneDao.update(prenotazione);
    }




}
