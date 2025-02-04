package com.project.app.modules.product.dto;

import com.project.app.modules.product.enums.ProductStatus;

import java.math.BigDecimal;

public class ProductRequestDTO {

    private final String name;
    private final BigDecimal mrp;

    public ProductRequestDTO(BigDecimal mrp, String name) {
        this.mrp = mrp;
        this.name = name;
    }

    public BigDecimal getMrp() {
        return mrp;
    }

    public String getName() {
        return name;
    }
}
