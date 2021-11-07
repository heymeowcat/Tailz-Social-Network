package com.heymeowcat.tailznet.dao;

import com.heymeowcat.tailznet.HibernateUtil;
import com.heymeowcat.tailznet.entities.GroupMembers;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GroupMembersDAO extends BaseDAO<GroupMembers> {

    private static final Logger logger = LoggerFactory.getLogger(GroupMembersDAO.class);

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
            logger.error("DAO operation failed: {}", e.getMessage());
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
            logger.error("DAO operation failed: {}", e.getMessage());
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
            logger.error("DAO operation failed: {}", e.getMessage());
            return false;
        }
    }

    public void deleteByGroup(String groupId) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            session.createQuery("DELETE FROM GroupMembers WHERE groupId = :groupId")
                    .setString("groupId", groupId).executeUpdate();
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            logger.error("DAO operation failed: {}", e.getMessage());
        }
    }
}
