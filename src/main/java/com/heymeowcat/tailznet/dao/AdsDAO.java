package com.heymeowcat.tailznet.dao;

import com.heymeowcat.tailznet.entities.Ads;
import java.util.List;

public class AdsDAO extends BaseDAO<Ads> {

    public AdsDAO() {
        super(Ads.class);
    }

    public Ads findById(int adId) {
        return super.findById(adId);
    }

    public List<Ads> findByStatus(String status) {
        return findByProperty("status", status);
    }
}
