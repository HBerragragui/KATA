package com.alten.backend.Repositories;

import com.alten.backend.Models.User;
import com.alten.backend.Models.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WishlistRepository extends JpaRepository<Wishlist, Long> {
    Wishlist findByUser(User user);
}
