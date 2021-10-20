package com.heymeowcat.tailznet.dao;

import com.heymeowcat.tailznet.entities.PostComment;
import java.util.List;

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
}
