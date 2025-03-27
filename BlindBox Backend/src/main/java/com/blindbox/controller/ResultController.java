package com.blindbox.controller;

import com.blindbox.response.DTO.ResultDTO;
import com.blindbox.model.Result;
import com.blindbox.service.ResultService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Result Management System", description = "Operations pertaining to results in the Result Management System")
@RestController
@RequestMapping("/results")
public class ResultController {

    private final ResultService resultService;

    public ResultController(ResultService resultService) {
        this.resultService = resultService;
    }

    @Operation(summary = "Get all results", description = "Retrieve a list of all available results")
    @GetMapping
    public ResponseEntity<List<ResultDTO>> getAllResults() {
        List<Result> results = resultService.getAllResults();
        List<ResultDTO> resultDTOs = resultService.convertToDTO(results);
        return new ResponseEntity<>(resultDTOs, HttpStatus.OK);
    }

    @Operation(summary = "Get a result by ID", description = "Retrieve a single result using its ID")
    @GetMapping("/{id}")
    public ResponseEntity<ResultDTO> getResultById(@PathVariable Integer id) {
        Result result = resultService.getResultById(id);
        if (result != null) {
            ResultDTO resultDTO = resultService.convertToDTO(result);
            return new ResponseEntity<>(resultDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Create a new result", description = "Add a new result to the catalog")
    @PostMapping
    public ResponseEntity<ResultDTO> createResult(@RequestBody Result result) {
        Result createdResult = resultService.createResult(result);
        ResultDTO resultDTO = resultService.convertToDTO(createdResult);
        return new ResponseEntity<>(resultDTO, HttpStatus.CREATED);
    }

    @Operation(summary = "Update an existing result", description = "Update an existing result using its ID")
    @PutMapping("/{id}")
    public ResponseEntity<ResultDTO> updateResult(@PathVariable Integer id, @RequestBody Result result) {
        Result updatedResult = resultService.updateResult(id, result);
        if (updatedResult != null) {
            ResultDTO resultDTO = resultService.convertToDTO(updatedResult);
            return new ResponseEntity<>(resultDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Delete a result by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResult(@PathVariable Integer id) {
        if (resultService.deleteResult(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Get results by Order ID", description = "Retrieve results using Order ID")
    @GetMapping("/order/{orderId}")
    public ResponseEntity<List<ResultDTO>> getResultByOrderID(@PathVariable Integer orderId) {
        List<Result> results = resultService.getResultByOrderID(orderId);
        List<ResultDTO> resultDTOs = resultService.convertToDTO(results);
        return new ResponseEntity<>(resultDTOs, HttpStatus.OK);
    }

    @Operation(summary = "Get results by Blindbox ID", description = "Retrieve results using Blindbox ID")
    @GetMapping("/blindbox/{blindboxId}")
    public ResponseEntity<List<ResultDTO>> getResultByBlindboxID(@PathVariable Integer blindboxId) {
        List<Result> results = resultService.getResultByBlindboxID(blindboxId);
        List<ResultDTO> resultDTOs = resultService.convertToDTO(results);
        return new ResponseEntity<>(resultDTOs, HttpStatus.OK);
    }
}