package com.project.app.modules.inventory.service;

import com.project.app.modules.inventory.annotation.InventoryType;
import com.project.app.modules.inventory.dto.InventoryDTO;
import com.project.app.modules.inventory.entity.Inventory;
import com.project.app.modules.inventory.entity.StockMovement;
import com.project.app.modules.inventory.enums.MovementDirection;
import com.project.app.modules.inventory.enums.MovementType;
import com.project.app.modules.inventory.repository.InventoryRepository;
import com.project.app.modules.inventory.repository.StockMovementRepository;
import com.project.app.modules.product.entity.Product;
import com.project.app.modules.product.exception.ProductNotFoundException;
import com.project.app.modules.product.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
@InventoryType(MovementType.PURCHASE)
public class PurchaseInventoryService implements InventoryService {

    private static final Logger log = LoggerFactory.getLogger(PurchaseInventoryService.class);
    private final InventoryRepository inventoryRepository;
    private final StockMovementRepository stockMovementRepository;
    private final ProductRepository productRepository;

    @Autowired
    public PurchaseInventoryService(InventoryRepository inventoryRepository,
                                    StockMovementRepository stockMovementRepository,
                                    ProductRepository productRepository) {
        this.inventoryRepository = inventoryRepository;
        this.stockMovementRepository = stockMovementRepository;
        this.productRepository = productRepository;
    }

    @Override
    @Transactional
    public void updateInventory(InventoryDTO inventoryDTO) {
        log.info("Updating inventory for product ID: {}", inventoryDTO.getProductId());

        // Fetch product or throw an exception
        Product product = productRepository.findById(inventoryDTO.getProductId())
                .orElseThrow(() -> new ProductNotFoundException("Product not found with ID: " + inventoryDTO.getProductId()));

        // Update or Create Inventory
        Inventory inventory = inventoryRepository.findByProduct(product)
                .orElseGet(() -> createNewInventory(product, inventoryDTO.getQuantity()));

        // Update existing inventory
        updateExistingInventory(inventory, inventoryDTO.getQuantity());

        // Save Stock Movement
        saveStockMovement(inventory, inventoryDTO);

        log.info("Inventory updated successfully for product ID: {}", inventoryDTO.getProductId());
    }

    private Inventory createNewInventory(Product product, int quantity) {
        log.info("Creating new inventory record for product ID: {}", product.getProductId());

        Inventory inventory = new Inventory();
        inventory.setProduct(product);
        inventory.setAvailableQty(quantity);
        inventory.setUpdatedAt(LocalDateTime.now());

        return inventoryRepository.save(inventory);
    }

    private void updateExistingInventory(Inventory inventory, int quantity) {
        log.info("Updating existing inventory for product ID: {}. Adding quantity: {}", inventory.getProduct().getProductId(), quantity);

        inventory.setAvailableQty(inventory.getAvailableQty() + quantity);
        inventory.setUpdatedAt(LocalDateTime.now());
        inventoryRepository.save(inventory);
    }

    private void saveStockMovement(Inventory inventory, InventoryDTO inventoryDTO) {
        log.info("Saving stock movement for product ID: {} with reference ID: {}", inventory.getProduct().getProductId(), inventoryDTO.getReferenceId());

        StockMovement stockMovement = new StockMovement();
        stockMovement.setInventory(inventory);
        stockMovement.setMovementType(MovementType.PURCHASE);
        stockMovement.setReferenceId(inventoryDTO.getReferenceId());
        stockMovement.setQuantity(inventoryDTO.getQuantity());
        stockMovement.setMovementDirection(MovementDirection.IN);
        stockMovement.setUpdatedAt(LocalDateTime.now());

        stockMovementRepository.save(stockMovement);
    }
}