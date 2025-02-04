package com.project.app.modules.product.mapper;

import com.project.app.modules.product.dto.ProductRequestDTO;
import com.project.app.modules.product.dto.ProductResponseDTO;
import com.project.app.modules.product.entity.Product;
import org.springframework.stereotype.Component;


@Component
public class ProductMapper {

    // Note: Uses constructor-based mapping → Better for immutability.
    public Product toEntity(ProductRequestDTO productRequestDTO) {
        return new Product(
                null,
                productRequestDTO.getName(),
                productRequestDTO.getMrp(),
                null,
                null
        );
    }

    public ProductResponseDTO toDto(Product product) {
        return new ProductResponseDTO(
                product.getProductId(),
                product.getName(),
                product.getMrp(),
                product.getStatus(),
                product.getUpdatedAt()
        );
    }
}

