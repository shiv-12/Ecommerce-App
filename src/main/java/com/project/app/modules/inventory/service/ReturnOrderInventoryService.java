package com.project.app.modules.inventory.service;

import com.project.app.modules.inventory.annotation.InventoryType;
import com.project.app.modules.inventory.dto.InventoryDTO;
import com.project.app.modules.inventory.enums.MovementType;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@InventoryType(MovementType.ORDER_RETURNED)
public class ReturnOrderInventoryService implements InventoryService {
    @Override
    @Transactional
    public void updateInventory(InventoryDTO inventoryDTO) {

    }
}
