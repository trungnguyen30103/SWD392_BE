package com.blindbox.controller;

import com.blindbox.model.Result;
import com.blindbox.service.ResultService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Result Management System", description = "Operations pertaining to results in the Result Management System")
@RestController
@RequestMapping("/results")
public class ResultController {

    @Autowired
    private ResultService resultService;

    // Lấy tất cả kết quả
    @Operation(summary = "Get all results", description = "Retrieve a list of all available results")
    @GetMapping
    public ResponseEntity<List<Result>> getAllResults() {
        List<Result> results = resultService.getAllResults();
        return new ResponseEntity<>(results, HttpStatus.OK);
    }

    // Lấy kết quả theo ID
    @Operation(summary = "Get a result by ID", description = "Retrieve a single result using its ID")
    @GetMapping("/{id}")
    public ResponseEntity<Result> getResultById(@PathVariable Integer id) {
        Result result = resultService.getResultById(id);
        if (result != null) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Tạo mới kết quả
    @Operation(summary = "Create a new result", description = "Add a new result to the catalog")
    @PostMapping
    public ResponseEntity<Result> createResult(@RequestBody Result result) {
        Result createdResult = resultService.createResult(result);
        return new ResponseEntity<>(createdResult, HttpStatus.CREATED);
    }

    // Cập nhật kết quả
    @Operation(summary = "Update an existing result", description = "Update an existing result using its ID")
    @PutMapping("/{id}")
    public ResponseEntity<Result> updateResult(@PathVariable Integer id, @RequestBody Result result) {
        Result updatedResult = resultService.updateResult(id, result);
        if (updatedResult != null) {
            return new ResponseEntity<>(updatedResult, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Xóa kết quả
    @Operation(summary = "Delete a result by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResult(@PathVariable Integer id) {
        if (resultService.deleteResult(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
