package com.heymeowcat.tailznet.dao;

import com.heymeowcat.tailznet.entities.UserProfilePic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserProfilePicDAO extends BaseDAO<UserProfilePic> {

    private static final Logger logger = LoggerFactory.getLogger(UserProfilePicDAO.class);

    public UserProfilePicDAO() {
        super(UserProfilePic.class);
    }

    public UserProfilePic findByUserId(int userId) {
        return super.findByProperty("userId", userId).stream().findFirst().orElse(null);
    }
}
