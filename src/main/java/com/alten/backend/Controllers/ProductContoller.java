package com.alten.backend.Controllers;

import com.alten.backend.Exceptions.ResourceNotFoundException;
import com.alten.backend.Models.Product;

import com.alten.backend.Services.ProductService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
@Tag(name = "Product Controller", description = "APIs for managing products")

public class ProductContoller {

    private static final Logger log = LoggerFactory.getLogger(ProductContoller.class);
    @Autowired
    private ProductService productService;


    @GetMapping
    @ResponseBody
    public List<Product> getProducts(){
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public Optional<Product> getProductById(@PathVariable Long id){
        return productService.getProductById(id);
    }

    @PostMapping()
    public Product addProduct(@RequestBody Product product){
        return productService.addProduct(product);
    }

    @PatchMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product product){
        return productService.updateProduct(id,product);

    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);

    }
}
