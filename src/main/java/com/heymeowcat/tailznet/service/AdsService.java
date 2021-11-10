package com.heymeowcat.tailznet.service;

import com.heymeowcat.tailznet.dao.AdsDAO;
import com.heymeowcat.tailznet.entities.Ads;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AdsService {
    private static final Logger logger = LoggerFactory.getLogger(AdsService.class);

    private final AdsDAO adsDAO;
    private final PurchaseHistoryService purchaseHistoryService;

    public AdsService() {
        this.adsDAO = new AdsDAO();
        this.purchaseHistoryService = new PurchaseHistoryService();
    }

    public List<Ads> getActiveAds() {
        return adsDAO.findByStatus("1");
    }

    public void processPurchase(int userId, int spaces, int hours) {
        double rate = adsDAO.getAppHpiRate();
        int usersInSystem = adsDAO.getActiveRegularUserCount();
        int total = (int) (rate * hours);

        for (int i = 0; i < spaces; i++) {
            purchaseHistoryService.savePurchase(userId, rate, total, hours);
            adsDAO.createPurchaseAd(userId, usersInSystem, hours);
        }
    }

    public double getAppHpiRate() {
        return adsDAO.getAppHpiRate();
    }

    public int getActiveRegularUserCount() {
        return adsDAO.getActiveRegularUserCount();
    }

    public void saveAd(Ads ad) {
        adsDAO.save(ad);
    }

    public void updateAd(Ads ad) {
        adsDAO.updateAd(ad);
    }
}
