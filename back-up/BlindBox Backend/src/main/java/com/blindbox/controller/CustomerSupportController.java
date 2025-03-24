package com.blindbox.controller;

import com.blindbox.model.CustomerSupport;
import com.blindbox.service.CustomerSupportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Customer Support Management System", description = "Operations pertaining to customer support in the Customer Support Management System")
@RestController
@RequestMapping("/api/customer-support")
public class CustomerSupportController {

    @Autowired
    private CustomerSupportService customerSupportService;

    // Lấy tất cả yêu cầu hỗ trợ khách hàng
    @Operation(summary = "Get all customer support requests", description = "Retrieve a list of all customer support requests")
    @GetMapping
    public List<CustomerSupport> getAllCustomerSupports() {
        return customerSupportService.getAllCustomerSupports();
    }

    // Lấy yêu cầu hỗ trợ theo ID
    @Operation(summary = "Get a customer support request by ID", description = "Retrieve a single customer support request using its ID")
    @GetMapping("/{id}")
    public CustomerSupport getCustomerSupportById(@PathVariable Integer id) {
        return customerSupportService.getCustomerSupportById(id);
    }

    // Tạo yêu cầu hỗ trợ mới
    @Operation(summary = "Create a new customer support request", description = "Add a new customer support request to the system")
    @PostMapping
    public CustomerSupport createCustomerSupport(@RequestBody CustomerSupport customerSupport) {
        return customerSupportService.createCustomerSupport(customerSupport);
    }

    // Cập nhật yêu cầu hỗ trợ
    @Operation(summary = "Update an existing customer support request", description = "Update an existing customer support request using its ID")
    @PutMapping("/{id}")
    public CustomerSupport updateCustomerSupport(@PathVariable Integer id, @RequestBody CustomerSupport customerSupport) {
        return customerSupportService.updateCustomerSupport(id, customerSupport);
    }

    // Xóa yêu cầu hỗ trợ
    @Operation(summary = "Delete a customer support request by ID")
    @DeleteMapping("/{id}")
    public void deleteCustomerSupport(@PathVariable Integer id) {
        customerSupportService.deleteCustomerSupport(id);
    }
}
