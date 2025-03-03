package com.blindbox.service;

import com.blindbox.model.BlindboxImage;
import java.util.List;

public interface BlindboxImageService {
    List<BlindboxImage> getAllImages();
    BlindboxImage getImageById(Integer id);
    BlindboxImage createImage(BlindboxImage image);
    BlindboxImage updateImage(BlindboxImage image);
    void deleteImage(Integer id);
}
