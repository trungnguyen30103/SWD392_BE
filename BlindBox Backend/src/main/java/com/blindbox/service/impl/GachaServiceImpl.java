package com.blindbox.service.impl;

import com.blindbox.model.*;
import com.blindbox.repository.*;
import com.blindbox.service.GachaService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class GachaServiceImpl implements GachaService {

    private final BlindBoxItemRepository blindBoxItemRepository;

    private final GachaHistoryRepository gachaHistoryRepository;

    private final UserRepository userRepository;

    private static final double GACHA_COST = 5.00;  // Sử dụng double thay vì BigDecimal
    private static final int GACHA_BOUNDS = 100;

    private final Random random = new Random();

    public GachaServiceImpl(BlindBoxItemRepository blindBoxItemRepository, GachaHistoryRepository gachaHistoryRepository, UserRepository userRepository) {
        this.blindBoxItemRepository = blindBoxItemRepository;
        this.gachaHistoryRepository = gachaHistoryRepository;
        this.userRepository = userRepository;
    }

    @Override
    public String openBlindBox(Integer userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // So sánh số dư với GACHA_COST (kiểu double)
        if (user.getBalance() < GACHA_COST) {
            return "Bạn không đủ tiền để mở hộp gacha! Vui lòng nạp thêm tiền.";
        }

        // Cập nhật số dư sau khi trừ GACHA_COST
        user.setBalance(user.getBalance() - GACHA_COST);
        userRepository.save(user);

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
                return "You got: " + item.getName();
            }
        }

        return "Không có vật phẩm trúng thưởng!";
    }
}
