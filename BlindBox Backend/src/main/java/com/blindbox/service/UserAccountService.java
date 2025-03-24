package com.blindbox.service;
import org.springframework.stereotype.Service;
@Service
public interface UserAccountService {


    void createUserAccount(Integer userId,double initialBalance);


}
