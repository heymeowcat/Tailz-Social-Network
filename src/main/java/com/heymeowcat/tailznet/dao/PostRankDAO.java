package com.heymeowcat.tailznet.dao;

import com.heymeowcat.tailznet.entities.PostRank;
import com.heymeowcat.tailznet.HibernateUtil;
import java.util.List;
import org.hibernate.Session;
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
}
