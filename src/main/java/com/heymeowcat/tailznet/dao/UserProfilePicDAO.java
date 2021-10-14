package com.heymeowcat.tailznet.dao;

import com.heymeowcat.tailznet.entities.UserProfilePic;

public class UserProfilePicDAO extends BaseDAO<UserProfilePic> {

    public UserProfilePicDAO() {
        super(UserProfilePic.class);
    }

    public UserProfilePic findByUserId(int userId) {
        return super.findByProperty("userId", userId).stream().findFirst().orElse(null);
    }
}
