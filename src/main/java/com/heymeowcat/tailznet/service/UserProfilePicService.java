package com.heymeowcat.tailznet.service;

import com.heymeowcat.tailznet.dao.UserProfilePicDAO;
import com.heymeowcat.tailznet.entities.UserProfilePic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserProfilePicService {
    private static final Logger logger = LoggerFactory.getLogger(UserProfilePicService.class);

    private final UserProfilePicDAO picDAO;

    public UserProfilePicService() {
        this.picDAO = new UserProfilePicDAO();
    }

    public UserProfilePic getProfilePic(int userId) {
        return picDAO.findByUserId(userId);
    }

    public String getProfilePicPath(int userId) {
        UserProfilePic pic = picDAO.findByUserId(userId);
        if (pic == null) return "def.png";
        return pic.getImage() != null ? pic.getImage() : "def.png";
    }

    public void saveProfilePic(UserProfilePic pic) {
        picDAO.save(pic);
    }

    public void updateProfilePic(UserProfilePic pic) {
        picDAO.update(pic);
    }

    public void updateProfilePic(int userId, String imagePath) {
        UserProfilePic pic = picDAO.findByUserId(userId);
        if (pic == null) {
            pic = new UserProfilePic();
            pic.setUserId(userId);
        }
        pic.setImage(imagePath);
        picDAO.save(pic);
    }

    public void resetProfilePic(int userId) {
        updateProfilePic(userId, "img/Profile_avatar_placeholder_large.png");
    }
}
