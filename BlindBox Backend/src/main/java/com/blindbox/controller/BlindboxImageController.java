package com.blindbox.controller;

import com.blindbox.model.BlindboxImage;
import com.blindbox.service.BlindboxImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/blindbox-images")
public class BlindboxImageController {

    private final BlindboxImageService blindboxImageService;

    @Autowired
    public BlindboxImageController(BlindboxImageService blindboxImageService) {
        this.blindboxImageService = blindboxImageService;
    }

    @GetMapping
    public ResponseEntity<List<BlindboxImage>> getAllImages() {
        List<BlindboxImage> images = blindboxImageService.getAllImages();
        return new ResponseEntity<>(images, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BlindboxImage> getImageById(@PathVariable Integer id) {
        BlindboxImage image = blindboxImageService.getImageById(id);
        return new ResponseEntity<>(image, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BlindboxImage> createImage(@RequestBody BlindboxImage image) {
        BlindboxImage createdImage = blindboxImageService.createImage(image);
        return new ResponseEntity<>(createdImage, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BlindboxImage> updateImage(@PathVariable Integer id, @RequestBody BlindboxImage image) {
        image.setBlindboxImageID(id);
        BlindboxImage updatedImage = blindboxImageService.updateImage(image);
        return new ResponseEntity<>(updatedImage, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteImage(@PathVariable Integer id) {
        blindboxImageService.deleteImage(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
