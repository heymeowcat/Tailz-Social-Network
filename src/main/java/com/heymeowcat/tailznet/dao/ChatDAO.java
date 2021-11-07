package com.heymeowcat.tailznet.dao;

import com.heymeowcat.tailznet.HibernateUtil;
import com.heymeowcat.tailznet.entities.Chat;
import java.util.List;
import org.hibernate.Criteria;
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
