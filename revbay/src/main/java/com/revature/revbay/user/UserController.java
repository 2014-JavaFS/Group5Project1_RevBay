package com.revature.revbay.user;

import com.revature.revbay.products.Products;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        return ResponseEntity.status(200).body(userService.findAll());
    }

    @PostMapping("/signup")
    private ResponseEntity<User> postNewUser(@RequestBody User user) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userService.create(user));
    }

    @GetMapping("/{userId}")
    private ResponseEntity<User> getUserById(@PathVariable int userId) {
        return ResponseEntity.ok(userService.findById(userId));
    }

    @DeleteMapping
    private ResponseEntity<Boolean> deleteUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.delete(user));
    }

    @PutMapping
    private ResponseEntity<Void> putUpdateUser(@RequestBody User user) {
        userService.update(user);
        return ResponseEntity.noContent().build();
    }

    private int loggedInCheck() {
      return -1;
    }
}
