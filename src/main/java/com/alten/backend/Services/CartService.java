package com.alten.backend.Services;

import com.alten.backend.Exceptions.ResourceNotFoundException;
import com.alten.backend.Models.Cart;
import com.alten.backend.Models.Product;
import com.alten.backend.Models.User;
import com.alten.backend.Repositories.CartRepository;
import com.alten.backend.Repositories.ProductRepository;
import com.alten.backend.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserService userService;

    public Cart getCart(){
        User user = userService.getAuthautifiedUser();
        return cartRepository.findByUser(user);
    }

    public void addProductToCart(Product product){
        Cart cart = this.getCart();
        Product productOptional = productRepository.findById(product.getId()).orElseThrow(() ->
                new ResourceNotFoundException("Product with ID " + product.getId() + " not found"));
        if (cart == null) {
            cart = new Cart();
            cart.setUser(userService.getAuthautifiedUser());
        }
        if (productOptional != null) {
            cart.getProducts().add(productOptional);
            cartRepository.save(cart);
        }
    }
    public void removeProductFromCart(Product product){
        Cart cart = this.getCart();
        if (cart == null) {
            throw new ResourceNotFoundException("Cart not found");
        }
        cart.getProducts().remove(product);
    }
}