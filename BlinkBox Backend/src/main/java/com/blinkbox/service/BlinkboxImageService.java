package com.blinkbox.service;

import com.blinkbox.model.BlinkboxImage;
import java.util.List;

public interface BlinkboxImageService {
    List<BlinkboxImage> getAllBlinkboxImages();
    List<BlinkboxImage> getImagesByBlinkboxId(Long blinkboxId);
    BlinkboxImage getBlinkboxImageById(Long id);
    BlinkboxImage createBlinkboxImage(BlinkboxImage blinkboxImage);
    BlinkboxImage updateBlinkboxImage(Long id, BlinkboxImage blinkboxImage);
    void deleteBlinkboxImage(Long id);
}
