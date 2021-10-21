package com.heymeowcat.tailznet.service;

import com.heymeowcat.tailznet.dao.UserPrivacyDAO;
import com.heymeowcat.tailznet.entities.UserPrivacy;

public class UserPrivacyService {

    private final UserPrivacyDAO privacyDAO;

    public UserPrivacyService() {
        this.privacyDAO = new UserPrivacyDAO();
    }

    public UserPrivacy getUserPrivacy(int userId) {
        return privacyDAO.findByUserId(userId);
    }

    public String getPrivacyName(int userId) {
        UserPrivacy privacy = getUserPrivacy(userId);
        return privacy != null ? privacy.getPrivacyName() : "public";
    }

    public void savePrivacy(UserPrivacy privacy) {
        privacyDAO.save(privacy);
    }

    public void updatePrivacy(UserPrivacy privacy) {
        privacyDAO.update(privacy);
    }
}
