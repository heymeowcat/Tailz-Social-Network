package com.heymeowcat.tailznet.service;

import com.heymeowcat.tailznet.dao.UserProfilePicDAO;
import com.heymeowcat.tailznet.entities.UserProfilePic;

public class UserProfilePicService {

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
}
