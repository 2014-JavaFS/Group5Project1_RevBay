package com.revature.revbay.util.auth;

import com.revature.revbay.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.security.sasl.AuthenticationException;

@CrossOrigin(exposedHeaders = {"userId", "userType"})
@RestController
@RequestMapping("/auth")
public class AuthController  {
    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping
    private ResponseEntity<Void> postLogin(@RequestParam String email, @RequestParam String password) throws AuthenticationException {
        User user = authService.login(email, password);

        return ResponseEntity.noContent()
                .header("userId", String.valueOf(user.getUserId()))
                .header("userType", String.valueOf(user.getUserType()))
                .build();
    }

    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String handleAuthenticationException(AuthenticationException ae){
        return ae.getMessage();
    }
}

