package com.blindbox.service;

import com.blindbox.model.Discount;
import java.util.List;

public interface DiscountService {
    List<Discount> getAllDiscounts();
    Discount getDiscountById(Long id);
    Discount createDiscount(Discount discount);
    Discount updateDiscount(Long id, Discount discount);
    void deleteDiscount(Long id);
}
