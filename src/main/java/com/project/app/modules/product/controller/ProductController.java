package com.project.app.modules.product.controller;

import com.project.app.modules.product.dto.ProductRequestDTO;
import com.project.app.modules.product.dto.ProductResponseDTO;
import com.project.app.modules.product.entity.Product;
import com.project.app.modules.product.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public String getMessage() {
        return "Welcome to the product service!";
    }

    @GetMapping()
    public ResponseEntity<List<ProductResponseDTO>> getProducts() {
        return ResponseEntity.status(HttpStatus.OK).body(productService.getProducts());
    }

    @PostMapping
    public ResponseEntity<ProductResponseDTO> addProduct(@RequestBody ProductRequestDTO productRequestDTO) {
        ProductResponseDTO productResponseDTO = productService.addProduct(productRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(productResponseDTO);
    }
}


/*

Note:

✔ Always use RequestDTO for requests (except for simple GET/DELETE).
✔ Never return ResponseDTO or directly expose the entity.
✔ The controller should never receive or return an entity.

🔥 Key Best Practices
✔ Controller should be thin – no logic, only request handling.
✔ Service should be thick – all business logic happens here.
✔ Use DTOs for API communication, never expose Entities.
✔ Entities should only represent the database, not contain logic.
✔ Repositories should only interact with the database.


1️⃣ Controller (API Layer)
📌 Handles HTTP requests and responses.
✅ Accepts DTO from API requests.
✅ Calls the Service layer for business logic.
✅ Returns DTO wrapped in ResponseEntity.
❌ No Business Logic
❌ No Database Queries
❌ No Entity Modification

2️⃣ Service (Business Logic Layer)
📌 Contains all business logic and transactions.
✅ Fetches Entity from Repository.
✅ Updates Entity based on DTO.
✅ Saves the modified Entity.
✅ Returns DTO to Controller.
❌ No Direct Database Queries (uses Repository).
❌ No Request Handling (Controller’s job).
❌ No Exposing Entities to Controller.

3️⃣ DTO (Data Transfer Object)
📌 Transfers data between API and Service Layer.
✅ Defines request/response structure.
✅ Ensures API data consistency.
✅ Uses validation annotations (@NotNull, @Min).
❌ No Business Logic
❌ No Database Operations
❌ No Entity Annotations (@Entity, @Table)

4️⃣ Entity (Database Representation)
📌 Represents database tables and relationships.
✅ Defines structure with @Entity, @Table, @Column.
✅ Use @OneToMany, @ManyToOne for relations.
✅ Implements helper methods (e.g., activate()).
❌ No Business Logic (except minor state changes).
❌ No Request Validation (@Valid goes in DTO).
❌ No Direct Exposure to API.

5️⃣ Repository (Database Access Layer)
📌 Performs database operations.
✅ Fetches, saves, deletes entities (findById(), save()).
✅ Uses @Query for custom JPQL/SQL queries.
✅ Supports pagination and sorting (Pageable).
❌ No Business Logic
❌ No Data Transformation
❌ No Direct API Exposure

*/