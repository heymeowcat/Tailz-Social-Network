package com.heymeowcat.tailznet.dao;

import com.heymeowcat.tailznet.HibernateUtil;
import com.heymeowcat.tailznet.entities.GroupMembers;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

public class GroupMembersDAO extends BaseDAO<GroupMembers> {

    public GroupMembersDAO() {
        super(GroupMembers.class);
    }

    @SuppressWarnings("unchecked")
    public List<GroupMembers> findByGroup(String groupId) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            Criteria criteria = session.createCriteria(GroupMembers.class);
            criteria.add(Restrictions.eq("groupId", groupId));
            List<GroupMembers> list = criteria.list();
            tx.commit();
            return list;
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    public List<GroupMembers> findByUser(int userId) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            Criteria criteria = session.createCriteria(GroupMembers.class);
            criteria.add(Restrictions.eq("memberId", userId));
            List<GroupMembers> list = criteria.list();
            tx.commit();
            return list;
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
            return null;
        }
    }

    public boolean isMember(int userId, String groupId) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            Criteria criteria = session.createCriteria(GroupMembers.class);
            criteria.add(Restrictions.eq("memberId", userId));
            criteria.add(Restrictions.eq("groupId", groupId));
            List<?> list = criteria.list();
            tx.commit();
            return !list.isEmpty();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
            return false;
        }
    }
}
