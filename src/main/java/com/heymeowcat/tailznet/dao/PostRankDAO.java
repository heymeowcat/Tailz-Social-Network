package com.heymeowcat.tailznet.dao;

import com.heymeowcat.tailznet.entities.PostRank;
import com.heymeowcat.tailznet.HibernateUtil;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SQLQuery;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PostRankDAO extends BaseDAO<PostRank> {

    private static final Logger logger = LoggerFactory.getLogger(PostRankDAO.class);

    public PostRankDAO() {
        super(PostRank.class);
    }

    public List<PostRank> findByPost(int postId) {
        return findByProperty("postId", postId);
    }

    public List<PostRank> findByLikedBy(int likedBy) {
        return findByProperty("likedBy", likedBy);
    }

    public PostRank findByPostAndLikedBy(int postId, int likedBy) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            PostRank result = (PostRank) session.createQuery(
                    "FROM PostRank WHERE postId = :postId AND likedBy = :likedBy")
                    .setInteger("postId", postId)
                    .setInteger("likedBy", likedBy)
                    .uniqueResult();
            tx.commit();
            return result;
        } catch (Exception e) {
            tx.rollback();
            logger.error("DAO operation failed: {}", e.getMessage());
            return null;
        }
    }

    public int getLikeCount(int postId) {
        List<PostRank> list = findByPost(postId);
        return list != null ? list.size() : 0;
    }

    public void deleteByPost(int postId) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            session.createQuery("DELETE FROM PostRank WHERE postId = :postId")
                    .setInteger("postId", postId).executeUpdate();
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            logger.error("DAO operation failed: {}", e.getMessage());
        }
    }

    public void deleteByPostAndLikedBy(int postId, int likedBy) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            session.createQuery("DELETE FROM PostRank WHERE postId = :postId AND likedBy = :likedBy")
                    .setInteger("postId", postId)
                    .setInteger("likedBy", likedBy)
                    .executeUpdate();
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            logger.error("DAO operation failed: {}", e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public List<Object[]> findLikedByPostWithUserDetails(int postId) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            SQLQuery query = session.createSQLQuery(
                    "SELECT DISTINCT u.firstname, u.lastname, u.idusers, upp.image " +
                    "FROM post_rank pr " +
                    "JOIN users u ON pr.likedby = u.idusers " +
                    "LEFT JOIN user_profile_pic upp ON u.idusers = upp.users_idusers " +
                    "WHERE pr.post_idpost = :postId");
            query.setInteger("postId", postId);
            List<Object[]> list = query.list();
            tx.commit();
            return list;
        } catch (Exception e) {
            tx.rollback();
            logger.error("DAO operation failed: {}", e.getMessage());
            return null;
        }
    }
}
