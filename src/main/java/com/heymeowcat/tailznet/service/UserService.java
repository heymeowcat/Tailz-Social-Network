package com.heymeowcat.tailznet.service;

import com.heymeowcat.tailznet.dao.UserDAO;
import com.heymeowcat.tailznet.entities.User;
import com.heymeowcat.tailznet.entities.UserLogin;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
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

    public boolean isEmailActive(String email) {
        User user = userDAO.findByEmail(email);
        return user != null && "1".equals(user.getStatus());
    }

    public Integer getUserIdByEmailAndHash(String email, String hash) {
        User user = userDAO.findByEmail(email);
        if (user != null && hash.equals(user.getHash())) {
            return user.getId();
        }
        return null;
    }

    public boolean activateAccount(String email, String hash) {
        return userDAO.activateAccount(email, hash);
    }

    public void updateUserNameAndImage(int userId, String firstName, String lastName, String image) {
        userDAO.updateUserName(userId, firstName, lastName);
        UserProfilePicService picService = new UserProfilePicService();
        picService.updateProfilePic(userId, image);
    }

    public boolean emailExists(String email) {
        User user = userDAO.findByEmail(email);
        return user != null;
    }

    public void registerUser(String email, String hash) {
        User user = new User();
        user.setEmail(email);
        user.setStatus("0");
        user.setHash(hash);
        user.setUserTypeId(2);
        userDAO.save(user);
    }

    public User createGoogleUser(String email, String hash, String firstName, String lastName) {
        User user = new User();
        user.setEmail(email);
        user.setStatus("1");
        user.setHash(hash);
        user.setUserTypeId(2);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        userDAO.save(user);
        return user;
    }

    public void updateUserName(int userId, String firstName, String lastName) {
        User user = userDAO.findById(userId);
        if (user != null) {
            user.setFirstName(firstName);
            user.setLastName(lastName);
            userDAO.update(user);
        }
    }

    public int createGoogleUserFull(String email, String hash, String firstName, String lastName,
            String profilePicUrl) {
        User user = createGoogleUser(email, hash, firstName, lastName);
        int uid = user.getId();

        UserProfilePicService picService = new UserProfilePicService();
        picService.updateProfilePic(uid, profilePicUrl + "?sz=180");

        AppThemeService themeService = new AppThemeService();
        themeService.upsertTheme(uid, "purplelight");

        AppLayoutService layoutService = new AppLayoutService();
        layoutService.saveLayout(uid);

        UserLoginService loginService = new UserLoginService();
        UserLogin login = new UserLogin();
        login.setUserId(uid);
        login.setPassword("1");
        loginService.saveLogin(login);

        UserPrivacyService privacyService = new UserPrivacyService();
        privacyService.upsertPrivacy(uid, "public");

        return uid;
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
