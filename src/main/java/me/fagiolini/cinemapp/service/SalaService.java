package me.fagiolini.cinemapp.service;


import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import me.fagiolini.cinemapp.db.tables.daos.ProiezioneDao;
import me.fagiolini.cinemapp.db.tables.pojos.Proiezione;
import me.fagiolini.cinemapp.db.tables.pojos.Sala;
import me.fagiolini.cinemapp.repository.ProiezioneRepository;
import me.fagiolini.cinemapp.repository.SalaRepository;


import java.util.List;

@Singleton
public class SalaService {
    @Inject
    private SalaRepository salaRepository;
    @Inject
    private ProiezioneRepository proiezioneRepository;

    public Sala getSalaById(long id) {
        return this.salaRepository.getSalaById(id);
    }

    public List<Sala> getSale() {
        return this.salaRepository.getAllSala();
    }

    public void save(Sala sala) {
        this.salaRepository.insertSala(sala);
    }
    public void update(Sala sala) {
        this.salaRepository.updateSala(sala);
    }
    public void deleteById(long id) {
        this.salaRepository.deleteSala(id);
    }

    public List<Sala> getSalaByCinemaId(long cinemaId) {
        return this.salaRepository.getSalaByCinemaId(cinemaId);
    }

    public Sala getSalaByProiezioneId(long proiezioneId) {
        Proiezione proiezione = proiezioneRepository.getProiezioneById(proiezioneId);
        return this.salaRepository.getSalaById(proiezione.getSalaId());
    }
}
