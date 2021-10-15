package com.heymeowcat.tailznet.service;

import com.heymeowcat.tailznet.dao.UserDAO;
import com.heymeowcat.tailznet.entities.User;
import java.util.List;

public class UserService {

    private final UserDAO userDAO;

    public UserService() {
        this.userDAO = new UserDAO();
    }

    public User getUserById(int userId) {
        return userDAO.findById(userId);
    }

    public User getUserByEmail(String email) {
        return userDAO.findByEmail(email);
    }

    public String getUserFullName(int userId) {
        User user = userDAO.findById(userId);
        if (user == null) return "Unknown";
        return user.getFirstName() + " " + user.getLastName();
    }

    public String getUserFirstName(int userId) {
        User user = userDAO.findById(userId);
        return user != null ? user.getFirstName() : "Unknown";
    }

    public String getUserLastName(int userId) {
        User user = userDAO.findById(userId);
        return user != null ? user.getLastName() : "";
    }

    public String getUserProfilePic(int userId) {
        User user = userDAO.findById(userId);
        return user != null ? user.getImage() : "def.png";
    }

    public void saveUser(User user) {
        userDAO.save(user);
    }

    public void updateUser(User user) {
        userDAO.update(user);
    }

    public void deleteUser(int userId) {
        userDAO.deleteById(userId);
    }
}
