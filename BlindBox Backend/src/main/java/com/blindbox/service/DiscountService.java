package com.blindbox.service;

import com.blindbox.model.Discount;
import java.util.List;

public interface DiscountService {

    List<Discount> getAllDiscounts();

    Discount getDiscountById(Integer id);

    Discount createDiscount(Discount discount);

    Discount updateDiscount(Integer id, Discount discount);

    boolean deleteDiscount(Integer id);
}
