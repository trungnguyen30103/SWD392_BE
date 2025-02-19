package com.blinkbox.model;

import java.util.Date;

public class Product {
    private Long productID;  // PK
    private String productName;
    private String description;
    private Long categoryID;  // FK
    private Double price;
    private Integer stock;
    private Date createdAt;
    private Date lastUpdated;

    // Constructors
    public Product() {}

    public Product(Long productID, String productName, String description, Long categoryID, Double price, Integer stock, Date createdAt, Date lastUpdated) {
        this.productID = productID;
        this.productName = productName;
        this.description = description;
        this.categoryID = categoryID;
        this.price = price;
        this.stock = stock;
        this.createdAt = createdAt;
        this.lastUpdated = lastUpdated;
    }

    // Getters and Setters
    public Long getProductID() {
        return productID;
    }

    public void setProductID(Long productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(Long categoryID) {
        this.categoryID = categoryID;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}
