package com.blinkbox.service;

import com.blinkbox.model.CustomerSupport;
import java.util.List;

public interface CustomerSupportService {
    List<CustomerSupport> getAllSupports();
    CustomerSupport getSupportById(Long id);
    CustomerSupport createSupport(CustomerSupport support);
    CustomerSupport updateSupport(Long id, CustomerSupport support);
    void deleteSupport(Long id);
}
