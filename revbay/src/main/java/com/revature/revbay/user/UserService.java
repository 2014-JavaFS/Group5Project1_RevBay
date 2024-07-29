package com.revature.revbay.user;

import com.revature.revbay.products.Products;
import com.revature.revbay.util.exceptions.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import java.util.List;

@Service
public class UserService  {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public User create(User newUser) {
        return userRepository.save(newUser);
    }
    public List<User> findAll(){
        List<User> users = userRepository.findAll();
        if(users.isEmpty()){
            throw new DataNotFoundException("No product information found");
        }else {
            return users;
        }
    }

    public User findById(int userId) {
        return userRepository.findById(userId).orElseThrow(
                () -> new DataNotFoundException("No user found with that id")
        );
    }

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

