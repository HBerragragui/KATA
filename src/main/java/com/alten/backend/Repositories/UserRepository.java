package com.alten.backend.Repositories;

import com.alten.backend.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAll();
    Optional<User> findByEmail(final String email);

    Optional<User> findByUsername(String username);
}
