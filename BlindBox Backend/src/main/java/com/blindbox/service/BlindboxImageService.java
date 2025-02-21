package com.blindbox.service;

import com.blindbox.model.BlindboxImage;
import java.util.List;

public interface BlindboxImageService {
    List<BlindboxImage> getAllBlindboxImages();
    List<BlindboxImage> getImagesByBlindboxId(Integer blindboxId);
    BlindboxImage getBlindboxImageById(Integer id);
    BlindboxImage createBlindboxImage(BlindboxImage blindboxImage);
    BlindboxImage updateBlindboxImage(Integer id, BlindboxImage blindboxImage);
    void deleteBlindboxImage(Integer id);
}
