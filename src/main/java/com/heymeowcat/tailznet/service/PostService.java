package com.heymeowcat.tailznet.service;

import com.heymeowcat.tailznet.dao.PostCommentDAO;
import com.heymeowcat.tailznet.dao.PostDAO;
import com.heymeowcat.tailznet.dao.PostRankDAO;
import com.heymeowcat.tailznet.dao.UserBookmarkDAO;
import com.heymeowcat.tailznet.entities.Post;
import com.heymeowcat.tailznet.entities.PostRank;
import com.heymeowcat.tailznet.ENCDEC;
import com.heymeowcat.tailznet.KEY;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PostService {
    private static final Logger logger = LoggerFactory.getLogger(PostService.class);

    private final PostDAO postDAO;
    private final PostRankDAO postRankDAO;
    private final PostCommentDAO postCommentDAO;
    private final UserBookmarkDAO bookmarkDAO;

    public PostService() {
        this.postDAO = new PostDAO();
        this.postRankDAO = new PostRankDAO();
        this.postCommentDAO = new PostCommentDAO();
        this.bookmarkDAO = new UserBookmarkDAO();
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

    public void createPost(int userId, String heading, String image, String detail, int privacy) {
        Post post = new Post();
        post.setHeading(ENCDEC.encrypt(heading, new KEY().secretKey));
        post.setImage(ENCDEC.encrypt(image, new KEY().secretKey));
        post.setDetail(ENCDEC.encrypt(detail, new KEY().secretKey));
        post.setUserId(userId);
        post.setPostTypeId(1);
        post.setPostPrivacy(privacy);
        postDAO.save(post);
    }

    public void createVideoPost(int userId, String heading, String embedHtml, String detail, int privacy) {
        Post post = new Post();
        post.setHeading(ENCDEC.encrypt(heading, new KEY().secretKey));
        post.setImage(ENCDEC.encrypt(embedHtml, new KEY().secretKey));
        post.setDetail(ENCDEC.encrypt(detail, new KEY().secretKey));
        post.setUserId(userId);
        post.setPostTypeId(2);
        post.setPostPrivacy(privacy);
        postDAO.save(post);
    }

    public void updatePost(Post post) {
        postDAO.update(post);
    }

    public void deletePost(int postId) {
        postDAO.deleteById(postId);
    }

    public void deletePostCascade(int postId) {
        postRankDAO.deleteByPost(postId);
        postCommentDAO.deleteByPost(postId);
        bookmarkDAO.deleteByPost(postId);
        postDAO.deleteById(postId);
    }

    public void updatePostPrivacy(int userId, int privacy) {
        postDAO.updatePostPrivacyForUser(userId, privacy);
    }

    public void toggleLike(int postId, int likedBy) {
        PostRank existing = postRankDAO.findByPostAndLikedBy(postId, likedBy);
        if (existing != null) {
            postRankDAO.deleteByPostAndLikedBy(postId, likedBy);
        } else {
            PostRank rank = new PostRank();
            rank.setLikes(1);
            rank.setPostId(postId);
            rank.setLikedBy(likedBy);
            postRankDAO.save(rank);
        }
    }

    public boolean hasUserLiked(int postId, int likedBy) {
        return postRankDAO.findByPostAndLikedBy(postId, likedBy) != null;
    }

    public int getLikeCount(int postId) {
        return postRankDAO.getLikeCount(postId);
    }

    public List<Object[]> getFeedForUser(int userId) {
        return postDAO.getFeedForUser(userId);
    }

    public List<Object[]> getPostLikers(int postId) {
        return postRankDAO.findLikedByPostWithUserDetails(postId);
    }

    public List<Object[]> getPostsByUserWithDetails(int userId) {
        return postDAO.getPostsByUserWithDetails(userId);
    }

    public List<Object[]> searchPostsWithDetails(String encryptedKeyword, String wildcardKeyword) {
        return postDAO.searchPostsWithDetails(encryptedKeyword, wildcardKeyword);
    }
}
