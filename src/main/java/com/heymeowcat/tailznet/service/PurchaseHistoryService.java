package com.heymeowcat.tailznet.service;

import com.heymeowcat.tailznet.dao.PurchaseHistoryDAO;
import com.heymeowcat.tailznet.entities.PurchaseHistory;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PurchaseHistoryService {
    private static final Logger logger = LoggerFactory.getLogger(PurchaseHistoryService.class);

    private final PurchaseHistoryDAO purchaseHistoryDAO;

    public PurchaseHistoryService() {
        this.purchaseHistoryDAO = new PurchaseHistoryDAO();
    }

    public List<PurchaseHistory> getUserPurchases(int userId) {
        return purchaseHistoryDAO.findByUser(userId);
    }

    public void savePurchase(PurchaseHistory purchase) {
        purchaseHistoryDAO.save(purchase);
    }

    public void savePurchase(int userId, double rate, int total, int hours) {
        PurchaseHistory purchase = new PurchaseHistory();
        purchase.setUserId(userId);
        purchase.setRate(rate);
        purchase.setTotal(total);
        purchase.setStatus("1");
        purchase.setHours(hours);
        purchaseHistoryDAO.save(purchase);
    }
}
