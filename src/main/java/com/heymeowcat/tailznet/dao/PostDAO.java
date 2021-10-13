package com.heymeowcat.tailznet.dao;

import com.heymeowcat.tailznet.HibernateUtil;
import com.heymeowcat.tailznet.entities.Post;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

public class PostDAO extends BaseDAO<Post> {

    public PostDAO() {
        super(Post.class);
    }

    public Post findById(int id) {
        return super.findById(id);
    }

    @SuppressWarnings("unchecked")
    public List<Post> findByUser(int userId) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            Criteria criteria = session.createCriteria(Post.class);
            criteria.add(Restrictions.eq("postedby", userId));
            criteria.addOrder(Order.desc("posttime"));
            List<Post> list = criteria.list();
            tx.commit();
            return list;
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    public List<Post> findByPrivacyAndFollowers(int userId, String privacy) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            Criteria criteria = session.createCriteria(Post.class);
            criteria.add(Restrictions.eq("postedby", userId));
            criteria.add(Restrictions.eq("privacy", privacy));
            criteria.addOrder(Order.desc("posttime"));
            List<Post> list = criteria.list();
            tx.commit();
            return list;
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
            return null;
        }
    }
}
