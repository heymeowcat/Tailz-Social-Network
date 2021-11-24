package com.heymeowcat.tailznet.dao;

import com.heymeowcat.tailznet.HibernateUtil;
import com.heymeowcat.tailznet.entities.Chat;
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

public class ChatDAO extends BaseDAO<Chat> {

    private static final Logger logger = LoggerFactory.getLogger(ChatDAO.class);

    public ChatDAO() {
        super(Chat.class);
    }

    @SuppressWarnings("unchecked")
    public List<Chat> findMessages(int senderId, int receiverId) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            Criteria criteria = session.createCriteria(Chat.class);
            criteria.add(Restrictions.or(
                Restrictions.and(Restrictions.eq("userSender", senderId), Restrictions.eq("userReceiver", receiverId)),
                Restrictions.and(Restrictions.eq("userSender", receiverId), Restrictions.eq("userReceiver", senderId))
            ));
            criteria.addOrder(Order.asc("chatDatetime"));
            List<Chat> list = criteria.list();
            tx.commit();
            return list;
        } catch (Exception e) {
            tx.rollback();
            logger.error("DAO operation failed: {}", e.getMessage());
            return null;
        }
    }

    public int getTotalUnreadCount(int receiverId) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            Criteria criteria = session.createCriteria(Chat.class);
            criteria.add(Restrictions.eq("userReceiver", receiverId));
            criteria.add(Restrictions.eq("chatlineStatus", 0));
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

    @SuppressWarnings("unchecked")
    public List<Object[]> getMessageOverview(int userId) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            String sql = "SELECT u.firstname, u.lastname, u.image, u.idusers, " +
                    "(SELECT COUNT(*) FROM chat WHERE chatlinestatus='0' AND user_sender=u.idusers AND users_receiver=:uid) AS unseen_sent, " +
                    "(SELECT COUNT(*) FROM chat WHERE chatlinestatus='0' AND users_receiver=u.idusers AND user_sender=:uid) AS unseen_recv, " +
                    "(SELECT COUNT(*) FROM chat WHERE chatlinestatus='1' AND users_receiver=u.idusers AND user_sender=:uid) AS seen_recv " +
                    "FROM users u JOIN user_profile_pic upp ON u.idusers = upp.users_idusers " +
                    "WHERE u.idusers IN (SELECT receiver FROM follow WHERE sender=:uid) " +
                    "AND u.idusers IN (SELECT sender FROM follow WHERE receiver=:uid) " +
                    "ORDER BY unseen_sent DESC, unseen_recv DESC, seen_recv DESC";
            SQLQuery query = session.createSQLQuery(sql);
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

    public int getUnreadCount(int receiverId, int senderId) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            Criteria criteria = session.createCriteria(Chat.class);
            criteria.add(Restrictions.eq("userReceiver", receiverId));
            criteria.add(Restrictions.eq("userSender", senderId));
            criteria.add(Restrictions.eq("chatlineStatus", 0));
            List<Chat> list = criteria.list();
            tx.commit();
            return list.size();
        } catch (Exception e) {
            tx.rollback();
            logger.error("DAO operation failed: {}", e.getMessage());
            return 0;
        }
    }
}
