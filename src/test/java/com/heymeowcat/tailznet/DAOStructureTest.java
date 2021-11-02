package com.heymeowcat.tailznet;

import com.heymeowcat.tailznet.dao.UserDAO;
import com.heymeowcat.tailznet.dao.PostDAO;
import com.heymeowcat.tailznet.dao.ChatDAO;
import com.heymeowcat.tailznet.dao.FollowDAO;
import com.heymeowcat.tailznet.dao.NotificationDAO;
import com.heymeowcat.tailznet.service.UserService;
import com.heymeowcat.tailznet.service.PostService;
import com.heymeowcat.tailznet.service.ChatService;
import com.heymeowcat.tailznet.service.FollowService;
import com.heymeowcat.tailznet.service.NotificationService;
import org.junit.Test;
import static org.junit.Assert.*;

public class DAOStructureTest {

    @Test
    public void testUserDAOInstantiation() {
        UserDAO dao = new UserDAO();
        assertNotNull("UserDAO should be instantiated", dao);
    }

    @Test
    public void testPostDAOInstantiation() {
        PostDAO dao = new PostDAO();
        assertNotNull("PostDAO should be instantiated", dao);
    }

    @Test
    public void testChatDAOInstantiation() {
        ChatDAO dao = new ChatDAO();
        assertNotNull("ChatDAO should be instantiated", dao);
    }

    @Test
    public void testFollowDAOInstantiation() {
        FollowDAO dao = new FollowDAO();
        assertNotNull("FollowDAO should be instantiated", dao);
    }

    @Test
    public void testNotificationDAOInstantiation() {
        NotificationDAO dao = new NotificationDAO();
        assertNotNull("NotificationDAO should be instantiated", dao);
    }

    @Test
    public void testUserServiceInstantiation() {
        UserService service = new UserService();
        assertNotNull("UserService should be instantiated", service);
    }

    @Test
    public void testPostServiceInstantiation() {
        PostService service = new PostService();
        assertNotNull("PostService should be instantiated", service);
    }

    @Test
    public void testChatServiceInstantiation() {
        ChatService service = new ChatService();
        assertNotNull("ChatService should be instantiated", service);
    }

    @Test
    public void testFollowServiceInstantiation() {
        FollowService service = new FollowService();
        assertNotNull("FollowService should be instantiated", service);
    }

    @Test
    public void testNotificationServiceInstantiation() {
        NotificationService service = new NotificationService();
        assertNotNull("NotificationService should be instantiated", service);
    }
}
