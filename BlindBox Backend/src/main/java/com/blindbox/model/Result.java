package com.blindbox.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "results")
public class Result {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long resultID;

    @ManyToOne
    @JoinColumn(name = "orderid", referencedColumnName = "orderID")
    private Order order;  // Giả sử bạn đã có lớp Order

    @ManyToOne
    @JoinColumn(name = "blindboxid", referencedColumnName = "blindboxID")
    private Blindbox blindbox;  // Giả sử bạn đã có lớp Blindbox

    private LocalDateTime drawTime;

    // Getters and Setters
    public Long getResultID() {
        return resultID;
    }

    public void setResultID(Long resultID) {
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

    public LocalDateTime getDrawTime() {
        return drawTime;
    }

    public void setDrawTime(LocalDateTime drawTime) {
        this.drawTime = drawTime;
    }
}
