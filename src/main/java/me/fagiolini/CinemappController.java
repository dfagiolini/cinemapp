package me.fagiolini;

import io.micronaut.http.annotation.*;

@Controller("/cinemapp")
public class CinemappController {

    @Get(uri="/", produces="text/plain")
    public String index() {
        return "Example Response";
    }

}