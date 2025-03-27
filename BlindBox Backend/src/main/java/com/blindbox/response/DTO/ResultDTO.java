package com.blindbox.response.DTO;

import com.blindbox.enums.Blindbox.Rarity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ResultDTO {
    Integer orderID;
    Integer blindboxID;
    Integer blindboxItemID;
    String imageUrl;
    Rarity rarity;
    LocalDateTime createdAt;
}
