package tech.wvs.movieflix2.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Component;
import tech.wvs.movieflix2.config.dto.JWTUserData;
import tech.wvs.movieflix2.domain.User;

import java.time.Instant;
import java.util.Optional;

@Component
public class JwtTokenService {

    // REFACTOR: colocar a secret em variavel de ambiente
    private final String secret = "secretKey";

    //metodo que geta o token
    public String generateToken(User user) {
        Algorithm algorithm = Algorithm.HMAC256(secret);

        return JWT.create()
                .withSubject(user.getEmail())
                .withClaim("id", user.getId())
                .withClaim("name", user.getName())
                .withIssuedAt(Instant.now())
                .withExpiresAt(Instant.now().plusSeconds(3600))
                .withIssuer("MovieFlix - API")
                .sign(algorithm);
    }


    //metodo que valida o token
    public Optional<JWTUserData> validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            DecodedJWT jwt = JWT.require(algorithm)
                    .build()
                    .verify(token);

            return Optional.of(new JWTUserData(
                    //id
                    (jwt.getClaim("id").asLong()),
                    //name
                    (jwt.getClaim("name").asString()),
                    //email
                    (jwt.getSubject())
            ));
        } catch (
                JWTVerificationException e) {
            return Optional.empty();
        }
    }
}
