package com.heymeowcat.tailznet.dao;

import com.heymeowcat.tailznet.HibernateUtil;
import com.heymeowcat.tailznet.entities.GroupEntity;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

public class GroupEntityDAO extends BaseDAO<GroupEntity> {

    public GroupEntityDAO() {
        super(GroupEntity.class);
    }

    public GroupEntity findById(String id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            GroupEntity entity = (GroupEntity) session.get(GroupEntity.class, id);
            tx.commit();
            return entity;
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    public List<GroupEntity> findByOwner(int ownerId) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            Criteria criteria = session.createCriteria(GroupEntity.class);
            criteria.add(Restrictions.eq("groupAdmin", ownerId));
            List<GroupEntity> list = criteria.list();
            tx.commit();
            return list;
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
            return null;
        }
    }

    public void deleteById(String id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            session.delete(session.get(GroupEntity.class, id));
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        }
    }
}
