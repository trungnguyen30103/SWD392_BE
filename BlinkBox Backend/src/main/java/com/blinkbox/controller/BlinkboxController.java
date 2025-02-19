package com.blinkbox.controller;

import com.blinkbox.model.Blinkbox;
import com.blinkbox.service.BlinkboxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/blinkboxes")
public class BlinkboxController {
    @Autowired
    private BlinkboxService blinkboxService;

    @GetMapping
    public List<Blinkbox> getAllBlinkboxes() {
        return blinkboxService.getAllBlinkboxes();
    }

    @GetMapping("/{id}")
    public Blinkbox getBlinkboxById(@PathVariable Long id) {
        return blinkboxService.getBlinkboxById(id);
    }

    @PostMapping
    public Blinkbox createBlinkbox(@RequestBody Blinkbox blinkbox) {
        return blinkboxService.createBlinkbox(blinkbox);
    }

    @PutMapping("/{id}")
    public Blinkbox updateBlinkbox(@PathVariable Long id, @RequestBody Blinkbox blinkbox) {
        return blinkboxService.updateBlinkbox(id, blinkbox);
    }

    @DeleteMapping("/{id}")
    public void deleteBlinkbox(@PathVariable Long id) {
        blinkboxService.deleteBlinkbox(id);
    }
}
