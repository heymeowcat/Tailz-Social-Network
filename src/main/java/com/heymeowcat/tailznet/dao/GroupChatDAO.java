package com.heymeowcat.tailznet.dao;

import com.heymeowcat.tailznet.HibernateUtil;
import com.heymeowcat.tailznet.entities.GroupChat;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GroupChatDAO extends BaseDAO<GroupChat> {

    private static final Logger logger = LoggerFactory.getLogger(GroupChatDAO.class);

    public GroupChatDAO() {
        super(GroupChat.class);
    }

    public void deleteByGroup(String groupId) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            session.createQuery("DELETE FROM GroupChat WHERE groupId = :groupId")
                    .setString("groupId", groupId).executeUpdate();
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            logger.error("DAO operation failed: {}", e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public List<GroupChat> findByGroup(String groupId) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            Criteria criteria = session.createCriteria(GroupChat.class);
            criteria.add(Restrictions.eq("groupId", groupId));
            criteria.addOrder(Order.asc("time"));
            List<GroupChat> list = criteria.list();
            tx.commit();
            return list;
        } catch (Exception e) {
            tx.rollback();
            logger.error("DAO operation failed: {}", e.getMessage());
            return null;
        }
    }
}
