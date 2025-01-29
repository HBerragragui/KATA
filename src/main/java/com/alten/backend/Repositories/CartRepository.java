package com.alten.backend.Repositories;

import com.alten.backend.Models.Cart;
import com.alten.backend.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Cart findByUser(User user);
}
