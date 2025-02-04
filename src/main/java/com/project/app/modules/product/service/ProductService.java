package com.project.app.modules.product.service;

import com.project.app.modules.product.dto.ProductRequestDTO;
import com.project.app.modules.product.dto.ProductResponseDTO;
import com.project.app.modules.product.entity.Product;
import com.project.app.modules.product.exception.ProductNotFoundException;
import com.project.app.modules.product.mapper.ProductMapper;
import com.project.app.modules.product.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Autowired
    public ProductService(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    // Note: Make sure to return responseDTO from the service layer not the entity
    public List<ProductResponseDTO> getProducts() {
        List<Product> products = productRepository.findAll();
        if (products.isEmpty())
            throw new ProductNotFoundException("No Products Found!");

        return products.stream().map(productMapper::toDto).toList();
    }

    @Transactional
    public ProductResponseDTO addProduct(ProductRequestDTO productRequestDTO) {

        // Note:
        // DTO Should be updated inside the Service, not in controller or not in mapper
        // Always Prefer Updating Default Values Inside the Entity class

        // Convert DTO to Entity
        Product product = productMapper.toEntity(productRequestDTO);

        // Save entity (updatedAt is set automatically in Entity)
        product = productRepository.save(product);

        // Convert Entity to Response DTO
        return productMapper.toDto(product);
    }
}
