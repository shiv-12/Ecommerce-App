package com.project.app.modules.inventory.controller;

import com.project.app.modules.inventory.dto.InventoryDTO;
import com.project.app.modules.inventory.enums.MovementDirection;
import com.project.app.modules.inventory.enums.MovementType;
import com.project.app.modules.inventory.factory.InventoryServiceFactory;
import com.project.app.modules.inventory.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inventory")
public class InventoryController {


    private final InventoryServiceFactory inventoryServiceFactory;

    @Autowired
    public InventoryController(InventoryServiceFactory inventoryServiceFactory) {
        this.inventoryServiceFactory = inventoryServiceFactory;
    }

    @GetMapping("/")
    public String getMessage() {
        return "Welcome to the Inventory Service!";
    }

    // Call this API after a product is purchased
    @PostMapping("/product-purchased")
    public void productPurchased(@RequestBody InventoryDTO inventoryDTO) {
        // Handle the product purchase logic
        InventoryService inventoryService = inventoryServiceFactory.getInventoryService(MovementType.PURCHASE);
        inventoryService.updateInventory(inventoryDTO);
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


/*

🔥 Key Best Practices
✔ Controller should be thin – no logic, only request handling.
✔ Service should be thick – all business logic happens here.
✔ Use DTOs for API communication, never expose Entities.
✔ Entities should only represent the database, not contain logic.
✔ Repositories should only interact with the database.

*/