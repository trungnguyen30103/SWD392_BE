package com.blindbox.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "result")
public class Result {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer resultID;

    @ManyToOne
    @JoinColumn(name = "orderID", referencedColumnName = "orderID")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "blindboxID", referencedColumnName = "blindboxID")
    private Blindbox blindbox;

    private LocalDateTime drawTime;

    // Constructors
    public Result() {
    }

    public Result(Order order, Blindbox blindbox, LocalDateTime drawTime) {
        this.order = order;
        this.blindbox = blindbox;
        this.drawTime = drawTime;
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

    public LocalDateTime getDrawTime() {
        return drawTime;
    }

    public void setDrawTime(LocalDateTime drawTime) {
        this.drawTime = drawTime;
    }
}
