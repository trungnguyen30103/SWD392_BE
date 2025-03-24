package com.blindbox.service.impl;

import com.blindbox.model.*;
import com.blindbox.repository.*;
import com.blindbox.service.GachaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    private static final double GACHA_COST = 5.00;  // Sử dụng double thay vì BigDecimal
    private static final int GACHA_BOUNDS = 100;

    private final Random random = new Random();

    @Override
    public String openBlindBox(Integer userId) {

        UserAccount userAccount = userAccountRepository.findByUserUserID(userId);
        if (userAccount == null) {
            return "Người dùng không tồn tại.";
        }

        // So sánh số dư với GACHA_COST (kiểu double)
        if (userAccount.getBalance() < GACHA_COST) {
            return "Bạn không đủ tiền để mở hộp gacha! Vui lòng nạp thêm tiền.";
        }

        // Cập nhật số dư sau khi trừ GACHA_COST
        userAccount.setBalance(userAccount.getBalance() - GACHA_COST);
        userAccountRepository.save(userAccount);

        // Lấy danh sách các vật phẩm trong blindbox
        List<BlindBoxItem> items = blindBoxItemRepository.findAll();

        // Tạo giá trị random
        int randomValue = random.nextInt(GACHA_BOUNDS) + 1;  // Random value between 1 and 100
        int cumulativeProbability = 0;

        // Kiểm tra xác suất để chọn vật phẩm trúng thưởng
        for (BlindBoxItem item : items) {
            cumulativeProbability += item.getRarity();
            if (randomValue <= cumulativeProbability) {

                // Lưu lịch sử mở hộp gacha
                GachaHistory history = new GachaHistory(userId, item.getName(), java.time.LocalDateTime.now().toString());
                gachaHistoryRepository.save(history);
                return "Bạn nhận được: " + item.getName();
            }
        }

        return "Không có vật phẩm trúng thưởng!";
    }
}
