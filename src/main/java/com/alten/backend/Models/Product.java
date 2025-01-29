package com.alten.backend.Models;

import jakarta.persistence.*;


import java.time.Instant;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String code;

    @Column
    private String name;

    @Column
    private String description;

    private String image;

    @Column
    private String category;

    @Column
    private Double price;

    @Column
    private Integer quantity;

    @Column(name = "internal_reference")
    private String internalReference;

    @Column(name = "shell_id")
    private Long shellId;

    @Enumerated(EnumType.STRING)
    @Column(name = "inventory_status")
    private InventoryStatus inventoryStatus;

    private Integer rating;




    public enum InventoryStatus {
        INSTOCK,
        LOWSTOCK,
        OUTOFSTOCK
    }

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant CreatedAt;

    @Column(name = "updated_at", nullable = false, updatable = true)
    private  Instant UpdatedAt;

    @PrePersist
    protected void onCreate() {
        CreatedAt = Instant.now();
        UpdatedAt = Instant.now();
    }

    @PreUpdate
    protected void onUpdate() {
        UpdatedAt = Instant.now();
    }

    public Product() {
    }
    public void UpdateProduct(Product product){
        this.code = product.getCode();
        this.name = product.getName();
        this.description = product.getDescription();
        this.image = product.getImage();
        this.category = product.getCategory();
        this.price = product.getPrice();
        this.quantity = product.getQuantity();
        this.internalReference = product.getInternalReference();
        this.inventoryStatus = product.getInventoryStatus();
        this.rating = product.getRating();

    }

    public Product(String code, String name, String description, String image, String category, Double price, Integer quantity, String internalReference, Long shellId, InventoryStatus inventoryStatus, Integer rating) {
        this.code = code;
        this.name = name;
        this.description = description;
        this.image = image;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
        this.internalReference = internalReference;
        this.shellId = shellId;
        this.inventoryStatus = inventoryStatus;
        this.rating = rating;
    }

    public Product(String code, String name, String description, String image, String category, Double price, Integer quantity, String internalReference, Long shellId, String inventoryStatus, Integer rating) {
        this.code = code;
        this.name = name;
        this.description = description;
        this.image = image;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
        this.internalReference = internalReference;
        this.shellId = shellId;
        this.inventoryStatus = InventoryStatus.valueOf(inventoryStatus);
        this.rating = rating;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getInternalReference() {
        return internalReference;
    }

    public void setInternalReference(String internalReference) {
        this.internalReference = internalReference;
    }

    public Long getShellId() {
        return shellId;
    }

    public void setShellId(Long shellId) {
        this.shellId = shellId;
    }

    public InventoryStatus getInventoryStatus() {
        return inventoryStatus;
    }

    public void setInventoryStatus(InventoryStatus inventoryStatus) {
        this.inventoryStatus = inventoryStatus;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Instant getCreatedAt() {
        return CreatedAt;
    }

    public void setCreatedAt(Instant createdAt) {
        CreatedAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return UpdatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        UpdatedAt = updatedAt;
    }
}