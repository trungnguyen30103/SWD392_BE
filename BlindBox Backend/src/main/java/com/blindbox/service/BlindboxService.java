package com.blindbox.service;

import com.blindbox.model.Blindbox;
import java.util.List;

public interface BlindboxService {
    List<Blindbox> getAllBlindboxes();
    Blindbox getBlindboxById(Integer id);
    Blindbox createBlindbox(Blindbox blindbox);
    Blindbox updateBlindbox(Blindbox blindbox);
    void deleteBlindbox(Integer id);
}
