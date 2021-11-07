package com.heymeowcat.tailznet.dao;

import com.heymeowcat.tailznet.entities.AdTiming;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AdTimingDAO extends BaseDAO<AdTiming> {

    private static final Logger logger = LoggerFactory.getLogger(AdTimingDAO.class);

    public AdTimingDAO() {
        super(AdTiming.class);
    }

    public List<AdTiming> findByAd(int adId) {
        return findByProperty("adId", adId);
    }
}
