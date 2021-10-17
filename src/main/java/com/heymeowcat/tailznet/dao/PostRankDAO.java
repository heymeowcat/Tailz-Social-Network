package com.heymeowcat.tailznet.dao;

import com.heymeowcat.tailznet.entities.PostRank;
import java.util.List;

public class PostRankDAO extends BaseDAO<PostRank> {

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
}
