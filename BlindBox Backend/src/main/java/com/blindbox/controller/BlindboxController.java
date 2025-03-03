package com.blindbox.controller;

import com.blindbox.model.Blindbox;
import com.blindbox.service.BlindboxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/blindboxes")
public class BlindboxController {

    private final BlindboxService blindboxService;

    @Autowired
    public BlindboxController(BlindboxService blindboxService) {
        this.blindboxService = blindboxService;
    }

    @GetMapping
    public ResponseEntity<List<Blindbox>> getAllBlindboxes() {
        List<Blindbox> blindboxes = blindboxService.getAllBlindboxes();
        return new ResponseEntity<>(blindboxes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Blindbox> getBlindboxById(@PathVariable Integer id) {
        Blindbox blindbox = blindboxService.getBlindboxById(id);
        return new ResponseEntity<>(blindbox, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Blindbox> createBlindbox(@RequestBody Blindbox blindbox) {
        Blindbox createdBlindbox = blindboxService.createBlindbox(blindbox);
        return new ResponseEntity<>(createdBlindbox, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Blindbox> updateBlindbox(@PathVariable Integer id, @RequestBody Blindbox blindbox) {
        blindbox.setBlindboxId(id);
        Blindbox updatedBlindbox = blindboxService.updateBlindbox(blindbox);
        return new ResponseEntity<>(updatedBlindbox, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBlindbox(@PathVariable Integer id) {
        blindboxService.deleteBlindbox(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
