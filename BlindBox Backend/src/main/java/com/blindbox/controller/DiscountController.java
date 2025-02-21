package com.blindbox.controller;

import com.blindbox.model.Discount;
import com.blindbox.service.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/discount")
public class DiscountController {

    @Autowired
    private DiscountService discountService;

    @GetMapping
    public List<Discount> getAllDiscounts() {
        return discountService.getAllDiscounts();
    }

    @GetMapping("/{id}")
    public Discount getDiscountById(@PathVariable Integer id) {
        return discountService.getDiscountById(id);
    }

    @PostMapping
    public Discount createDiscount(@RequestBody Discount discount) {
        return discountService.createDiscount(discount);
    }

    @PutMapping("/{id}")
    public Discount updateDiscount(@PathVariable Integer id, @RequestBody Discount discount) {
        return discountService.updateDiscount(id, discount);
    }

    @DeleteMapping("/{id}")
    public void deleteDiscount(@PathVariable Integer id) {
        discountService.deleteDiscount(id);
    }
}
