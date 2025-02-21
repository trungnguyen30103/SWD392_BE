package com.blindbox.service;

import com.blindbox.model.Result;
import com.blindbox.repository.ResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ResultServiceImpl implements ResultService {

    @Autowired
    private ResultRepository resultRepository;

    @Override
    public List<Result> getAllResults() {
        return resultRepository.findAll();
    }

    @Override
    public Optional<Result> getResultById(Integer id) {
        return resultRepository.findById(id);
    }

    @Override
    public Result createResult(Result result) {
        return resultRepository.save(result);
    }

    @Override
    public Result updateResult(Integer id, Result result) {
        Optional<Result> existing = resultRepository.findById(id);
        if(existing.isPresent()){
            Result existingResult = existing.get();
            existingResult.setOrder(result.getOrder());
            existingResult.setBlindbox(result.getBlindbox());
            existingResult.setDrawTime(result.getDrawTime());
            return resultRepository.save(existingResult);
        } else {
            return null;
        }
    }

    @Override
    public void deleteResult(Integer id) {
        resultRepository.deleteById(id);
    }
}
