package com.blindbox.service.impl;

import com.blindbox.model.Discount;
import com.blindbox.repository.DiscountRepository;
import com.blindbox.service.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DiscountServiceImpl implements DiscountService {

    @Autowired
    private DiscountRepository discountRepository;

    @Override
    public List<Discount> getAllDiscounts() {
        return discountRepository.findAll();
    }

    @Override
    public Discount getDiscountById(Integer id) {
        Optional<Discount> discount = discountRepository.findById(id);
        return discount.orElse(null);
    }

    @Override
    public Discount createDiscount(Discount discount) {
        return discountRepository.save(discount);
    }

    @Override
    public Discount updateDiscount(Integer id, Discount discount) {
        if (!discountRepository.existsById(id)) {
            return null;
        }
        discount.setDiscountId(id);
        return discountRepository.save(discount);
    }

    @Override
    public boolean deleteDiscount(Integer id) {
        if (!discountRepository.existsById(id)) {
            return false;
        }
        discountRepository.deleteById(id);
        return true;
    }
}
