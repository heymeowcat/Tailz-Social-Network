package com.heymeowcat.tailznet.dao;

import com.heymeowcat.tailznet.entities.AdTiming;
import java.util.List;

public class AdTimingDAO extends BaseDAO<AdTiming> {

    public AdTimingDAO() {
        super(AdTiming.class);
    }

    public List<AdTiming> findByAd(int adId) {
        return findByProperty("adId", adId);
    }
}
