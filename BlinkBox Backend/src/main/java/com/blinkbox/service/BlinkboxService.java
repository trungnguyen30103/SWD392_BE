package com.blinkbox.service;

import com.blinkbox.model.Blinkbox;
import java.util.List;

public interface BlinkboxService {
    List<Blinkbox> getAllBlinkboxes();
    Blinkbox getBlinkboxById(Long id);
    Blinkbox createBlinkbox(Blinkbox blinkbox);
    Blinkbox updateBlinkbox(Long id, Blinkbox blinkbox);
    void deleteBlinkbox(Long id);
}
