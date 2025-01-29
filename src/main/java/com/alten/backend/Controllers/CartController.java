package com.alten.backend.Controllers;

import com.alten.backend.Models.Cart;
import com.alten.backend.Models.Product;
import com.alten.backend.Services.CartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/carts")
public class CartController {
    private static final Logger log = LoggerFactory.getLogger(ProductContoller.class);
    @Autowired
    private CartService cartService;

    @GetMapping()
    public ResponseEntity<Cart> getCart() {
        return ResponseEntity.ok().body(cartService.getCart());
    }

    @PostMapping()
    public ResponseEntity<Cart> addProduct(@RequestBody Product product) {
        cartService.addProductToCart(product);
        return ResponseEntity.ok().body(cartService.getCart());
    }

    @DeleteMapping()
    public ResponseEntity<Cart> removeProduct(@RequestBody Product product) {
        cartService.removeProductFromCart(product);
        return ResponseEntity.ok().body(cartService.getCart());
    }
}
