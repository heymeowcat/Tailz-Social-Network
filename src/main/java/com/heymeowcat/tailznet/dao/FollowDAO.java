package com.heymeowcat.tailznet.dao;

import com.heymeowcat.tailznet.HibernateUtil;
import com.heymeowcat.tailznet.entities.Follow;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

public class FollowDAO extends BaseDAO<Follow> {

    public FollowDAO() {
        super(Follow.class);
    }

    public int getFollowerCount(int userId) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            Criteria criteria = session.createCriteria(Follow.class);
            criteria.add(Restrictions.eq("receiver", userId));
            criteria.setProjection(Projections.rowCount());
            List<?> list = criteria.list();
            tx.commit();
            return list.isEmpty() ? 0 : ((Long) list.get(0)).intValue();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
            return 0;
        }
    }

    public int getFollowingCount(int userId) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            Criteria criteria = session.createCriteria(Follow.class);
            criteria.add(Restrictions.eq("sender", userId));
            criteria.setProjection(Projections.rowCount());
            List<?> list = criteria.list();
            tx.commit();
            return list.isEmpty() ? 0 : ((Long) list.get(0)).intValue();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
            return 0;
        }
    }

    public boolean isFollowing(int senderId, int receiverId) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            Criteria criteria = session.createCriteria(Follow.class);
            criteria.add(Restrictions.eq("sender", senderId));
            criteria.add(Restrictions.eq("receiver", receiverId));
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
