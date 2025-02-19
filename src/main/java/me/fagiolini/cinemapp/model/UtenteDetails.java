package me.fagiolini.cinemapp.model;

import io.micronaut.serde.annotation.Serdeable;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Serdeable
public class UtenteDetails {
    private String nome;
    private String email;
}
