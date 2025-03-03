package com.blindbox.service.impl;

import com.blindbox.model.BlindboxImage;
import com.blindbox.repository.BlindboxImageRepository;
import com.blindbox.service.BlindboxImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BlindboxImageServiceImpl implements BlindboxImageService {

    private final BlindboxImageRepository blindboxImageRepository;

    @Autowired
    public BlindboxImageServiceImpl(BlindboxImageRepository blindboxImageRepository) {
        this.blindboxImageRepository = blindboxImageRepository;
    }

    @Override
    public List<BlindboxImage> getAllImages() {
        return blindboxImageRepository.findAll();
    }

    @Override
    public BlindboxImage getImageById(Integer id) {
        Optional<BlindboxImage> image = blindboxImageRepository.findById(id);
        return image.orElseThrow(() -> new RuntimeException("Image not found"));
    }

    @Override
    public BlindboxImage createImage(BlindboxImage image) {
        return blindboxImageRepository.save(image);
    }

    @Override
    public BlindboxImage updateImage(BlindboxImage image) {
        if (!blindboxImageRepository.existsById(image.getBlindboxImageId())) {
            throw new RuntimeException("Image not found");
        }
        return blindboxImageRepository.save(image);
    }

    @Override
    public void deleteImage(Integer id) {
        blindboxImageRepository.deleteById(id);
    }
}
