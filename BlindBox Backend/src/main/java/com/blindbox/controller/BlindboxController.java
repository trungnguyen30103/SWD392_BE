package com.blindbox.controller;

import com.blindbox.model.Blindbox;
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

    // Get all blindboxes
    @Operation(summary = "Get all blindboxes", description = "Retrieve a list of all available blindboxes")
    @GetMapping
    public ResponseEntity<List<Blindbox>> getAllBlindboxes() {
        List<Blindbox> blindboxes = blindboxService.getAllBlindboxes();
        return new ResponseEntity<>(blindboxes, HttpStatus.OK);
    }

    // Get a blindbox by ID
    @Operation(summary = "Get a blindbox by ID", description = "Retrieve a single blindbox using its ID")
    @GetMapping("/{id}")
    public ResponseEntity<Blindbox> getBlindboxById(@PathVariable Integer id) {
        Blindbox blindbox = blindboxService.getBlindboxById(id);
        return new ResponseEntity<>(blindbox, HttpStatus.OK);
    }

    // Create a new blindbox
    @Operation(summary = "Create a new blindbox", description = "Add a new blindbox to the catalog")
    @PostMapping
    public ResponseEntity<Blindbox> createBlindbox(@RequestBody Blindbox blindbox) {
        Blindbox createdBlindbox = blindboxService.createBlindbox(blindbox);
        return new ResponseEntity<>(createdBlindbox, HttpStatus.CREATED);
    }

    // Update an existing blindbox
    @Operation(summary = "Update an existing blindbox", description = "Update an existing blindbox using its ID")
    @PutMapping("/{id}")
    public ResponseEntity<Blindbox> updateBlindbox(@PathVariable Integer id, @RequestBody Blindbox blindbox) {
        blindbox.setBlindboxID(id);
        Blindbox updatedBlindbox = blindboxService.updateBlindbox(blindbox);
        return new ResponseEntity<>(updatedBlindbox, HttpStatus.OK);
    }

    // Delete a blindbox by ID
    @Operation(summary = "Delete a blindbox by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBlindbox(@PathVariable Integer id) {
        blindboxService.deleteBlindbox(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
