package com.blindbox.controller;

import com.blindbox.model.Result;
import com.blindbox.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/result")
public class ResultController {

    @Autowired
    private ResultService resultService;

    @GetMapping
    public List<Result> getAllResults() {
        return resultService.getAllResults();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Result> getResultById(@PathVariable Integer id) {
        return resultService.getResultById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Result createResult(@RequestBody Result result) {
        return resultService.createResult(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Result> updateResult(@PathVariable Integer id, @RequestBody Result result) {
        Result updatedResult = resultService.updateResult(id, result);
        if (updatedResult != null) {
            return ResponseEntity.ok(updatedResult);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResult(@PathVariable Integer id) {
        resultService.deleteResult(id);
        return ResponseEntity.noContent().build();
    }
}
