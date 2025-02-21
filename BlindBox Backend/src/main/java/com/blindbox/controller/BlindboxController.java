package com.blindbox.controller;

import com.blindbox.model.Blindbox;
import com.blindbox.service.BlindboxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/blindbox")
public class BlindboxController {

    @Autowired
    private BlindboxService blindboxService;

    @GetMapping
    public List<Blindbox> getAllBlindboxes() {
        return blindboxService.getAllBlindboxes();
    }

    @GetMapping("/{id}")
    public Blindbox getBlindboxById(@PathVariable Integer id) {
        return blindboxService.getBlindboxById(id);
    }

    @PostMapping
    public Blindbox createBlindbox(@RequestBody Blindbox blindbox) {
        return blindboxService.createBlindbox(blindbox);
    }

    @PutMapping("/{id}")
    public Blindbox updateBlindbox(@PathVariable Integer id, @RequestBody Blindbox blindbox) {
        return blindboxService.updateBlindbox(id, blindbox);
    }

    @DeleteMapping("/{id}")
    public void deleteBlindbox(@PathVariable Integer id) {
        blindboxService.deleteBlindbox(id);
    }
}
