package com.blindbox.service.impl;

import com.blindbox.model.CustomerSupport;
import com.blindbox.repository.CustomerSupportRepository;
import com.blindbox.service.CustomerSupportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerSupportServiceImpl implements CustomerSupportService {

    @Autowired
    private CustomerSupportRepository customerSupportRepository;

    @Override
    public List<CustomerSupport> getAllCustomerSupports() {
        return customerSupportRepository.findAll();
    }

    @Override
    public CustomerSupport getCustomerSupportById(Integer id) {
        Optional<CustomerSupport> customerSupport = customerSupportRepository.findById(id);
        return customerSupport.orElse(null);
    }

    @Override
    public CustomerSupport createCustomerSupport(CustomerSupport customerSupport) {
        return customerSupportRepository.save(customerSupport);
    }

    @Override
    public CustomerSupport updateCustomerSupport(Integer id, CustomerSupport customerSupport) {
        if (customerSupportRepository.existsById(id)) {
            customerSupport.setSupportId(id); // Đảm bảo ID không bị thay đổi
            return customerSupportRepository.save(customerSupport);
        }
        return null; // Nếu không tìm thấy ID để cập nhật
    }

    @Override
    public void deleteCustomerSupport(Integer id) {
        if (customerSupportRepository.existsById(id)) {
            customerSupportRepository.deleteById(id);
        }
    }
}
