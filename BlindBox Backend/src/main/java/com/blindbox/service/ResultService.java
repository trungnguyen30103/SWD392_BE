package com.blindbox.service;

import com.blindbox.model.Result;
import com.blindbox.response.DTO.ResultDTO;

import java.util.List;

public interface ResultService {
    List<Result> getAllResults();
    Result getResultById(Integer id);
    Result createResult(Result result);
    Result updateResult(Integer id, Result result);
    boolean deleteResult(Integer id);

    // Quy's
    List<Result> getResultByOrderID(Integer orderId);
    List<Result> getResultByBlindboxID(Integer blindboxId);
    List<ResultDTO> convertToDTO(List<Result> results);
    ResultDTO convertToDTO(Result result);
}
