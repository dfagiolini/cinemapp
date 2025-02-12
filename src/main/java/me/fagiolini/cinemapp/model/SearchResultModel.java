package me.fagiolini.cinemapp.model;

import io.micronaut.serde.annotation.Serdeable;
import lombok.AllArgsConstructor;
import lombok.Data;
import me.fagiolini.cinemapp.db.tables.pojos.Cinema;
import me.fagiolini.cinemapp.db.tables.pojos.Film;
import me.fagiolini.cinemapp.db.tables.pojos.Proiezione;
import me.fagiolini.cinemapp.db.tables.pojos.Sala;
@Data
@AllArgsConstructor
@Serdeable
public class SearchResultModel {
    private Film film;
    private Proiezione proiezione;
    private Cinema cinema;
    public Sala sala;
}
