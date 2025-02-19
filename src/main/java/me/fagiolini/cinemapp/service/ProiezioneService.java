package me.fagiolini.cinemapp.service;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import me.fagiolini.cinemapp.db.tables.daos.SalaDao;
import me.fagiolini.cinemapp.db.tables.pojos.Proiezione;
import me.fagiolini.cinemapp.exception.myException;
import me.fagiolini.cinemapp.repository.ProiezioneRepository;
import me.fagiolini.cinemapp.repository.SalaRepository;
import org.jooq.DSLContext;


import java.util.List;

import static me.fagiolini.cinemapp.db.Tables.PRENOTAZIONE;
import static me.fagiolini.cinemapp.db.Tables.PROIEZIONE;
import static org.jooq.impl.DSL.*;

@Singleton
public class ProiezioneService {
    @Inject
    private ProiezioneRepository proiezioneRepository;
    @Inject
    private SalaRepository salaRepository;
    @Inject
    private DSLContext create;



    public Proiezione getProiezioneById(long id) {
        return this.proiezioneRepository.getProiezioneById(id);
    }

    public List<Proiezione> getProiezioni() {
        return this.proiezioneRepository.getAllProiezioni();
    }

    public void save(Proiezione proiezione) throws myException {
        int count = getOverlappingCount(proiezione);

        if (count == 0)
            this.proiezioneRepository.insertProiezione(proiezione);
        else
            throw new myException("Errore: la proiezione si sovrappone a una esistente");
    }

    private Integer getOverlappingCount(Proiezione proiezione) {
        return this.create.select(count()).from(PROIEZIONE)
                .where(PROIEZIONE.SALA_ID.eq(proiezione.getSalaId()))
                .and(row(PROIEZIONE.DATA_ORA_INIZIO, PROIEZIONE.DATA_ORA_FINE)
                        .overlaps(proiezione.getDataOraInizio(), proiezione.getDataOraFine()))
                .fetchOptionalInto(Integer.class).orElse(0);

    }

    public void update(Proiezione proiezione) throws myException {
        int count = getOverlappingCount(proiezione);

        if (count == 0)
            this.proiezioneRepository.updateProiezione(proiezione);
        else
            throw new myException("Errore: la proiezione si sovrappone a una esistente");
    }

    public void delete(long id) {
        this.proiezioneRepository.deleteProiezione(id);
    }

    public int getDisponibilita(long proiezioneId) {
        Proiezione proiezione = proiezioneRepository.getProiezioneById(proiezioneId);
        Integer giaPrenotati = this.create.select(sum(PRENOTAZIONE.NUMERO_BIGLIETTI)).from(PRENOTAZIONE)
                .where(PRENOTAZIONE.PROIEZIONE_ID.eq(proiezione.getId())).fetchOptionalInto(Integer.class).orElse(0);
        return this.salaRepository.getSalaById(proiezione.getSalaId()).getCapacita()
                - giaPrenotati;
    }
}
