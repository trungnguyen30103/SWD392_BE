package com.blindbox.service;

import com.blindbox.model.CustomerSupport;

import java.util.List;

public interface CustomerSupportService {
    List<CustomerSupport> getAllCustomerSupports();
    CustomerSupport getCustomerSupportById(Integer id);
    CustomerSupport createCustomerSupport(CustomerSupport customerSupport);
    CustomerSupport updateCustomerSupport(Integer id, CustomerSupport customerSupport);
    void deleteCustomerSupport(Integer id);
}
