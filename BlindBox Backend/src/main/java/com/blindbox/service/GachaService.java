package com.blindbox.service;

import com.blindbox.model.BlindBoxItem;

import java.util.List;

public interface GachaService {
    List<BlindBoxItem> openBlindbox(Integer userId, Integer orderId);
}
