package me.fagiolini.cinemapp.model;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;
import lombok.AllArgsConstructor;
import lombok.Data;
import me.fagiolini.cinemapp.db.tables.pojos.Cinema;
import me.fagiolini.cinemapp.db.tables.pojos.Film;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Serdeable
public class SearchModel {
    private Long filmId;
    private Long cinemaId;
    private LocalDateTime orario;
    private boolean isAsc;
    private String orderByColumn;


}
