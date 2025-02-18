package me.fagiolini.cinemapp.model;

import io.micronaut.serde.annotation.Serdeable;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Serdeable
@AllArgsConstructor
public class LoginModel {
    private String username;
    private String password;
}
