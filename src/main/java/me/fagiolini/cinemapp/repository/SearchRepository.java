package me.fagiolini.cinemapp.repository;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import me.fagiolini.cinemapp.model.SearchModel;
import me.fagiolini.cinemapp.model.SearchResultModel;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static me.fagiolini.cinemapp.db.Tables.*;
import static me.fagiolini.cinemapp.db.Tables.SALA;

@Singleton
public class SearchRepository {
    @Inject
    private DSLContext create;

    public List<SearchResultModel>  search (SearchModel searchModel) {

        var query = this.create.select(FILM, PROIEZIONE, CINEMA, SALA).from(CINEMA, PROIEZIONE, FILM, SALA)
                .where(CINEMA.ID.eq(SALA.CINEMA_ID))
                .and(PROIEZIONE.FILM_ID.eq(FILM.ID))
                .and(PROIEZIONE.SALA_ID.eq(SALA.ID));

        //Per prevenire SQL Injection
        Set<String> allowedColumns = new HashSet<>();
        allowedColumns.add("cinema");
        allowedColumns.add("proiezione");
        allowedColumns.add("film");

        if (!allowedColumns.contains(searchModel.getOrderByColumn())) {
            return null;
        } else {
        if (searchModel != null) {
            if (searchModel.getFilmId() != null)
                query.and(FILM.ID.eq(searchModel.getFilmId()));
            if (searchModel.getCinemaId() != null)
                query.and(SALA.CINEMA_ID.eq(searchModel.getCinemaId()));
            if (searchModel.getOrario() != null)
                query.and(PROIEZIONE.DATA_ORA_INIZIO.greaterOrEqual(searchModel.getOrario()));
            if (searchModel.isAsc())
                query.orderBy(DSL.field(searchModel.getOrderByColumn()).asc());
            else if (!searchModel.isAsc()) {
                query.orderBy(DSL.field(searchModel.getOrderByColumn()).desc());

            }
        }
            return query.fetchInto(SearchResultModel.class);

        }


    }



}
