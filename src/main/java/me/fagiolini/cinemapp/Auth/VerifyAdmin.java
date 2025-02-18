package me.fagiolini.cinemapp.Auth;

import io.micronaut.http.HttpRequest;
import me.fagiolini.cinemapp.exception.myException;
import me.fagiolini.cinemapp.utils.JwtUtil;

public class VerifyAdmin {
    public static boolean verify(HttpRequest<?> request) throws myException {
        String authorizationHeader = request.getHeaders().get("Authorization");
        String token = authorizationHeader.substring(7);
        token = token.strip();
        int role = JwtUtil.getRoleFromToken(token);
        if(role == 0)
            return true;
        else
            throw new myException("L'utente non ha i permessi per finalizzare quest'azione");
    }
}
