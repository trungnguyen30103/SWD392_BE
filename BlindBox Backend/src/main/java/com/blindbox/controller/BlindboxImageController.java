package com.blindbox.controller;

import com.blindbox.model.BlindboxImage;
import com.blindbox.service.BlindboxImageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Blind Box Image Management System", description = "Operations pertaining to images in the Blind Box Image Management System")
@RestController
@RequestMapping("/api/blindbox-images")
public class BlindboxImageController {

    private final BlindboxImageService blindboxImageService;

    @Autowired
    public BlindboxImageController(BlindboxImageService blindboxImageService) {
        this.blindboxImageService = blindboxImageService;
    }

    // Get all images
    @Operation(summary = "Get all images", description = "Retrieve a list of all available images")
    @GetMapping
    public ResponseEntity<List<BlindboxImage>> getAllImages() {
        List<BlindboxImage> images = blindboxImageService.getAllImages();
        return new ResponseEntity<>(images, HttpStatus.OK);
    }

    // Get an image by ID
    @Operation(summary = "Get an image by ID", description = "Retrieve a single image using its ID")
    @GetMapping("/{id}")
    public ResponseEntity<BlindboxImage> getImageById(@PathVariable Integer id) {
        BlindboxImage image = blindboxImageService.getImageById(id);
        return new ResponseEntity<>(image, HttpStatus.OK);
    }

    // Create a new image
    @Operation(summary = "Create a new image", description = "Add a new image to the catalog")
    @PostMapping
    public ResponseEntity<BlindboxImage> createImage(@RequestBody BlindboxImage image) {
        BlindboxImage createdImage = blindboxImageService.createImage(image);
        return new ResponseEntity<>(createdImage, HttpStatus.CREATED);
    }

    // Update an existing image
    @Operation(summary = "Update an existing image", description = "Update an existing image using its ID")
    @PutMapping("/{id}")
    public ResponseEntity<BlindboxImage> updateImage(@PathVariable Integer id, @RequestBody BlindboxImage image) {
        image.setBlindboxImageID(id);
        BlindboxImage updatedImage = blindboxImageService.updateImage(image);
        return new ResponseEntity<>(updatedImage, HttpStatus.OK);
    }

    // Delete an image by ID
    @Operation(summary = "Delete an image by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteImage(@PathVariable Integer id) {
        blindboxImageService.deleteImage(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
