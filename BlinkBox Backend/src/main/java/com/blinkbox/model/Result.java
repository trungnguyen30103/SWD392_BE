package com.blinkbox.model;

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
    @JoinColumn(name = "blinkboxid", referencedColumnName = "blinkboxID")
    private Blinkbox blinkbox;  // Giả sử bạn đã có lớp Blinkbox

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

    public Blinkbox getBlinkbox() {
        return blinkbox;
    }

    public void setBlinkbox(Blinkbox blinkbox) {
        this.blinkbox = blinkbox;
    }

    public LocalDateTime getDrawTime() {
        return drawTime;
    }

    public void setDrawTime(LocalDateTime drawTime) {
        this.drawTime = drawTime;
    }
}
