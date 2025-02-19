package com.blinkbox.service.impl;

import com.blinkbox.model.BlinkboxImage;
import com.blinkbox.repository.BlinkboxImageRepository;
import com.blinkbox.service.BlinkboxImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BlinkboxImageServiceImpl implements BlinkboxImageService {

    @Autowired
    private BlinkboxImageRepository blinkboxImageRepository;

    @Override
    public List<BlinkboxImage> getAllBlinkboxImages() {
        return blinkboxImageRepository.findAll();
    }

    @Override
    public List<BlinkboxImage> getImagesByBlinkboxId(Long blinkboxId) {
        return blinkboxImageRepository.findByBlinkboxBlinkboxId(blinkboxId);
    }

    @Override
    public BlinkboxImage getBlinkboxImageById(Long id) {
        Optional<BlinkboxImage> blinkboxImage = blinkboxImageRepository.findById(id);
        return blinkboxImage.orElse(null);
    }

    @Override
    public BlinkboxImage createBlinkboxImage(BlinkboxImage blinkboxImage) {
        return blinkboxImageRepository.save(blinkboxImage);
    }

    @Override
    public BlinkboxImage updateBlinkboxImage(Long id, BlinkboxImage blinkboxImage) {
        if (blinkboxImageRepository.existsById(id)) {
            blinkboxImage.setBlinkboxImageId(id);
            return blinkboxImageRepository.save(blinkboxImage);
        }
        return null;
    }

    @Override
    public void deleteBlinkboxImage(Long id) {
        blinkboxImageRepository.deleteById(id);
    }
}
