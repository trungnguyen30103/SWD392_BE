package com.blindbox.controller;

import com.blindbox.model.CustomerSupport;
import com.blindbox.service.CustomerSupportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer-support")
public class CustomerSupportController {

    @Autowired
    private CustomerSupportService customerSupportService;

    // Lấy tất cả yêu cầu hỗ trợ khách hàng
    @GetMapping
    public List<CustomerSupport> getAllCustomerSupports() {
        return customerSupportService.getAllCustomerSupports();
    }

    // Lấy yêu cầu hỗ trợ theo ID
    @GetMapping("/{id}")
    public CustomerSupport getCustomerSupportById(@PathVariable Integer id) {
        return customerSupportService.getCustomerSupportById(id);
    }

    // Tạo yêu cầu hỗ trợ mới
    @PostMapping
    public CustomerSupport createCustomerSupport(@RequestBody CustomerSupport customerSupport) {
        return customerSupportService.createCustomerSupport(customerSupport);
    }

    // Cập nhật yêu cầu hỗ trợ
    @PutMapping("/{id}")
    public CustomerSupport updateCustomerSupport(@PathVariable Integer id, @RequestBody CustomerSupport customerSupport) {
        return customerSupportService.updateCustomerSupport(id, customerSupport);
    }

    // Xóa yêu cầu hỗ trợ
    @DeleteMapping("/{id}")
    public void deleteCustomerSupport(@PathVariable Integer id) {
        customerSupportService.deleteCustomerSupport(id);
    }
}
