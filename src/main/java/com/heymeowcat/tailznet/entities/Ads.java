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

    @Column(name = "status")
    private String status;

    @Column(name = "forhowmanyhours")
    private int forHowManyHours;

    public Ads() {}

    public Ads(int adId, String status, int forHowManyHours) {
        this.adId = adId;
        this.status = status;
        this.forHowManyHours = forHowManyHours;
    }

    public int getAdId() { return adId; }
    public void setAdId(int adId) { this.adId = adId; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public int getForHowManyHours() { return forHowManyHours; }
    public void setForHowManyHours(int forHowManyHours) { this.forHowManyHours = forHowManyHours; }
}
