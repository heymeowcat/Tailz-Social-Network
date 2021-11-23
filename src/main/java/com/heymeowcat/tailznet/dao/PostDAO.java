package com.heymeowcat.tailznet.dao;

import com.heymeowcat.tailznet.HibernateUtil;
import com.heymeowcat.tailznet.entities.Post;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PostDAO extends BaseDAO<Post> {

    private static final Logger logger = LoggerFactory.getLogger(PostDAO.class);

    public PostDAO() {
        super(Post.class);
    }

    public Post findById(int id) {
        return super.findById(id);
    }

    @SuppressWarnings("unchecked")
    public List<Post> findByUser(int userId) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            Criteria criteria = session.createCriteria(Post.class);
            criteria.add(Restrictions.eq("userId", userId));
            criteria.addOrder(Order.desc("postTime"));
            List<Post> list = criteria.list();
            tx.commit();
            return list;
        } catch (Exception e) {
            tx.rollback();
            logger.error("DAO operation failed: {}", e.getMessage());
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    public List<Post> findByPrivacyAndFollowers(int userId, String privacy) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            Criteria criteria = session.createCriteria(Post.class);
            criteria.add(Restrictions.eq("userId", userId));
            criteria.add(Restrictions.eq("postPrivacy", privacy));
            criteria.addOrder(Order.desc("postTime"));
            List<Post> list = criteria.list();
            tx.commit();
            return list;
        } catch (Exception e) {
            tx.rollback();
            logger.error("DAO operation failed: {}", e.getMessage());
            return null;
        }
    }

    public int getPostCount(int userId) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            Criteria criteria = session.createCriteria(Post.class);
            criteria.add(Restrictions.eq("userId", userId));
            criteria.setProjection(Projections.rowCount());
            List<?> list = criteria.list();
            tx.commit();
            return list.isEmpty() ? 0 : ((Long) list.get(0)).intValue();
        } catch (Exception e) {
            tx.rollback();
            logger.error("DAO operation failed: {}", e.getMessage());
            return 0;
        }
    }

    public void updatePostPrivacyForUser(int userId, int privacy) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            session.createQuery("UPDATE Post SET postPrivacy = :privacy WHERE userId = :userId")
                    .setInteger("privacy", privacy)
                    .setInteger("userId", userId)
                    .executeUpdate();
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            logger.error("DAO operation failed: {}", e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public List<Object[]> getFeedForUser(int userId) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            SQLQuery query = session.createSQLQuery(
                    "SELECT p.*, u.firstname, u.lastname, upp.image, " +
                    "DATE(p.post_time) as post_date, TIME(p.post_time) as post_time_val " +
                    "FROM post p " +
                    "JOIN users u ON p.users_idusers = u.idusers " +
                    "JOIN user_profile_pic upp ON p.users_idusers = upp.users_idusers " +
                    "WHERE p.Post_Privacy='1' and p.users_idusers = ANY " +
                    "(SELECT receiver FROM follow WHERE sender = :uid and Post_Privacy='1') " +
                    "OR p.users_idusers = :uid and p.Post_Privacy='1' " +
                    "ORDER BY p.post_time DESC");
            query.setInteger("uid", userId);
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
    public List<Object[]> searchPostsWithDetails(String encryptedKeyword, String wildcardKeyword) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            String sql = "SELECT p.idpost, p.post_type, p.post_heading, p.post_detial, p.post_image, " +
                    "p.post_privacy, p.post_time, p.users_idusers, u.firstname, u.lastname, upp.image " +
                    "FROM post p " +
                    "JOIN users u ON p.users_idusers = u.idusers " +
                    "JOIN user_profile_pic upp ON u.idusers = upp.users_idusers " +
                    "WHERE p.post_privacy = 1 " +
                    "AND p.idpost IN (SELECT DISTINCT idpost FROM post WHERE post_heading = ? OR post_detial = ? " +
                    "OR users_idusers IN (SELECT idusers FROM users WHERE firstname LIKE ? OR lastname LIKE ? " +
                    "OR concat(firstname,' ',lastname) LIKE ? OR concat(firstname,lastname) LIKE ?))";
            SQLQuery query = session.createSQLQuery(sql);
            query.setString(1, encryptedKeyword);
            query.setString(2, encryptedKeyword);
            query.setString(3, wildcardKeyword);
            query.setString(4, wildcardKeyword);
            query.setString(5, wildcardKeyword);
            query.setString(6, wildcardKeyword);
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
    public List<Object[]> getPostsByUserWithDetails(int userId) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            SQLQuery query = session.createSQLQuery(
                    "SELECT p.*, u.firstname, u.lastname, upp.image, " +
                    "DATE(p.post_time) as post_date, TIME(p.post_time) as post_time_val " +
                    "FROM post p " +
                    "JOIN users u ON p.users_idusers = u.idusers " +
                    "JOIN user_profile_pic upp ON p.users_idusers = upp.users_idusers " +
                    "WHERE p.users_idusers = :userId " +
                    "ORDER BY p.post_time DESC");
            query.setInteger("userId", userId);
            List<Object[]> list = query.list();
            tx.commit();
            return list;
        } catch (Exception e) {
            tx.rollback();
            logger.error("DAO operation failed: {}", e.getMessage());
            return null;
        }
    }
}
