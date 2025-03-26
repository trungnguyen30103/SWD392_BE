package com.blindbox.controller;

import com.blindbox.model.Blindbox;
import com.blindbox.request.Create.Blindbox.BlindboxCreateRequest;
import com.blindbox.request.Update.Blindbox.BlindboxUpdateRequest;
import com.blindbox.response.ResponseData;
import com.blindbox.service.BlindboxService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Blindbox Management System", description = "Operations pertaining to blindboxes in the Blindbox Management System")
@RestController
@RequestMapping("/api/blindboxes")
public class BlindboxController {

    private final BlindboxService blindboxService;

    @Autowired
    public BlindboxController(BlindboxService blindboxService) {
        this.blindboxService = blindboxService;
    }

    /* Blindbox
    * */

    // Create a new blindbox
    @Operation(summary = "Create a new blindbox", description = "Add a new blindbox to the catalog")
    @PostMapping
    public ResponseEntity<ResponseData> createBlindbox(@RequestBody BlindboxCreateRequest request) {
        try {
            Blindbox createdBlindbox = blindboxService.createBlindbox(request);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ResponseData(201, true, "Blindbox created successfully", createdBlindbox, null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseData(500, false, "Failed to create blindbox", null, null));
        }
    }

    // Update an existing blindbox
    @Operation(summary = "Update an existing blindbox", description = "Update an existing blindbox using its ID")
    @PutMapping("/{blindboxID}")
    public ResponseEntity<ResponseData> updateBlindbox(@PathVariable Integer blindboxID, @RequestBody BlindboxUpdateRequest request) {
        try {
            Blindbox updatedBlindbox = blindboxService.updateBlindbox(blindboxID, request);
            return ResponseEntity.ok(new ResponseData(200, true, "Blindbox updated successfully", updatedBlindbox, null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseData(500, false, "Failed to update blindbox: " + e.getMessage(), null, null));
        }
    }

    // Delete a blindbox by ID
    @Operation(summary = "Disable a blindbox by ID", description = "Disable a blindbox along with its item(s) using its ID")
    @DeleteMapping("/{blindboxID}")
    public ResponseEntity<ResponseData> deleteBlindbox(@PathVariable Integer blindboxID) {
        blindboxService.deleteBlindbox(blindboxID);
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body(new ResponseData(204, true, "Blindbox deleted", null,null));
    }

    // Get all blindboxes
    @Operation(summary = "Get all blindboxes", description = "Retrieve a list of all available blindboxes")
    @GetMapping
    public ResponseEntity<ResponseData> getAllBlindboxes() {
        try {
            List<Blindbox> blindboxes = blindboxService.getAllBlindboxes();
            return ResponseEntity.ok(new ResponseData(200, true, "Blindboxes retrieved successfully", blindboxes, null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseData(500, false, "Failed to retrieve blindboxes", null, null));
        }
    }

    // Get a blindbox by ID
    @Operation(summary = "Get a blindbox by ID", description = "Retrieve a single blindbox using its ID")
    @GetMapping("/{blindboxID}")
    public ResponseEntity<ResponseData> getBlindboxById(@PathVariable Integer blindboxID) {
        try {
            Blindbox blindbox = blindboxService.getBlindboxById(blindboxID);
            System.out.println("✅ Fetch Successful: " + blindbox);
            return ResponseEntity.ok(new ResponseData(200, true, "Blindbox retrieved successfully", blindbox, null));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseData(404, false, "Blindbox not found: " + e.getMessage(), null, null));
        }
    }

    // Get blindboxes by name
    @Operation(summary = "Get a blindboxes by name", description = "Search for blindboxes using name")
    @GetMapping("/search")
    public ResponseEntity<ResponseData> searchBlindboxesByName(@RequestParam String name) {
        try {
            List<Blindbox> blindboxes = blindboxService.getBlindboxByName(name);
            if (blindboxes.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ResponseData(404, false, "There is no blindbox with name '" + name + "'.", null, null));
            }
            return ResponseEntity.ok(new ResponseData(200, true, "Blindbox(es) found", blindboxes, null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseData(500, false, "Failed to search blindboxes", null, null));
        }
    }

    @Operation(summary = "Get blindboxes by category", description = "Retrieve blindboxes by category ID")
    @GetMapping("/category/{categoryID}")
    public ResponseEntity<ResponseData> getBlindboxesByCategory(@PathVariable Integer categoryID) {
        try {
            List<Blindbox> blindboxes = blindboxService.getBlindboxByCategory(categoryID);
            if (blindboxes.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ResponseData(404, false, "No blindboxes found for category ID: " + categoryID, null, null));
            }
            return ResponseEntity.ok(new ResponseData(200, true, "Blindboxes retrieved successfully", blindboxes, null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseData(500, false, "Failed to retrieve blindboxes", null, null));
        }
    }

    /* Blindbox Image
    * */

    // Delete an image by ID
    @Operation(summary = "Delete an image by ID")
    @DeleteMapping("/{blindboxID}/blindbox-images/{imageID}")
    public ResponseEntity<Void> deleteImage(@PathVariable Integer blindboxID, @PathVariable Integer imageID) {
        blindboxService.deleteImage(blindboxID, imageID);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /* Blindbox Item
    * */

    // Delete an item by ID
    @Operation(summary = "Disable an item by blindboxID and blindbox_item_id")
    @DeleteMapping("/{blindboxID}/blindbox-items/{itemID}")
    public ResponseEntity<Void> deleteItem(@PathVariable Integer blindboxID, @PathVariable Integer itemID) {
        blindboxService.deleteItem(blindboxID, itemID);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
