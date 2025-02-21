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

    @Autowired
    private BlindboxImageRepository blindboxImageRepository;

    @Override
    public List<BlindboxImage> getAllBlindboxImages() {
        return blindboxImageRepository.findAll();
    }

    @Override
    public List<BlindboxImage> getImagesByBlindboxId(Integer blindboxId) {
        return blindboxImageRepository.findByBlindbox_BlindboxID(blindboxId);
    }


    @Override
    public BlindboxImage getBlindboxImageById(Integer id) {
        Optional<BlindboxImage> blindboxImage = blindboxImageRepository.findById(id);
        return blindboxImage.orElse(null);
    }

    @Override
    public BlindboxImage createBlindboxImage(BlindboxImage blindboxImage) {
        return blindboxImageRepository.save(blindboxImage);
    }

    @Override
    public BlindboxImage updateBlindboxImage(Integer id, BlindboxImage blindboxImage) {
        if (blindboxImageRepository.existsById(id)) {
            blindboxImage.setBlindboxImageId(id);
            return blindboxImageRepository.save(blindboxImage);
        }
        return null;
    }

    @Override
    public void deleteBlindboxImage(Integer id) {
        blindboxImageRepository.deleteById(id);
    }
}
