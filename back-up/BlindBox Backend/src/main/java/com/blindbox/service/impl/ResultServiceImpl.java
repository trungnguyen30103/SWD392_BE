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
}
