package com.blindbox.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "result")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Result {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "result_id")
    private Integer resultID;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;  // Liên kết với bảng Order

    @ManyToOne
    @JoinColumn(name = "blindbox_id", referencedColumnName = "blindboxID", nullable = false)
    private Blindbox blindbox;  // Liên kết với bảng Blindbox

    @Column(name = "result_text", columnDefinition = "TEXT")
    private String resultText;  // Kết quả của đơn hàng (ví dụ: kết quả mở blindbox)

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();  // Thời gian khi kết quả được tạo ra
}
