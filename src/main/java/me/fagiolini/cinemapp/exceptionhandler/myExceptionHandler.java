package me.fagiolini.cinemapp.exceptionhandler;

import io.micronaut.context.annotation.Requires;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.server.exceptions.ExceptionHandler;
import jakarta.inject.Singleton;
import me.fagiolini.cinemapp.exception.myException;
@Produces
@Singleton
@Requires(classes = { myException.class, ExceptionHandler.class })

public class myExceptionHandler implements ExceptionHandler<myException, HttpResponse> {


    @Override
    public HttpResponse handle(HttpRequest request, myException exception) {
        return HttpResponse.serverError().body(exception.getMessage());
    }



}
