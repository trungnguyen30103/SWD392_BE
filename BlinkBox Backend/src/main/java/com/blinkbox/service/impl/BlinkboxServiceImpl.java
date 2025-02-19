package com.blinkbox.service.impl;

import com.blinkbox.model.Blinkbox;
import com.blinkbox.repository.BlinkboxRepository;
import com.blinkbox.service.BlinkboxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BlinkboxServiceImpl implements BlinkboxService {

    @Autowired
    private BlinkboxRepository blinkboxRepository;

    @Override
    public List<Blinkbox> getAllBlinkboxes() {
        return blinkboxRepository.findAll();
    }

    @Override
    public Blinkbox getBlinkboxById(Long id) {
        Optional<Blinkbox> blinkbox = blinkboxRepository.findById(id);
        return blinkbox.orElse(null);
    }

    @Override
    public Blinkbox createBlinkbox(Blinkbox blinkbox) {
        return blinkboxRepository.save(blinkbox);
    }

    @Override
    public Blinkbox updateBlinkbox(Long id, Blinkbox blinkbox) {
        if (blinkboxRepository.existsById(id)) {
            blinkbox.setBlinkboxID(id);
            return blinkboxRepository.save(blinkbox);
        }
        return null;
    }

    @Override
    public void deleteBlinkbox(Long id) {
        blinkboxRepository.deleteById(id);
    }
}
