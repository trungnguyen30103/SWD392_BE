package com.blindbox.service.impl;

import com.blindbox.model.*;
import com.blindbox.repository.*;
import com.blindbox.service.GachaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;

@Service
public class GachaServiceImpl implements GachaService {

    @Autowired
    private BlindBoxItemRepository blindBoxItemRepository;

    @Autowired
    private GachaHistoryRepository gachaHistoryRepository;

    @Autowired
    private UserAccountRepository userAccountRepository;

    private static final BigDecimal GACHA_COST = new BigDecimal("5.00");
    private static final int GACHA_BOUNDS = 100;

    private final Random random = new Random();

    @Override
    public String openBlindBox(Integer userId) {

        UserAccount userAccount = userAccountRepository.findByUserUserID(userId);
        if (userAccount == null) {
            return "Người dùng không tồn tại.";
        }

        if (userAccount.getBalance().compareTo(GACHA_COST) < 0) {
            return "Bạn không đủ tiền để mở hộp gacha! Vui lòng nạp thêm tiền.";
        }

        userAccount.setBalance(userAccount.getBalance().subtract(GACHA_COST));
        userAccountRepository.save(userAccount);


        List<BlindBoxItem> items = blindBoxItemRepository.findAll();


        int randomValue = random.nextInt(GACHA_BOUNDS) + 1;  // Random value between 1 and 100
        int cumulativeProbability = 0;

        for (BlindBoxItem item : items) {
            cumulativeProbability += item.getRarity();
            if (randomValue <= cumulativeProbability) {

                GachaHistory history = new GachaHistory(userId, item.getName(), java.time.LocalDateTime.now().toString());
                gachaHistoryRepository.save(history);
                return "Bạn nhận được: " + item.getName();
            }
        }

        return "Không có vật phẩm trúng thưởng!";
    }
}
