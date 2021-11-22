package com.heymeowcat.tailznet.dao;

import com.heymeowcat.tailznet.HibernateUtil;
import com.heymeowcat.tailznet.entities.UserLogin;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserLoginDAO extends BaseDAO<UserLogin> {

    private static final Logger logger = LoggerFactory.getLogger(UserLoginDAO.class);

    public UserLoginDAO() {
        super(UserLogin.class);
    }

    public UserLogin findByUsername(String username) {
        List<UserLogin> list = findByProperty("username", username);
        return list != null && !list.isEmpty() ? list.get(0) : null;
    }

    public UserLogin findByUserId(int userId) {
        List<UserLogin> list = findByProperty("userId", userId);
        return list != null && !list.isEmpty() ? list.get(0) : null;
    }

    public int getLoginIdByUserId(int userId) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            Query query = session.createQuery(
                    "SELECT id FROM UserLogin WHERE userId = :userId ORDER BY id DESC");
            query.setInteger("userId", userId);
            query.setMaxResults(1);
            List<?> list = query.list();
            tx.commit();
            return list.isEmpty() ? 0 : ((Number) list.get(0)).intValue();
        } catch (Exception e) {
            tx.rollback();
            logger.error("DAO operation failed: {}", e.getMessage());
            return 0;
        }
    }
}
