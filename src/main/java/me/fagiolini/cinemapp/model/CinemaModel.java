package me.fagiolini.cinemapp.model;

import lombok.Data;

import java.util.Set;

@Data

public class CinemaModel {

    private Long id;
    private String indirizzo;
    private String nome;
    private String telefono;
    private Set<SalaModel> sale;
}
