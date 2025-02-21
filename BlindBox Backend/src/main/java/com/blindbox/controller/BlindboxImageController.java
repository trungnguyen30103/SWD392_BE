package com.blindbox.controller;

import com.blindbox.model.BlindboxImage;
import com.blindbox.service.BlindboxImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/blindbox-images")
public class BlindboxImageController {

    @Autowired
    private BlindboxImageService blindboxImageService;

    @GetMapping
    public List<BlindboxImage> getAllBlindboxImages() {
        return blindboxImageService.getAllBlindboxImages();
    }

    @GetMapping("/blindbox/{blindboxId}")
    public List<BlindboxImage> getImagesByBlindboxId(@PathVariable Long blindboxId) {
        return blindboxImageService.getImagesByBlindboxId(blindboxId);
    }

    @GetMapping("/{id}")
    public BlindboxImage getBlindboxImageById(@PathVariable Long id) {
        return blindboxImageService.getBlindboxImageById(id);
    }

    @PostMapping
    public BlindboxImage createBlindboxImage(@RequestBody BlindboxImage blindboxImage) {
        return blindboxImageService.createBlindboxImage(blindboxImage);
    }

    @PutMapping("/{id}")
    public BlindboxImage updateBlindboxImage(@PathVariable Long id, @RequestBody BlindboxImage blindboxImage) {
        return blindboxImageService.updateBlindboxImage(id, blindboxImage);
    }

    @DeleteMapping("/{id}")
    public void deleteBlindboxImage(@PathVariable Long id) {
        blindboxImageService.deleteBlindboxImage(id);
    }
}
