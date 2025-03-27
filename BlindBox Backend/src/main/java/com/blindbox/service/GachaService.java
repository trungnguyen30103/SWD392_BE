package com.blindbox.service;

import com.blindbox.model.BlindBoxItem;
import com.blindbox.model.GachaHistory;

import java.util.List;

public interface GachaService {
    List<BlindBoxItem> openBlindbox(Integer userId, Integer orderId);
    List<GachaHistory> getAllGachaHistory();
    List<GachaHistory> getGachaHistoryByUserID(Integer userId);
}
