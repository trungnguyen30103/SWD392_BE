package com.blinkbox.controller;

import com.blinkbox.model.Result;
import com.blinkbox.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/results")
public class ResultController {

    @Autowired
    private ResultService resultService;

    @GetMapping
    public List<Result> getAllResults() {
        return resultService.getAllResults();
    }

    @GetMapping("/{resultID}")
    public Optional<Result> getResultById(@PathVariable Long resultID) {
        return resultService.getResultById(resultID);
    }

    @PostMapping
    public Result createResult(@RequestBody Result result) {
        return resultService.saveResult(result);
    }

    @DeleteMapping("/{resultID}")
    public void deleteResult(@PathVariable Long resultID) {
        resultService.deleteResult(resultID);
    }
}
