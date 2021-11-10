package com.heymeowcat.tailznet.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ads")
public class Ads {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Adid")
    private int adId;

    @Column(name = "users_idusers")
    private int userId;

    @Column(name = "forhowmanyusers")
    private int forHowManyUsers;

    @Column(name = "forhowmanyhours")
    private int forHowManyHours;

    @Column(name = "rate")
    private double rate;

    @Column(name = "total")
    private int total;

    @Column(name = "status")
    private String status;

    @Column(name = "adcategory")
    private int adCategory;

    @Column(name = "src")
    private String src;

    @Column(name = "link")
    private String link;

    public Ads() {}

    public Ads(int adId, int userId, int forHowManyUsers, int forHowManyHours,
               double rate, int total, String status, int adCategory, String src, String link) {
        this.adId = adId;
        this.userId = userId;
        this.forHowManyUsers = forHowManyUsers;
        this.forHowManyHours = forHowManyHours;
        this.rate = rate;
        this.total = total;
        this.status = status;
        this.adCategory = adCategory;
        this.src = src;
        this.link = link;
    }

    public int getAdId() { return adId; }
    public void setAdId(int adId) { this.adId = adId; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public int getForHowManyUsers() { return forHowManyUsers; }
    public void setForHowManyUsers(int forHowManyUsers) { this.forHowManyUsers = forHowManyUsers; }

    public int getForHowManyHours() { return forHowManyHours; }
    public void setForHowManyHours(int forHowManyHours) { this.forHowManyHours = forHowManyHours; }

    public double getRate() { return rate; }
    public void setRate(double rate) { this.rate = rate; }

    public int getTotal() { return total; }
    public void setTotal(int total) { this.total = total; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public int getAdCategory() { return adCategory; }
    public void setAdCategory(int adCategory) { this.adCategory = adCategory; }

    public String getSrc() { return src; }
    public void setSrc(String src) { this.src = src; }

    public String getLink() { return link; }
    public void setLink(String link) { this.link = link; }
}
