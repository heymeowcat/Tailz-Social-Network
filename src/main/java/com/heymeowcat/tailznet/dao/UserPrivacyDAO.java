package com.heymeowcat.tailznet.dao;

import com.heymeowcat.tailznet.entities.UserPrivacy;
import java.util.List;

public class UserPrivacyDAO extends BaseDAO<UserPrivacy> {

    public UserPrivacyDAO() {
        super(UserPrivacy.class);
    }

    public UserPrivacy findByUserId(int userId) {
        List<UserPrivacy> list = findByProperty("userId", userId);
        return list != null && !list.isEmpty() ? list.get(0) : null;
    }
}
