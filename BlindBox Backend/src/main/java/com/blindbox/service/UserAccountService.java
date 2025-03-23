package com.blindbox.service;

import java.math.BigDecimal;

public interface UserAccountService {


    void createUserAccount(Integer userId, BigDecimal initialBalance);


}
