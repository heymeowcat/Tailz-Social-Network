package com.heymeowcat.tailznet.dao;

import com.heymeowcat.tailznet.HibernateUtil;
import com.heymeowcat.tailznet.entities.User;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserDAO extends BaseDAO<User> {

    private static final Logger logger = LoggerFactory.getLogger(UserDAO.class);

    public UserDAO() {
        super(User.class);
    }

    public User findByEmail(String email) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            Criteria criteria = session.createCriteria(User.class);
            criteria.add(Restrictions.eq("email", email));
            List<User> list = criteria.list();
            tx.commit();
            if (!list.isEmpty()) {
                return list.get(0);
            }
            return null;
        } catch (Exception e) {
            tx.rollback();
            logger.error("DAO operation failed: {}", e.getMessage());
            return null;
        }
    }

    public User findById(int id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            User user = (User) session.get(User.class, id);
            tx.commit();
            return user;
        } catch (Exception e) {
            tx.rollback();
            logger.error("DAO operation failed: {}", e.getMessage());
            return null;
        }
    }

    public String getFirstName(int userId) {
        User user = findById(userId);
        return user != null ? user.getFirstName() : "";
    }

    public String getLastName(int userId) {
        User user = findById(userId);
        return user != null ? user.getLastName() : "";
    }

    public String getFullName(int userId) {
        User user = findById(userId);
        if (user == null) return "";
        return user.getFirstName() + " " + user.getLastName();
    }

    public boolean activateAccount(String email, String hash) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            Query query = session.createQuery(
                    "UPDATE User SET status = '1' WHERE email = :email AND hash = :hash AND status = '0'");
            query.setString("email", email);
            query.setString("hash", hash);
            int updated = query.executeUpdate();
            tx.commit();
            return updated > 0;
        } catch (Exception e) {
            tx.rollback();
            logger.error("DAO operation failed: {}", e.getMessage());
            return false;
        }
    }

    public void updateUserName(int userId, String firstName, String lastName) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            session.createQuery("UPDATE User SET firstName = :firstName, lastName = :lastName WHERE id = :userId")
                    .setString("firstName", firstName)
                    .setString("lastName", lastName)
                    .setInteger("userId", userId)
                    .executeUpdate();
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            logger.error("DAO operation failed: {}", e.getMessage());
        }
    }
}
