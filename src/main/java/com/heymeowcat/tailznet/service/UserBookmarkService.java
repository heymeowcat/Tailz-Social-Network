package com.heymeowcat.tailznet.service;

import com.heymeowcat.tailznet.dao.UserBookmarkDAO;
import com.heymeowcat.tailznet.entities.UserBookmark;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserBookmarkService {
    private static final Logger logger = LoggerFactory.getLogger(UserBookmarkService.class);

    private final UserBookmarkDAO bookmarkDAO;

    public UserBookmarkService() {
        this.bookmarkDAO = new UserBookmarkDAO();
    }

    public List<UserBookmark> getUserBookmarks(int userId) {
        return bookmarkDAO.findByUserId(userId);
    }

    public boolean isBookmarked(int userId, int postId) {
        return bookmarkDAO.isBookmarked(userId, postId);
    }

    public void bookmarkPost(int userId, int postId) {
        if (!isBookmarked(userId, postId)) {
            UserBookmark bm = new UserBookmark();
            bm.setUserId(userId);
            bm.setPostId(postId);
            bookmarkDAO.save(bm);
        }
    }

    public void removeBookmark(int userId, int postId) {
        List<UserBookmark> list = getUserBookmarks(userId);
        if (list != null) {
            for (UserBookmark bm : list) {
                if (bm.getPostId() == postId) {
                    bookmarkDAO.delete(bm);
                }
            }
        }
    }
}
