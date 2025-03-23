package com.blindbox.service;

import com.blindbox.model.Result;

import java.util.List;

public interface ResultService {
    List<Result> getAllResults();
    Result getResultById(Integer id);
    Result createResult(Result result);
    Result updateResult(Integer id, Result result);
    boolean deleteResult(Integer id);
}
