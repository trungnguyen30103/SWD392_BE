package com.blindbox.service;

import com.blindbox.model.BlindboxImage;
import java.util.List;

public interface BlindboxImageService {
    List<BlindboxImage> getAllBlindboxImages();
    List<BlindboxImage> getImagesByBlindboxId(Long blindboxId);
    BlindboxImage getBlindboxImageById(Long id);
    BlindboxImage createBlindboxImage(BlindboxImage blindboxImage);
    BlindboxImage updateBlindboxImage(Long id, BlindboxImage blindboxImage);
    void deleteBlindboxImage(Long id);
}
