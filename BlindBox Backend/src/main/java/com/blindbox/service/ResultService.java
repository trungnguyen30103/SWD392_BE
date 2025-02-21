package com.blindbox.service;

import com.blindbox.model.Result;

import java.util.List;
import java.util.Optional;

public interface ResultService {
    List<Result> getAllResults();
    Optional<Result> getResultById(Long resultID);
    Result saveResult(Result result);
    void deleteResult(Long resultID);
}
