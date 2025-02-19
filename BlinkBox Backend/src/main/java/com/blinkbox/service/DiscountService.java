package com.blinkbox.service;

import com.blinkbox.model.Discount;
import java.util.List;

public interface DiscountService {
    List<Discount> getAllDiscounts();
    Discount getDiscountById(Long id);
    Discount createDiscount(Discount discount);
    Discount updateDiscount(Long id, Discount discount);
    void deleteDiscount(Long id);
}
