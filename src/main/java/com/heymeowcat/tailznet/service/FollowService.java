package com.heymeowcat.tailznet.service;

import com.heymeowcat.tailznet.dao.FollowDAO;
import com.heymeowcat.tailznet.entities.Follow;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FollowService {
    private static final Logger logger = LoggerFactory.getLogger(FollowService.class);

    private final FollowDAO followDAO;

    public FollowService() {
        this.followDAO = new FollowDAO();
    }

    public int getFollowerCount(int userId) {
        return followDAO.getFollowerCount(userId);
    }

    public int getFollowingCount(int userId) {
        return followDAO.getFollowingCount(userId);
    }

    public boolean isFollowing(int senderId, int receiverId) {
        return followDAO.isFollowing(senderId, receiverId);
    }

    public void follow(int senderId, int receiverId) {
        Follow follow = new Follow();
        follow.setSender(senderId);
        follow.setReceiver(receiverId);
        followDAO.save(follow);
    }

    public void unfollow(int senderId, int receiverId) {
        List<Follow> follows = followDAO.findByProperty("sender", senderId);
        for (Follow f : follows) {
            if (f.getReceiver() == receiverId) {
                followDAO.delete(f);
            }
        }
    }
}
