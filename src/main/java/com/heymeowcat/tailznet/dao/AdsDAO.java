package com.heymeowcat.tailznet.dao;

import com.heymeowcat.tailznet.entities.Ads;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AdsDAO extends BaseDAO<Ads> {

    private static final Logger logger = LoggerFactory.getLogger(AdsDAO.class);

    public AdsDAO() {
        super(Ads.class);
    }

    public Ads findById(int adId) {
        return super.findById(adId);
    }

    public List<Ads> findByStatus(String status) {
        return findByProperty("status", status);
    }

    public void updateAd(Ads ads) {
        ads.setStatus("3");
        update(ads);
    }
}
