package com.heymeowcat.tailznet.service;

import com.heymeowcat.tailznet.dao.PostCommentDAO;
import com.heymeowcat.tailznet.entities.PostComment;
import java.util.List;

public class CommentService {

    private final PostCommentDAO commentDAO;

    public CommentService() {
        this.commentDAO = new PostCommentDAO();
    }

    public List<PostComment> getCommentsForPost(int postId) {
        return commentDAO.findByPost(postId);
    }

    public void saveComment(PostComment comment) {
        commentDAO.save(comment);
    }

    public void deleteComment(int commentId) {
        commentDAO.deleteById(commentId);
    }
}
