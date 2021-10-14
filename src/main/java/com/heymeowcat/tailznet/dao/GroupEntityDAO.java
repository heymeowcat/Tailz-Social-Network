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

    public GroupEntity findById(int id) {
        return super.findById(id);
    }

    @SuppressWarnings("unchecked")
    public List<GroupEntity> findByOwner(int ownerId) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            Criteria criteria = session.createCriteria(GroupEntity.class);
            criteria.add(Restrictions.eq("groupowner", ownerId));
            List<GroupEntity> list = criteria.list();
            tx.commit();
            return list;
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
            return null;
        }
    }
}
