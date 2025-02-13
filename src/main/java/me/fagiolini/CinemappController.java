package me.fagiolini;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import io.micronaut.http.annotation.Error;
import me.fagiolini.cinemapp.exception.myException;

@Controller("/cinemapp")
public class CinemappController {

    @Get(uri="/", produces="text/plain")
    public String index() {
        return "Example Response";
    }



}