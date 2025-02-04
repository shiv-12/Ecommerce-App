package com.project.app.modules.inventory.annotation;

import com.project.app.modules.inventory.enums.MovementType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)     // Annotation will be available while the program is running.
@Target(ElementType.TYPE)               // It can only be applied to classes or interfaces, not methods or fields.
public @interface InventoryType {
    MovementType value();               // It has one property (value()), which must be assigned an enum of type MovementType.
    // String description() default ""; // You can add a description if needed.
}

/*

Usage:

@Service
@InventoryType(value = MovementType.STOCK_ADJUSTMENT, description = "Handles stock adjustments")
public class AdjustmentInventoryService implements InventoryService {
    // Implementation
}

Now, if we have a list of InventoryService instances,
we can determine their type using the @InventoryType annotation:

InventoryType annotation = purchaseService.getClass().getAnnotation(InventoryType.class);

We can then retrieve its value using:
MovementType type = annotation.value();
// Optionally, we can retrieve the description if defined:
String description = annotation.description();  // If 'description' is provided in the annotation.

*/
