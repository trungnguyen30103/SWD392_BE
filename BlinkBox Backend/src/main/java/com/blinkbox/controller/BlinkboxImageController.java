package com.blinkbox.controller;

import com.blinkbox.model.BlinkboxImage;
import com.blinkbox.service.BlinkboxImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/blinkbox-images")
public class BlinkboxImageController {

    @Autowired
    private BlinkboxImageService blinkboxImageService;

    @GetMapping
    public List<BlinkboxImage> getAllBlinkboxImages() {
        return blinkboxImageService.getAllBlinkboxImages();
    }

    @GetMapping("/blinkbox/{blinkboxId}")
    public List<BlinkboxImage> getImagesByBlinkboxId(@PathVariable Long blinkboxId) {
        return blinkboxImageService.getImagesByBlinkboxId(blinkboxId);
    }

    @GetMapping("/{id}")
    public BlinkboxImage getBlinkboxImageById(@PathVariable Long id) {
        return blinkboxImageService.getBlinkboxImageById(id);
    }

    @PostMapping
    public BlinkboxImage createBlinkboxImage(@RequestBody BlinkboxImage blinkboxImage) {
        return blinkboxImageService.createBlinkboxImage(blinkboxImage);
    }

    @PutMapping("/{id}")
    public BlinkboxImage updateBlinkboxImage(@PathVariable Long id, @RequestBody BlinkboxImage blinkboxImage) {
        return blinkboxImageService.updateBlinkboxImage(id, blinkboxImage);
    }

    @DeleteMapping("/{id}")
    public void deleteBlinkboxImage(@PathVariable Long id) {
        blinkboxImageService.deleteBlinkboxImage(id);
    }
}
