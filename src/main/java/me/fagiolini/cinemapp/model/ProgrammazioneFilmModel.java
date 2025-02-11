package me.fagiolini.cinemapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import me.fagiolini.cinemapp.db.tables.pojos.Cinema;
import me.fagiolini.cinemapp.db.tables.pojos.Proiezione;

import java.util.List;

@Data
@AllArgsConstructor
public class ProgrammazioneFilmModel {
    private Cinema cinema;
    private List<Proiezione> proiezioni;
}
