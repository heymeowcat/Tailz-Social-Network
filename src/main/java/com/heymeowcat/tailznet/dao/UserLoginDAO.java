package com.heymeowcat.tailznet.dao;

import com.heymeowcat.tailznet.entities.UserLogin;
import java.util.List;

public class UserLoginDAO extends BaseDAO<UserLogin> {

    public UserLoginDAO() {
        super(UserLogin.class);
    }

    public UserLogin findByUsername(String username) {
        List<UserLogin> list = findByProperty("username", username);
        return list != null && !list.isEmpty() ? list.get(0) : null;
    }

    public UserLogin findByUserId(int userId) {
        List<UserLogin> list = findByProperty("userId", userId);
        return list != null && !list.isEmpty() ? list.get(0) : null;
    }
}
