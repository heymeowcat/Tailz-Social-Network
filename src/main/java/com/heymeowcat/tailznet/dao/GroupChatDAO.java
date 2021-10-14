package com.heymeowcat.tailznet.dao;

import com.heymeowcat.tailznet.HibernateUtil;
import com.heymeowcat.tailznet.entities.GroupChat;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

public class GroupChatDAO extends BaseDAO<GroupChat> {

    public GroupChatDAO() {
        super(GroupChat.class);
    }

    @SuppressWarnings("unchecked")
    public List<GroupChat> findByGroup(int groupId) {
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
            e.printStackTrace();
            return null;
        }
    }
}
