package com.heymeowcat.tailznet.dao;

import com.heymeowcat.tailznet.HibernateUtil;
import com.heymeowcat.tailznet.entities.PostComment;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class PostCommentDAO extends BaseDAO<PostComment> {

    public PostCommentDAO() {
        super(PostComment.class);
    }

    public List<PostComment> findByPost(int postId) {
        return findByProperty("postId", postId);
    }

    public List<PostComment> findByUser(int userId) {
        return findByProperty("commentBy", userId);
    }

    public void deleteByPost(int postId) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            session.createQuery("DELETE FROM PostComment WHERE postId = :postId")
                    .setInteger("postId", postId).executeUpdate();
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        }
    }
}
