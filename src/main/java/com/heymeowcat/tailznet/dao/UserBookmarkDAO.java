package com.heymeowcat.tailznet.dao;

import com.heymeowcat.tailznet.entities.UserBookmark;
import java.util.List;

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
}
