package com.heymeowcat.tailznet.dao;

import com.heymeowcat.tailznet.HibernateUtil;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class BaseDAO<T> {

    private static final Logger logger = LoggerFactory.getLogger(BaseDAO.class);

    protected Class<T> entityClass;

    public BaseDAO(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public T findById(int id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            T entity = (T) session.get(entityClass, id);
            tx.commit();
            return entity;
        } catch (Exception e) {
            tx.rollback();
            logger.error("Database operation failed: {}", e.getMessage());
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    public List<T> findAll() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            List<T> list = session.createCriteria(entityClass).list();
            tx.commit();
            return list;
        } catch (Exception e) {
            tx.rollback();
            logger.error("Database operation failed: {}", e.getMessage());
            return null;
        }
    }

    public void save(T entity) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            session.save(entity);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            logger.error("Database operation failed: {}", e.getMessage());
        }
    }

    public void update(T entity) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            session.update(entity);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            logger.error("Database operation failed: {}", e.getMessage());
        }
    }

    public void delete(T entity) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            session.delete(entity);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            logger.error("Database operation failed: {}", e.getMessage());
        }
    }

    public void deleteById(int id) {
        T entity = findById(id);
        if (entity != null) {
            delete(entity);
        }
    }

    @SuppressWarnings("unchecked")
    public List<T> findByProperty(String propertyName, Object value) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            Criteria criteria = session.createCriteria(entityClass);
            criteria.add(Restrictions.eq(propertyName, value));
            List<T> list = criteria.list();
            tx.commit();
            return list;
        } catch (Exception e) {
            tx.rollback();
            logger.error("Database operation failed: {}", e.getMessage());
            return null;
        }
    }
}
