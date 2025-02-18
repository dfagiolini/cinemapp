package me.fagiolini.cinemapp.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import io.micronaut.http.HttpRequest;

public class JwtUtil {

    private static final String SECRET = "0fe55a1fa44aa5443955af076c0cdbe8883b6e573b33eedd63861097aab92c53328ae10f4a1a0234608c62fac99723a7fd5ed24554e0f86a9c77495b51559ed159db33f26ea8b93be604eaf6cdfab03bd196c1f8b07fb10baa0ebf51f91c3796553815501f5c5cecf688d3ca3fac649d7b32696e4c9b7d0ea4a0b174eb2877813e18ff884022a69175a76324e5a732fae17d42e34c807235b30dd550185306c28f5cba36f818d2300a463e5bcbe5dfe0f3945a5401c8c92b47e3d5d39cb1d5ad74e4ab59d755dd6cbe6fc27d5012030f5bfc4468f51a43fe410657ed153ed3508b366fc0a8429a73ab71d1b0bc00ef4ed6f6eb4f595a106e1f20fb84b8366892";

    public static DecodedJWT verifyToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();
            return verifier.verify(token);
        } catch (JWTVerificationException exception) {
            System.out.println(exception.getMessage());
            return null;
        }
    }

    public static int getRoleFromToken(String token) {
        DecodedJWT decodedJWT = verifyToken(token);
        if (decodedJWT != null) {
            return Integer.parseInt(decodedJWT.getClaim("role").toString().replace("\"",""));
        }
        return -1;
    }

    public static long getUserIdFromRequest(HttpRequest<?> request) {
        String authorizationHeader = request.getHeaders().get("Authorization");
        String token = authorizationHeader.substring(7);
        token = token.strip();
        DecodedJWT decodedJWT = verifyToken(token);
        if (decodedJWT != null) {
            return Long.parseLong(decodedJWT.getClaim("id").toString().replace("\"",""));
        }
        return -1;
    }
}
