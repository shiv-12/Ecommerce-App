package com.project.app.modules.inventory.dto;

import com.project.app.modules.inventory.enums.MovementType;
import com.project.app.modules.inventory.enums.MovementDirection;

public class InventoryDTO {

    private Integer productId;      // The product being affected
    private Integer referenceId;    // Order ID, Purchase ID, or Return ID
    private Integer quantity;       // Transaction quantity

    // These will be set dynamically based on the API Type
    private MovementType movementType;
    private MovementDirection movementDirection;

    // Getters and setters
    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(Integer referenceId) {
        this.referenceId = referenceId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public MovementType getMovementType() {
        return movementType;
    }

    public void setMovementType(MovementType movementType) {
        this.movementType = movementType;
    }

    public MovementDirection getMovementDirection() {
        return movementDirection;
    }

    public void setMovementDirection(MovementDirection movementDirection) {
        this.movementDirection = movementDirection;
    }
}
