package com.alten.backend.Services;

import com.alten.backend.Exceptions.ResourceNotFoundException;
import com.alten.backend.Models.User;
import com.alten.backend.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    public User getUserById(Long id) {
        return userRepository.findById(id).get();
    }
    public User addUser(User user) {
        return userRepository.save(user);
    }

    public boolean existsByEmail(String email) {

        if (userRepository.findByEmail(email).isPresent()) {
            return true;
        }
        return false;
    }

    public User getAuthautifiedUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication.getName());

        User user = userRepository.findByUsername((String) authentication.getName()).orElseThrow(() ->
                new ResourceNotFoundException("User not found"));
        return user;
    }
}
