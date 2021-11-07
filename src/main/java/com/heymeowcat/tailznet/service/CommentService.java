package com.heymeowcat.tailznet.service;

import com.heymeowcat.tailznet.dao.PostCommentDAO;
import com.heymeowcat.tailznet.entities.PostComment;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommentService {
    private static final Logger logger = LoggerFactory.getLogger(CommentService.class);

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
