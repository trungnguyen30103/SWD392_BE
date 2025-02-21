package com.blindbox.service.impl;

import com.blindbox.model.Result;
import com.blindbox.repository.ResultRepository;
import com.blindbox.service.ResultService;
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
    public Optional<Result> getResultById(Long resultID) {
        return resultRepository.findById(resultID);
    }

    @Override
    public Result saveResult(Result result) {
        return resultRepository.save(result);
    }

    @Override
    public void deleteResult(Long resultID) {
        resultRepository.deleteById(resultID);
    }
}
