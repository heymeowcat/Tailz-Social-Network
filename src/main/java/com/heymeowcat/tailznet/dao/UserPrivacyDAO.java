package com.heymeowcat.tailznet.dao;

import com.heymeowcat.tailznet.entities.UserPrivacy;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserPrivacyDAO extends BaseDAO<UserPrivacy> {

    private static final Logger logger = LoggerFactory.getLogger(UserPrivacyDAO.class);

    public UserPrivacyDAO() {
        super(UserPrivacy.class);
    }

    public UserPrivacy findByUserId(int userId) {
        List<UserPrivacy> list = findByProperty("userId", userId);
        return list != null && !list.isEmpty() ? list.get(0) : null;
    }
}
