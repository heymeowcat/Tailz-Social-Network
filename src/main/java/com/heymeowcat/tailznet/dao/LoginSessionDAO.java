package com.heymeowcat.tailznet.dao;

import com.heymeowcat.tailznet.HibernateUtil;
import com.heymeowcat.tailznet.entities.LoginSession;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginSessionDAO extends BaseDAO<LoginSession> {

    private static final Logger logger = LoggerFactory.getLogger(LoginSessionDAO.class);

    public LoginSessionDAO() {
        super(LoginSession.class);
    }

    public List<LoginSession> findByUserLogin(int userLoginId) {
        return findByProperty("userLoginId", userLoginId);
    }

    public LoginSession findLatestByUserLogin(int userLoginId) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            Query query = session.createQuery(
                    "FROM LoginSession WHERE userLoginId = :userLoginId ORDER BY id DESC");
            query.setInteger("userLoginId", userLoginId);
            query.setMaxResults(1);
            List<?> list = query.list();
            tx.commit();
            return list.isEmpty() ? null : (LoginSession) list.get(0);
        } catch (Exception e) {
            tx.rollback();
            logger.error("DAO operation failed: {}", e.getMessage());
            return null;
        }
    }

    public LoginSession findLatestByUserId(int userId) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            String sql = "SELECT ls.idlogin_sessions FROM login_sessions ls " +
                    "JOIN user_login ul ON ls.user_login_iduser_login = ul.iduser_login " +
                    "WHERE ul.users_idusers = :userId ORDER BY ls.idlogin_sessions DESC LIMIT 1";
            SQLQuery query = session.createSQLQuery(sql);
            query.setInteger("userId", userId);
            List<?> list = query.list();
            if (list.isEmpty()) {
                tx.commit();
                return null;
            }
            Integer id = ((Number) list.get(0)).intValue();
            LoginSession result = (LoginSession) session.get(LoginSession.class, id);
            tx.commit();
            return result;
        } catch (Exception e) {
            tx.rollback();
            logger.error("DAO operation failed: {}", e.getMessage());
            return null;
        }
    }

    public void markLogout(int sessionId) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            session.createSQLQuery("UPDATE login_sessions SET out_time = CURRENT_TIMESTAMP WHERE idlogin_sessions = :sessionId")
                    .setInteger("sessionId", sessionId)
                    .executeUpdate();
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            logger.error("DAO operation failed: {}", e.getMessage());
        }
    }
}
