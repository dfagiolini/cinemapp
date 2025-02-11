package me.fagiolini.cinemapp.service;


import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import me.fagiolini.cinemapp.db.tables.daos.ProiezioneDao;
import me.fagiolini.cinemapp.db.tables.daos.SalaDao;
import me.fagiolini.cinemapp.db.tables.pojos.Proiezione;
import me.fagiolini.cinemapp.db.tables.pojos.Sala;


import java.util.List;

@Singleton
public class SalaService {
    @Inject
    private SalaDao salaDao;
    @Inject
    private ProiezioneDao proiezioneDao;

    public Sala getSalaById(long id) {
        return this.salaDao.fetchOneById(id);
    }

    public List<Sala> getSale() {
        return this.salaDao.findAll();
    }

    public void save(Sala sala) {
        this.salaDao.insert(sala);
    }
    public void update(Sala sala) {
        this.salaDao.update(sala);
    }
    public void deleteById(long id) {
        this.salaDao.deleteById(id);
    }

    public List<Sala> getSalaByCinemaId(long cinemaId) {
        return this.salaDao.fetchByCinemaId(cinemaId);
    }

    public Sala getSalaByProiezioneId(long proiezioneId) {
        Proiezione proiezione = proiezioneDao.fetchOneById(proiezioneId);
        return this.salaDao.fetchOneById(proiezione.getSalaId());
    }
}
