package com.heymeowcat.tailznet.dao;

import com.heymeowcat.tailznet.entities.PurchaseHistory;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PurchaseHistoryDAO extends BaseDAO<PurchaseHistory> {

    private static final Logger logger = LoggerFactory.getLogger(PurchaseHistoryDAO.class);

    public PurchaseHistoryDAO() {
        super(PurchaseHistory.class);
    }

    public List<PurchaseHistory> findByUser(int userId) {
        return findByProperty("userId", userId);
    }
}
