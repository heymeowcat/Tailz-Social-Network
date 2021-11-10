package com.heymeowcat.tailznet.service;

import com.heymeowcat.tailznet.dao.NotificationDAO;
import com.heymeowcat.tailznet.entities.Notification;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NotificationService {

    private static final Logger logger = LoggerFactory.getLogger(NotificationService.class);
    private final NotificationDAO notificationDAO;

    public NotificationService() {
        this.notificationDAO = new NotificationDAO();
    }

    public List<Notification> getUserNotifications(int userId) {
        return notificationDAO.findByUser(userId);
    }

    public List<Object[]> getUserNotificationsWithDetails(int userId) {
        return notificationDAO.findByUserWithUserDetails(userId);
    }

    public int getUnreadCount(int userId) {
        return notificationDAO.getUnreadCount(userId);
    }

    public void markAsRead(Notification notification) {
        notification.setStatus("1");
        notificationDAO.update(notification);
    }

    public void markAsRead(int notificationId) {
        Notification notif = notificationDAO.findById(notificationId);
        if (notif != null) {
            notif.setStatus("1");
            notificationDAO.update(notif);
        }
    }

    public void createNotification(Notification notification) {
        notificationDAO.save(notification);
    }

    public void deleteNotification(int notificationId) {
        notificationDAO.deleteById(notificationId);
    }
}
