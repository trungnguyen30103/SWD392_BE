package com.blinkbox.service.impl;

import com.blinkbox.model.CustomerSupport;
import com.blinkbox.repository.CustomerSupportRepository;
import com.blinkbox.service.CustomerSupportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerSupportServiceImpl implements CustomerSupportService {

    @Autowired
    private CustomerSupportRepository supportRepository;

    @Override
    public List<CustomerSupport> getAllSupports() {
        return supportRepository.findAll();
    }

    @Override
    public CustomerSupport getSupportById(Long id) {
        Optional<CustomerSupport> support = supportRepository.findById(id);
        return support.orElse(null);
    }

    @Override
    public CustomerSupport createSupport(CustomerSupport support) {
        return supportRepository.save(support);
    }

    @Override
    public CustomerSupport updateSupport(Long id, CustomerSupport support) {
        if (supportRepository.existsById(id)) {
            support.setSupportID(id);
            return supportRepository.save(support);
        }
        return null;
    }

    @Override
    public void deleteSupport(Long id) {
        supportRepository.deleteById(id);
    }
}
