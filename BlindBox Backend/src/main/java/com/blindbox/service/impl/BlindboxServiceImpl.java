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

    @Autowired
    private BlindboxRepository blindboxRepository;

    @Override
    public List<Blindbox> getAllBlindboxes() {
        return blindboxRepository.findAll();
    }

    @Override
    public Blindbox getBlindboxById(Integer id) {
        Optional<Blindbox> blindbox = blindboxRepository.findById(id);
        return blindbox.orElse(null);
    }

    @Override
    public Blindbox createBlindbox(Blindbox blindbox) {
        return blindboxRepository.save(blindbox);
    }

    @Override
    public Blindbox updateBlindbox(Integer id, Blindbox blindbox) {
        if (blindboxRepository.existsById(id)) {
            blindbox.setBlindboxID(id);
            return blindboxRepository.save(blindbox);
        }
        return null;
    }

    @Override
    public void deleteBlindbox(Integer id) {
        blindboxRepository.deleteById(id);
    }
}
