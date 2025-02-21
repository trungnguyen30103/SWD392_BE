package com.blindbox.controller;

import com.blindbox.model.CustomerSupport;
import com.blindbox.service.CustomerSupportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/support")
public class CustomerSupportController {

    @Autowired
    private CustomerSupportService supportService;

    @GetMapping
    public List<CustomerSupport> getAllSupports() {
        return supportService.getAllSupports();
    }

    @GetMapping("/{id}")
    public CustomerSupport getSupportById(@PathVariable Long id) {
        return supportService.getSupportById(id);
    }

    @PostMapping
    public CustomerSupport createSupport(@RequestBody CustomerSupport support) {
        return supportService.createSupport(support);
    }

    @PutMapping("/{id}")
    public CustomerSupport updateSupport(@PathVariable Long id, @RequestBody CustomerSupport support) {
        return supportService.updateSupport(id, support);
    }

    @DeleteMapping("/{id}")
    public void deleteSupport(@PathVariable Long id) {
        supportService.deleteSupport(id);
    }
}
