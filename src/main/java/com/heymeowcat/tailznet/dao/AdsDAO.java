package com.heymeowcat.tailznet.dao;

import com.heymeowcat.tailznet.HibernateUtil;
import com.heymeowcat.tailznet.entities.Ads;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
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

    public double getAppHpiRate() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            Query query = session.createSQLQuery("SELECT idAPPHPI FROM apphpi");
            List<?> list = query.list();
            tx.commit();
            if (!list.isEmpty()) {
                return Double.parseDouble(list.get(0).toString());
            }
            return 0;
        } catch (Exception e) {
            tx.rollback();
            logger.error("DAO operation failed: {}", e.getMessage());
            return 0;
        }
    }

    public int getActiveRegularUserCount() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            Query query = session.createSQLQuery(
                    "SELECT count(email) FROM users WHERE status='1' AND user_type_iduser_type='2'");
            List<?> list = query.list();
            tx.commit();
            if (!list.isEmpty()) {
                return Integer.parseInt(list.get(0).toString());
            }
            return 0;
        } catch (Exception e) {
            tx.rollback();
            logger.error("DAO operation failed: {}", e.getMessage());
            return 0;
        }
    }

    public void updateAd(Ads ads) {
        ads.setStatus("3");
        update(ads);
    }

    public void createPurchaseAd(int userId, int forHowManyUsers, int hours) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            session.createSQLQuery(
                    "INSERT INTO ads (users_idusers, forhowmanyusers, forhowmanyhours, status) VALUES (:uid, :users, :hours, '2')")
                    .setInteger("uid", userId)
                    .setInteger("users", forHowManyUsers)
                    .setInteger("hours", hours)
                    .executeUpdate();
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            logger.error("DAO operation failed: {}", e.getMessage());
        }
    }
}
