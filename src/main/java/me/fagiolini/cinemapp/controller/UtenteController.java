package me.fagiolini.cinemapp.controller;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.security.annotation.Secured;
import jakarta.inject.Inject;
import jakarta.validation.constraints.NotBlank;
import me.fagiolini.cinemapp.db.tables.pojos.Utente;
import me.fagiolini.cinemapp.exception.myException;
import me.fagiolini.cinemapp.model.LoginModel;
import me.fagiolini.cinemapp.model.UtenteDetails;
import me.fagiolini.cinemapp.service.UtenteService;
import io.micronaut.security.rules.SecurityRule;

import java.util.Optional;

@Controller
public class UtenteController {
    @Inject
    private UtenteService utenteService;
    @Secured(SecurityRule.IS_ANONYMOUS)
    @Post("/registrati")
    public void registraUtente(Utente utente) throws myException {
        this.utenteService.registra(utente);
    }
    @Secured(SecurityRule.IS_ANONYMOUS)
    @Post("/cinemapp/login")
    public HttpResponse<?> login(@Body LoginModel loginModel) throws myException {
        Optional<String> token = this.utenteService.login(loginModel.getUsername(), loginModel.getPassword());
        if (token.isPresent()) {
            String actualToken = token.get();
            return HttpResponse.ok(actualToken);
        } else {
            return HttpResponse.unauthorized();
        }

    }

    @Secured(SecurityRule.IS_AUTHENTICATED)
    @Post("/loginTest")
    public void loginTest() {}

    @Secured(SecurityRule.IS_AUTHENTICATED)
    @Get("/getMyDetails")
    public UtenteDetails getMyDetails(HttpRequest<?> request) {
        return this.utenteService.getDetailsFromRequest(request);

    }
}
