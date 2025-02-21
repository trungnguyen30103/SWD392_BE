package com.blindbox.service;

import com.blindbox.model.Blindbox;
import java.util.List;

public interface BlindboxService {
    List<Blindbox> getAllBlindboxes();
    Blindbox getBlindboxById(Long id);
    Blindbox createBlindbox(Blindbox blindbox);
    Blindbox updateBlindbox(Long id, Blindbox blindbox);
    void deleteBlindbox(Long id);
}
