package com.alten.backend.Services;

import com.alten.backend.Exceptions.ResourceNotFoundException;
import com.alten.backend.Models.Product;
import com.alten.backend.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
    public Optional<Product> getProductById(Long id) {

        return Optional.ofNullable(productRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Product with ID " + id + " not found")));

    }
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }
    public Product updateProduct(Long id, Product product) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product with ID " + id + " not found"));
        existingProduct.UpdateProduct(product);
        return productRepository.save(existingProduct);
    }
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

}
