package com.revature.revbay.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    //TODO: Products will need this method too. Change to interface later.
    public User create(User newUser) {
        newUser.setUserType("BUYER");
        return userRepository.create(newUser);
    }

    //TODO: And this one...
    public User findById(int userId) {
        return userRepository.findById(userId);
    }

    //TODO: And this one...
    public boolean delete(User removedUser) {
        return userRepository.delete(removedUser);
    }

    //TODO: Maybe this one too...
    public boolean update(User updatedUser) {
        return userRepository.update(updatedUser);
    }

    public User findByEmailAndPassword(String email, String password){
        return userRepository.findByEmailAndPassword(email, password);
    }

}
