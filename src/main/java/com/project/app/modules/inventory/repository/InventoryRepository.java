package com.project.app.modules.inventory.repository;

import com.project.app.modules.inventory.entity.Inventory;
import com.project.app.modules.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Integer> {
    Optional<Inventory> findByProduct(Product product);  // Find by the full Product entity
}
