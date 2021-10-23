package com.heymeowcat.tailznet.service;

import com.heymeowcat.tailznet.dao.PostDAO;
import com.heymeowcat.tailznet.entities.Post;
import java.util.List;

public class PostService {

    private final PostDAO postDAO;

    public PostService() {
        this.postDAO = new PostDAO();
    }

    public Post getPostById(int postId) {
        return postDAO.findById(postId);
    }

    public List<Post> getPostsByUser(int userId) {
        return postDAO.findByUser(userId);
    }

    public List<Post> getPostsByUserPrivacy(int userId, String privacy) {
        return postDAO.findByPrivacyAndFollowers(userId, privacy);
    }

    public int getPostCount(int userId) {
        return postDAO.getPostCount(userId);
    }

    public void savePost(Post post) {
        postDAO.save(post);
    }

    public void updatePost(Post post) {
        postDAO.update(post);
    }

    public void deletePost(int postId) {
        postDAO.deleteById(postId);
    }
}
