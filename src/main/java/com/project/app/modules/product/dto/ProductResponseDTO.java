package com.project.app.modules.product.dto;

import com.project.app.modules.product.enums.ProductStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ProductResponseDTO {

    // Note: Field Name should be the same as product entity names
    private final Integer productId;
    private final String name;
    private final BigDecimal mrp;
    private final ProductStatus status;
    private final LocalDateTime updatedAt;

    public ProductResponseDTO(Integer productId, String name, BigDecimal mrp, ProductStatus status, LocalDateTime updatedAt) {
        this.productId = productId;
        this.name = name;
        this.mrp = mrp;
        this.status = status;
        this.updatedAt = updatedAt;
    }

    public Integer getProductId() {
        return productId;
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

    public String getName() {
        return name;
    }
}
