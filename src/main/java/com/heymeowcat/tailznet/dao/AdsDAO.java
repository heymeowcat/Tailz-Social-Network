package com.heymeowcat.tailznet.dao;

import com.heymeowcat.tailznet.HibernateUtil;
import com.heymeowcat.tailznet.entities.Ads;
import java.util.List;
import org.hibernate.SQLQuery;
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

    @SuppressWarnings("unchecked")
    public List<Object[]> getActiveAdsWithTiming(int adCategory, boolean useCategory) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            StringBuilder sql = new StringBuilder(
                    "SELECT a.Adid, a.src, a.link, a.users_idusers, a.forhowmanyusers, a.forhowmanyhours, " +
                    "TIMEDIFF(at.adendtime, CURRENT_TIMESTAMP) AS time_diff " +
                    "FROM ads a LEFT JOIN adtiming at ON a.Adid = at.Ads_Adid " +
                    "WHERE a.status='4'");
            if (useCategory && adCategory != 1) {
                sql.append(" AND a.adcategory=:adCategory");
            }
            SQLQuery query = session.createSQLQuery(sql.toString());
            if (useCategory && adCategory != 1) {
                query.setInteger("adCategory", adCategory);
            }
            List<Object[]> list = query.list();
            tx.commit();
            return list;
        } catch (Exception e) {
            tx.rollback();
            logger.error("DAO operation failed: {}", e.getMessage());
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    public List<Object[]> getUserActiveAdsWithTiming(int userId) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            String sql = "SELECT a.Adid, a.src, a.link, a.users_idusers, a.forhowmanyusers, a.forhowmanyhours, " +
                    "TIMEDIFF(at.adendtime, CURRENT_TIMESTAMP) AS time_diff " +
                    "FROM ads a LEFT JOIN adtiming at ON a.Adid = at.Ads_Adid " +
                    "WHERE a.status='4' AND a.users_idusers=? " +
                    "ORDER BY at.adendtime DESC";
            Query query = session.createSQLQuery(sql);
            query.setInteger(0, userId);
            List<Object[]> list = query.list();
            tx.commit();
            return list;
        } catch (Exception e) {
            tx.rollback();
            logger.error("DAO operation failed: {}", e.getMessage());
            return null;
        }
    }

    public void updateAdStatus(int adId, String status) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            session.createSQLQuery(
                    "UPDATE ads SET status = :status WHERE Adid = :adId")
                    .setString("status", status)
                    .setInteger("adId", adId)
                    .executeUpdate();
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            logger.error("DAO operation failed: {}", e.getMessage());
        }
    }

    public int getUserAdCategory(int userId) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            Query query = session.createSQLQuery("SELECT adcategory FROM user_followed_ad_catergories WHERE users_idusers = :userId");
            query.setInteger("userId", userId);
            List<?> list = query.list();
            tx.commit();
            if (list.isEmpty()) {
                return 1;
            }
            Object result = list.get(0);
            if (result == null) return 1;
            return ((Number) result).intValue();
        } catch (Exception e) {
            tx.rollback();
            logger.error("DAO operation failed: {}", e.getMessage());
            return 1;
        }
    }

    public int getUserPreference(int userId) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            Query query = session.createSQLQuery(
                    "SELECT Preference FROM uap WHERE users_idusers = :userId");
            query.setInteger("userId", userId);
            List<?> list = query.list();
            tx.commit();
            if (!list.isEmpty() && list.get(0) != null) {
                return ((Number) list.get(0)).intValue();
            }
            return 1;
        } catch (Exception e) {
            tx.rollback();
            logger.error("DAO operation failed: {}", e.getMessage());
            return 1;
        }
    }
}
