package com.blindbox.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "gacha_history")
public class GachaHistory {

    @Id

    private Integer id;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "item_name")
    private String itemName;

    @Column(name = "timestamp")
    private LocalDateTime timestamp;

    public GachaHistory(Integer userId, String itemName, String timestamp) {
        this.userId = userId;
        this.itemName = itemName;
        this.timestamp = LocalDateTime.parse(timestamp);
    }


    public GachaHistory() {
    }
}
