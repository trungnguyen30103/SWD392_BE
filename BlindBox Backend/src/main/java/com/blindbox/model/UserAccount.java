package com.blindbox.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "user_account")
public class UserAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_account_id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "balance", precision = 10, scale = 2, nullable = false)
    private BigDecimal balance;


    public UserAccount() {
    }

    public UserAccount(User user, BigDecimal balance) {
        this.user = user;
        this.balance = balance;
    }
}
