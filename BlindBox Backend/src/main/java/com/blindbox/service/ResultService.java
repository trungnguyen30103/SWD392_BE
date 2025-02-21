package com.blindbox.service;

import com.blindbox.model.Result;
import java.util.List;
import java.util.Optional;

public interface ResultService {

    List<Result> getAllResults();

    Optional<Result> getResultById(Integer id);

    Result createResult(Result result);

    Result updateResult(Integer id, Result result);

    void deleteResult(Integer id);
}
