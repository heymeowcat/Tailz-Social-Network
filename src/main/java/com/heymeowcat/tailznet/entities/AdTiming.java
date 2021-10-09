package com.heymeowcat.tailznet.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "adtiming")
public class AdTiming {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idtiming")
    private int id;

    @Column(name = "Ads_Adid")
    private int adId;

    @Column(name = "adstartedtime")
    private String adStartedTime;

    @Column(name = "adendtime")
    private String adEndTime;

    public AdTiming() {}

    public AdTiming(int id, int adId, String adStartedTime, String adEndTime) {
        this.id = id;
        this.adId = adId;
        this.adStartedTime = adStartedTime;
        this.adEndTime = adEndTime;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getAdId() { return adId; }
    public void setAdId(int adId) { this.adId = adId; }

    public String getAdStartedTime() { return adStartedTime; }
    public void setAdStartedTime(String adStartedTime) { this.adStartedTime = adStartedTime; }

    public String getAdEndTime() { return adEndTime; }
    public void setAdEndTime(String adEndTime) { this.adEndTime = adEndTime; }
}
