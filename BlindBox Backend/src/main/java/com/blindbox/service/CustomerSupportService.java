package com.blindbox.service;

import com.blindbox.model.CustomerSupport;
import java.util.List;

public interface CustomerSupportService {
    List<CustomerSupport> getAllSupports();
    CustomerSupport getSupportById(Integer id);
    CustomerSupport createSupport(CustomerSupport support);
    CustomerSupport updateSupport(Integer id, CustomerSupport support);
    void deleteSupport(Integer id);
}
