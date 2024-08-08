package com.revature.revbay.util.auth;

import com.revature.revbay.user.User;
import com.revature.revbay.user.UserService;
import org.springframework.stereotype.Service;
import javax.security.sasl.AuthenticationException;

@Service
public class AuthService {
    private final UserService userService;

    public AuthService(UserService userService) {
        this.userService = userService;
    }

    public User login(String email, String password) throws AuthenticationException {
        User user = userService.findByEmailAndPassword(email, password);

        if(user == null) throw new AuthenticationException("Invalid credentials, please try again");
        return user;
    }
}
