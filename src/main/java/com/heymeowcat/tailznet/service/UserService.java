package com.heymeowcat.tailznet.service;

import com.heymeowcat.tailznet.dao.UserDAO;
import com.heymeowcat.tailznet.entities.User;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
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

    public void updateUserType(int userId, int userTypeId) {
        User user = userDAO.findById(userId);
        if (user != null) {
            user.setUserTypeId(userTypeId);
            userDAO.update(user);
        }
    }

    public void deleteUser(int userId) {
        userDAO.deleteById(userId);
    }

    @SuppressWarnings("unchecked")
    public List<User> searchUsers(String keyword) {
        Session session = com.heymeowcat.tailznet.HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            Criteria criteria = session.createCriteria(User.class);
            String pattern = "%" + keyword + "%";
            criteria.add(Restrictions.or(
                Restrictions.ilike("firstName", pattern, MatchMode.ANYWHERE),
                Restrictions.ilike("lastName", pattern, MatchMode.ANYWHERE)
            ));
            List<User> list = criteria.list();
            tx.commit();
            return list;
        } catch (Exception e) {
            tx.rollback();
            logger.error("Error searching users: {}", e.getMessage());
            return null;
        }
    }
}
