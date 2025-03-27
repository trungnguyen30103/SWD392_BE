package com.blindbox.service.impl;

import com.blindbox.model.Result;
import com.blindbox.repository.ResultRepository;
import com.blindbox.response.DTO.ResultDTO;
import com.blindbox.service.ResultService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ResultServiceImpl implements ResultService {

    private final ResultRepository resultRepository;

    public ResultServiceImpl(ResultRepository resultRepository) {
        this.resultRepository = resultRepository;
    }

    @Override
    public List<Result> getAllResults() {
        return resultRepository.findAll();
    }

    @Override
    public Result getResultById(Integer id) {
        Optional<Result> result = resultRepository.findById(id);
        return result.orElse(null);
    }

    @Override
    public Result createResult(Result result) {
        return resultRepository.save(result);
    }

    @Override
    public Result updateResult(Integer id, Result result) {
        if (resultRepository.existsById(id)) {
            result.setResultID(id);
            return resultRepository.save(result);
        }
        return null;
    }

    @Override
    public boolean deleteResult(Integer id) {
        if (resultRepository.existsById(id)) {
            resultRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<Result> getResultByOrderID(Integer orderId) {
        return resultRepository.findByOrder_OrderId(orderId);
    }

    @Override
    public List<Result> getResultByBlindboxID(Integer blindboxId) {
        return resultRepository.findByBlindboxItem_Blindbox_BlindboxID(blindboxId);
    }

    @Override
    public List<ResultDTO> convertToDTO(List<Result> results) {
        return results.stream().map(result -> new ResultDTO(
                result.getOrder().getOrderId(),
                result.getBlindboxItem().getBlindbox().getBlindboxID(),
                result.getBlindboxItem().getBlindboxItemID(),
                result.getBlindboxItem().getImageUrl(),
                result.getBlindboxItem().getRarity(),
                result.getCreatedAt()
        )).collect(Collectors.toList());
    }

    @Override
    public ResultDTO convertToDTO(Result result) {
        return new ResultDTO(
                result.getOrder().getOrderId(),
                result.getBlindboxItem().getBlindbox().getBlindboxID(),
                result.getBlindboxItem().getBlindboxItemID(),
                result.getBlindboxItem().getImageUrl(),
                result.getBlindboxItem().getRarity(),
                result.getCreatedAt()
        );
    }
}
