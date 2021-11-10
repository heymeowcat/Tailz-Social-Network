package com.heymeowcat.tailznet.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "purchase_history")
public class PurchaseHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idpurchase_history")
    private int id;

    @Column(name = "users_idusers")
    private int userId;

    @Column(name = "rate")
    private double rate;

    @Column(name = "total")
    private int total;

    @Column(name = "status")
    private String status;

    @Column(name = "hours")
    private int hours;

    public PurchaseHistory() {}

    public PurchaseHistory(int id, int userId, double rate, int total, String status, int hours) {
        this.id = id;
        this.userId = userId;
        this.rate = rate;
        this.total = total;
        this.status = status;
        this.hours = hours;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public double getRate() { return rate; }
    public void setRate(double rate) { this.rate = rate; }

    public int getTotal() { return total; }
    public void setTotal(int total) { this.total = total; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public int getHours() { return hours; }
    public void setHours(int hours) { this.hours = hours; }
}
