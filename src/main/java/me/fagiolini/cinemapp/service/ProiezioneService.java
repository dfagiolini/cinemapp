package me.fagiolini.cinemapp.service;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import me.fagiolini.cinemapp.db.tables.daos.ProiezioneDao;
import me.fagiolini.cinemapp.db.tables.daos.SalaDao;
import me.fagiolini.cinemapp.db.tables.pojos.Proiezione;
import org.jooq.DSLContext;


import java.util.List;

import static me.fagiolini.cinemapp.db.Tables.PRENOTAZIONE;
import static me.fagiolini.cinemapp.db.Tables.PROIEZIONE;
import static org.jooq.impl.DSL.*;

@Singleton
public class ProiezioneService {
    @Inject
    private ProiezioneDao proiezioneDao;
    @Inject
    private SalaDao salaDao;

    private final DSLContext create;

    public ProiezioneService(DSLContext dslContext) {
        this.create = dslContext;
    }

    public Proiezione getProiezioneById(long id) {
        return this.proiezioneDao.fetchOneById(id);
    }

    public List<Proiezione> getProiezioni() {
        return this.proiezioneDao.findAll();
    }

    public void save(Proiezione proiezione) {
        int count  = this.create.select(count()).from(PROIEZIONE)
                .where(PROIEZIONE.SALA_ID.eq(proiezione.getSalaId()))
                .and(row(PROIEZIONE.DATA_ORA_INIZIO, PROIEZIONE.DATA_ORA_FINE)
                        .overlaps(proiezione.getDataOraInizio(), proiezione.getDataOraFine()))
                .fetchOneInto(Integer.class);

        if (count == 0)
            this.proiezioneDao.insert(proiezione);
    }

    public void update(Proiezione proiezione) {
        this.proiezioneDao.update(proiezione);
    }
    public void delete(long id) {
        this.proiezioneDao.deleteById(id);
    }

    public int getDisponibilita(long proiezioneId) {
        Proiezione proiezione = proiezioneDao.fetchOneById(proiezioneId);
        return this.salaDao.fetchOneById(proiezione.getSalaId()).getCapacita()
                - this.create.select(sum(PRENOTAZIONE.NUMERO_BIGLIETTI)).from(PRENOTAZIONE)
                .where(PRENOTAZIONE.PROIEZIONE_ID.eq(proiezione.getId())).fetchOneInto(Integer.class);
    }
}
