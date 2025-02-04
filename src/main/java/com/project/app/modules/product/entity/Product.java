package com.project.app.modules.product.entity;


import com.project.app.modules.product.enums.ProductStatus;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "product")
public class Product {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Integer productId;

    @Column(name = "name")
    private String name;

    @Column(name = "mrp")
    private BigDecimal mrp;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ProductStatus status;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // Note: If you want to set things automatically!
    @PrePersist
    @PreUpdate
    public void updateDefault() {
        this.updatedAt = LocalDateTime.now();
        this.status = ProductStatus.INACTIVE;
    }

    // Use Constructor and Getters only for immutability
    public Product(Integer productId, String name, BigDecimal mrp, ProductStatus status, LocalDateTime updatedAt) {
        this.productId = productId;
        this.name = name;
        this.mrp = mrp;
        this.status = status;
        this.updatedAt = updatedAt;
    }

    public Product() {
    }

    public Integer getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getMrp() {
        return mrp;
    }

    public ProductStatus getStatus() {
        return status;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
