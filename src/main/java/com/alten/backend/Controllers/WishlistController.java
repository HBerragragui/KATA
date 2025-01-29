package com.alten.backend.Controllers;

import com.alten.backend.Models.Cart;
import com.alten.backend.Models.Product;
import com.alten.backend.Models.Wishlist;
import com.alten.backend.Services.CartService;
import com.alten.backend.Services.WishlistService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/wishlists")
public class WishlistController {
    private static final Logger log = LoggerFactory.getLogger(ProductContoller.class);
    @Autowired
    private WishlistService wishlistService;

    @GetMapping()
    public ResponseEntity<Wishlist> getWishlist() {
        return ResponseEntity.ok().body(wishlistService.getWishlist());
    }

    @PostMapping()
    public ResponseEntity<Wishlist> addWishlist(@RequestBody Product product) {
        wishlistService.addProductToWishlist(product);
        return ResponseEntity.ok().body(wishlistService.getWishlist());
    }

    @DeleteMapping()
    public ResponseEntity<Wishlist> removeWishlist(@RequestBody Product product) {
        wishlistService.removeProductFromWishlist(product);
        return ResponseEntity.ok().body(wishlistService.getWishlist());
    }
}
