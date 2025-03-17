package com.blindbox.controller;

import com.blindbox.model.Discount;
import com.blindbox.service.DiscountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Discount Management System", description = "Operations pertaining to discounts in the Discount Management System")
@RestController
@RequestMapping("/api/discounts")
public class DiscountController {

    @Autowired
    private DiscountService discountService;

    // Get all discounts
    @Operation(summary = "Get all discounts", description = "Retrieve a list of all available discounts")
    @GetMapping
    public List<Discount> getAllDiscounts() {
        return discountService.getAllDiscounts();
    }

    // Get a single discount by ID
    @Operation(summary = "Get a discount by ID", description = "Retrieve a single discount using its ID")
    @GetMapping("/{id}")
    public ResponseEntity<Discount> getDiscountById(@PathVariable("id") Integer id) {
        Discount discount = discountService.getDiscountById(id);
        if (discount == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(discount, HttpStatus.OK);
    }

    // Create a new discount
    @Operation(summary = "Create a new discount", description = "Add a new discount to the system")
    @PostMapping
    public ResponseEntity<Discount> createDiscount(@RequestBody Discount discount) {
        Discount createdDiscount = discountService.createDiscount(discount);
        return new ResponseEntity<>(createdDiscount, HttpStatus.CREATED);
    }

    // Update an existing discount
    @Operation(summary = "Update an existing discount", description = "Update an existing discount using its ID")
    @PutMapping("/{id}")
    public ResponseEntity<Discount> updateDiscount(@PathVariable("id") Integer id, @RequestBody Discount discount) {
        Discount updatedDiscount = discountService.updateDiscount(id, discount);
        if (updatedDiscount == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedDiscount, HttpStatus.OK);
    }

    // Delete a discount
    @Operation(summary = "Delete a discount by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDiscount(@PathVariable("id") Integer id) {
        if (!discountService.deleteDiscount(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
