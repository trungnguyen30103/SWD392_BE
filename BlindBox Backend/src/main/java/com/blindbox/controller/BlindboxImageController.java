package com.blindbox.controller;

import com.blindbox.model.BlindboxImage;
import com.blindbox.service.BlindboxImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/blindboximages")
public class BlindboxImageController {

    @Autowired
    private BlindboxImageService blindboxImageService;

    @GetMapping
    public List<BlindboxImage> getAllBlindboxImages() {
        return blindboxImageService.getAllBlindboxImages();
    }

    @GetMapping("/blindbox/{blindboxID}")
    public List<BlindboxImage> getImagesByBlindboxId(@PathVariable Integer blindboxId) {
        return blindboxImageService.getImagesByBlindboxId(blindboxId);
    }

    @GetMapping("/{id}")
    public BlindboxImage getBlindboxImageById(@PathVariable Integer id) {
        return blindboxImageService.getBlindboxImageById(id);
    }

    @PostMapping
    public BlindboxImage createBlindboxImage(@RequestBody BlindboxImage blindboxImage) {
        return blindboxImageService.createBlindboxImage(blindboxImage);
    }

    @PutMapping("/{id}")
    public BlindboxImage updateBlindboxImage(@PathVariable Integer id, @RequestBody BlindboxImage blindboxImage) {
        return blindboxImageService.updateBlindboxImage(id, blindboxImage);
    }

    @DeleteMapping("/{id}")
    public void deleteBlindboxImage(@PathVariable Integer id) {
        blindboxImageService.deleteBlindboxImage(id);
    }
}
