package com.heymeowcat.tailznet.dao;

import com.heymeowcat.tailznet.HibernateUtil;
import com.heymeowcat.tailznet.entities.User;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

public class UserDAO extends BaseDAO<User> {

    public UserDAO() {
        super(User.class);
    }

    public User findByEmail(String email) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            Criteria criteria = session.createCriteria(User.class);
            criteria.add(Restrictions.eq("email", email));
            List<User> list = criteria.list();
            tx.commit();
            if (!list.isEmpty()) {
                return list.get(0);
            }
            return null;
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
            return null;
        }
    }

    public User findById(int id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            User user = (User) session.get(User.class, id);
            tx.commit();
            return user;
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
            return null;
        }
    }

    public String getFirstName(int userId) {
        User user = findById(userId);
        return user != null ? user.getFirstName() : "";
    }

    public String getLastName(int userId) {
        User user = findById(userId);
        return user != null ? user.getLastName() : "";
    }

    public String getFullName(int userId) {
        User user = findById(userId);
        if (user == null) return "";
        return user.getFirstName() + " " + user.getLastName();
    }
}
