package com.blindbox.service.impl;

import com.blindbox.model.Blindbox;
import com.blindbox.repository.BlindboxRepository;
import com.blindbox.service.BlindboxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BlindboxServiceImpl implements BlindboxService {

    private final BlindboxRepository blindboxRepository;

    @Autowired
    public BlindboxServiceImpl(BlindboxRepository blindboxRepository) {
        this.blindboxRepository = blindboxRepository;
    }

    @Override
    public List<Blindbox> getAllBlindboxes() {
        return blindboxRepository.findAll();
    }

    @Override
    public Blindbox getBlindboxById(Integer id) {
        Optional<Blindbox> blindbox = blindboxRepository.findById(id);
        return blindbox.orElseThrow(() -> new RuntimeException("Blindbox not found"));
    }

    @Override
    public Blindbox createBlindbox(Blindbox blindbox) {
        return blindboxRepository.save(blindbox);
    }

    @Override
    public Blindbox updateBlindbox(Blindbox blindbox) {
        if (!blindboxRepository.existsById(blindbox.getBlindboxID())) {
            throw new RuntimeException("Blindbox not found");
        }
        return blindboxRepository.save(blindbox);
    }

    @Override
    public void deleteBlindbox(Integer id) {
        blindboxRepository.deleteById(id);
    }
}
