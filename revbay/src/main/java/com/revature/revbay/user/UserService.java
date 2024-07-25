package com.revature.revbay.user;

import com.revature.revbay.util.exceptions.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;

@Service
public class UserService  {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public User create(User newUser) {
        newUser.setUserType(User.UserType.valueOf("BUYER"));
        return userRepository.save(newUser);
    }


    public User findById(int userId) {
        return userRepository.findById(userId).orElseThrow(
                () -> new DataNotFoundException("No user found with that id")
        );
    }
    //TODO: Should these be in Serviceable??
    public boolean delete(User removedUser) {
        userRepository.delete(removedUser);
        return true;
    }

    public boolean update(User updatedUser) {
        userRepository.save(updatedUser);
        return true;
    }

    public User findByEmailAndPassword(String email, String password) throws AuthenticationException {
        return userRepository.findByEmailAndPassword(email, password).orElseThrow(
                () -> new AuthenticationException("Invalid credentials provided.")
        );
    }
}
