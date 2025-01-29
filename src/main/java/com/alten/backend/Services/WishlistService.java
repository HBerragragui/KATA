package com.alten.backend.Services;

import com.alten.backend.Exceptions.ResourceNotFoundException;
import com.alten.backend.Models.Cart;
import com.alten.backend.Models.Product;
import com.alten.backend.Models.User;
import com.alten.backend.Models.Wishlist;
import com.alten.backend.Repositories.ProductRepository;
import com.alten.backend.Repositories.WishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WishlistService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private WishlistRepository wishlistRepository;

    public Wishlist getWishlist(){
        User user = userService.getAuthautifiedUser();
        return wishlistRepository.findByUser(user);
    }

    public void addProductToWishlist(Product product){
        Wishlist wishlist = this.getWishlist();
        Product productOptional = productRepository.findById(product.getId()).orElseThrow(() ->
                new ResourceNotFoundException("Product with ID " + product.getId() + " not found"));
        if (wishlist == null) {
            wishlist = new Wishlist();
            wishlist.setUser(userService.getAuthautifiedUser());
        }
        if (productOptional != null) {
            wishlist.getProducts().add(productOptional);
            wishlistRepository.save(wishlist);
        }
    }
    public void removeProductFromWishlist(Product product){
        Wishlist wishlist = this.getWishlist();
        if (wishlist == null) {
            throw new ResourceNotFoundException("Wishlist not found");
        }
        wishlist.getProducts().remove(product);
    }
}
