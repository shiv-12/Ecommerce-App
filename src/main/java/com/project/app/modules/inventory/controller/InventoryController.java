package com.project.app.modules.inventory.controller;

import com.project.app.modules.inventory.dto.InventoryDTO;
import com.project.app.modules.inventory.enums.MovementDirection;
import com.project.app.modules.inventory.enums.MovementType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inventory-update")
public class InventoryController {

    // Call this API after a product is purchased
    @PostMapping("/product-purchased")
    public void productPurchased(@RequestBody InventoryDTO inventoryDTO) {
        // Handle the product purchase logic
        inventoryDTO.setMovementType(MovementType.PURCHASE);
        inventoryDTO.setMovementDirection(MovementDirection.IN);

    }

    // Call this API after an order is placed
    @PostMapping("/order-placed")
    public void orderPlaced(@RequestBody InventoryDTO inventoryDTO) {
        // Handle the order placed logic
        inventoryDTO.setMovementType(MovementType.ORDER_PLACED);
        inventoryDTO.setMovementDirection(MovementDirection.OUT);
    }

    // Call this API after an order is canceled
    @PostMapping("/order-canceled")
    public void orderCanceled(@RequestBody InventoryDTO inventoryDTO) {
        // Handle the order canceled logic
        inventoryDTO.setMovementType(MovementType.ORDER_CANCELLED);
        inventoryDTO.setMovementDirection(MovementDirection.OUT);
    }

    // Call this API after an order is returned
    @PostMapping("/order-returned")
    public void orderReturned(@RequestBody InventoryDTO inventoryDTO) {
        // Handle the order returned logic
        inventoryDTO.setMovementType(MovementType.ORDER_RETURNED);
        inventoryDTO.setMovementDirection(MovementDirection.OUT);
    }

    // Call this API when a user wants to adjust the stock
    @PostMapping("/adjust-stock")
    public void adjustStock(@RequestBody InventoryDTO inventoryDTO) {
        // Handle the stock adjustment logic

        if (inventoryDTO.getMovementDirection() == null) {
            throw new RuntimeException("Movement direction is required!");
        }

        if (inventoryDTO.getMovementDirection() != MovementDirection.IN && inventoryDTO.getMovementDirection() != MovementDirection.OUT) {
            throw new RuntimeException("Invalid Movement direction!");
        }


        inventoryDTO.setMovementType(MovementType.STOCK_ADJUSTMENT);

    }
}
