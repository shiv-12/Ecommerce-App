package com.project.app.modules.inventory.factory;

import com.project.app.modules.inventory.annotation.InventoryType;
import com.project.app.modules.inventory.enums.MovementType;
import com.project.app.modules.inventory.exception.InvalidMovementTypeException;
import com.project.app.modules.inventory.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class InventoryServiceFactory {

    private final Map<MovementType, InventoryService> serviceMap = new HashMap<>();

    @Autowired
    public InventoryServiceFactory(List<InventoryService> services) {
        for (InventoryService service : services) {
            InventoryType annotation = service.getClass().getAnnotation(InventoryType.class);
            if (annotation != null) {
                serviceMap.put(annotation.value(), service);
            }
        }
    }

    public InventoryService getInventoryService(MovementType movementType) {
        InventoryService service = serviceMap.get(movementType);
        if (service != null) {
            return service;
        } else {
            throw new InvalidMovementTypeException("Invalid Movement Type: " + movementType);
        }
    }
}
