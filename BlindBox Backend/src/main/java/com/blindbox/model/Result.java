package com.blindbox.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "result")
public class Result {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "result_id")
    private Integer resultID;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;  // Liên kết với bảng Order

    @ManyToOne
    @JoinColumn(name = "blindbox_id", nullable = false)
    private Blindbox blindbox;  // Liên kết với bảng Blindbox

    @Column(name = "result_text", columnDefinition = "TEXT")
    private String resultText;  // Kết quả của đơn hàng (ví dụ: kết quả mở blindbox)

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();  // Thời gian khi kết quả được tạo ra

    // Constructors
    public Result() {
    }

    public Result(Order order, Blindbox blindbox, String resultText) {
        this.order = order;
        this.blindbox = blindbox;
        this.resultText = resultText;
        this.createdAt = LocalDateTime.now();
    }

    // Getters and Setters
    public Integer getResultID() {
        return resultID;
    }

    public void setResultID(Integer resultID) {
        this.resultID = resultID;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Blindbox getBlindbox() {
        return blindbox;
    }

    public void setBlindbox(Blindbox blindbox) {
        this.blindbox = blindbox;
    }

    public String getResultText() {
        return resultText;
    }

    public void setResultText(String resultText) {
        this.resultText = resultText;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
