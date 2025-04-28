package tech.wvs.movieflix2.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.wvs.movieflix2.config.JwtTokenService;
import tech.wvs.movieflix2.controller.dto.request.LoginRequest;
import tech.wvs.movieflix2.controller.dto.request.UserRequest;
import tech.wvs.movieflix2.controller.dto.response.LoginResponse;
import tech.wvs.movieflix2.domain.User;
import tech.wvs.movieflix2.service.UserService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenService jwtTokenService;

    public AuthController(UserService userService, AuthenticationManager authenticationManager, JwtTokenService jwtTokenService) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtTokenService = jwtTokenService;
    }

    //Rota pra registrar usuario
    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody UserRequest request) {
        userService.create(request);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    //Rota pra login
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        //aqui que amarra tudo

        try {
            UsernamePasswordAuthenticationToken userAndPassword = new
                    UsernamePasswordAuthenticationToken(request.email(), request.password());

            Authentication authenticate = authenticationManager.authenticate(userAndPassword);

            User user = (User) authenticate.getPrincipal();

            String token =  jwtTokenService.generateToken(user);

            return ResponseEntity.ok(new LoginResponse(token));
        } catch (
                BadCredentialsException e ){
            throw new BadCredentialsException("Usuario ou senha invalidos");
        }
    }
}
