package com.heymeowcat.tailznet.dao;

import com.heymeowcat.tailznet.HibernateUtil;
import com.heymeowcat.tailznet.entities.Chat;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

public class ChatDAO extends BaseDAO<Chat> {

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
                Restrictions.and(Restrictions.eq("sender", senderId), Restrictions.eq("receiver", receiverId)),
                Restrictions.and(Restrictions.eq("sender", receiverId), Restrictions.eq("receiver", senderId))
            ));
            criteria.addOrder(Order.asc("time"));
            List<Chat> list = criteria.list();
            tx.commit();
            return list;
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
            return null;
        }
    }

    public int getUnreadCount(int receiverId, int senderId) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            Criteria criteria = session.createCriteria(Chat.class);
            criteria.add(Restrictions.eq("receiver", receiverId));
            criteria.add(Restrictions.eq("sender", senderId));
            criteria.add(Restrictions.eq("status", "0"));
            List<Chat> list = criteria.list();
            tx.commit();
            return list.size();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
            return 0;
        }
    }
}
