package com.heymeowcat.tailznet.dao;

import com.heymeowcat.tailznet.HibernateUtil;
import com.heymeowcat.tailznet.entities.UserBookmark;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class UserBookmarkDAO extends BaseDAO<UserBookmark> {

    public UserBookmarkDAO() {
        super(UserBookmark.class);
    }

    public List<UserBookmark> findByUserId(int userId) {
        return findByProperty("userId", userId);
    }

    public boolean isBookmarked(int userId, int postId) {
        List<UserBookmark> list = findByUserId(userId);
        if (list != null) {
            for (UserBookmark bm : list) {
                if (bm.getPostId() == postId) {
                    return true;
                }
            }
        }
        return false;
    }

    public void deleteByPost(int postId) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            session.createQuery("DELETE FROM UserBookmark WHERE postId = :postId")
                    .setInteger("postId", postId).executeUpdate();
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        }
    }
}
