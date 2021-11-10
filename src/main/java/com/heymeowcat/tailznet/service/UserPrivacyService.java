package com.heymeowcat.tailznet.service;

import com.heymeowcat.tailznet.dao.UserPrivacyDAO;
import com.heymeowcat.tailznet.entities.UserPrivacy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserPrivacyService {
    private static final Logger logger = LoggerFactory.getLogger(UserPrivacyService.class);

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

    public void upsertPrivacy(int userId, String privacyName) {
        UserPrivacy existing = getUserPrivacy(userId);
        if (existing != null) {
            existing.setPrivacyName(privacyName);
            privacyDAO.update(existing);
        } else {
            UserPrivacy privacy = new UserPrivacy();
            privacy.setUserId(userId);
            privacy.setPrivacyName(privacyName);
            privacyDAO.save(privacy);
        }
    }
}
