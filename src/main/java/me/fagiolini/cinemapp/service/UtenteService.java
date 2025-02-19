package me.fagiolini.cinemapp.service;

import io.micronaut.http.HttpRequest;
import io.micronaut.security.token.jwt.generator.JwtTokenGenerator;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import me.fagiolini.cinemapp.db.tables.pojos.Utente;
import me.fagiolini.cinemapp.db.tables.daos.UtenteDao;
import me.fagiolini.cinemapp.exception.myException;
import me.fagiolini.cinemapp.model.UtenteDetails;
import me.fagiolini.cinemapp.repository.UtenteRepository;

import io.micronaut.security.token.jwt.generator.JwtTokenGenerator;
import me.fagiolini.cinemapp.utils.JwtUtil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


@Singleton
public class UtenteService {
    @Inject
    private UtenteRepository utenteRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenGenerator jwtTokenGenerator;

    public UtenteService(JwtTokenGenerator jwtTokenGenerator) {
        this.passwordEncoder = new BCryptPasswordEncoder();
        this.jwtTokenGenerator = jwtTokenGenerator;
    }

    public void registra(@NotNull Utente utente) throws myException {
        String hashedPassword = passwordEncoder.encode(utente.getPassword());
        utente.setPassword(hashedPassword);
        utenteRepository.save(utente);
        if (utenteRepository.findByNome(utente.getNome()) == null) {
            throw new myException("Utente già presente!");
        }
    }

    public Optional<String> login(@NotBlank String username, @NotBlank String password) throws myException {
        Utente utente = utenteRepository.findByNome(username);
        if (utente != null && passwordEncoder.matches(password, utente.getPassword())) {
            return generateToken(utente);
        }
        else {
            throw new myException("Login fallito!");

        }
    }

    private Optional<String> generateToken(Utente utente) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("sub", String.valueOf(utente.getId()));
        claims.put("role", String.valueOf(utente.getRuolo()));


        return jwtTokenGenerator.generateToken(claims);
    }

    public UtenteDetails getDetailsFromRequest(HttpRequest<?> request) {
        Utente u = this.utenteRepository.findById(JwtUtil.getUserIdFromRequest(request));
        UtenteDetails uDetails = new UtenteDetails(u.getNome(), u.getEmail());
        return uDetails;


    }

}
