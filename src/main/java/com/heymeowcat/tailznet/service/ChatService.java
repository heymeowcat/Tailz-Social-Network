package com.heymeowcat.tailznet.service;

import com.heymeowcat.tailznet.dao.ChatDAO;
import com.heymeowcat.tailznet.entities.Chat;
import java.util.List;

public class ChatService {

    private final ChatDAO chatDAO;

    public ChatService() {
        this.chatDAO = new ChatDAO();
    }

    public List<Chat> getConversation(int senderId, int receiverId) {
        return chatDAO.findMessages(senderId, receiverId);
    }

    public int getUnreadCount(int receiverId, int senderId) {
        return chatDAO.getUnreadCount(receiverId, senderId);
    }

    public void saveMessage(Chat chat) {
        chatDAO.save(chat);
    }

    public void markMessagesAsRead(int receiverId, int senderId) {
        List<Chat> messages = getConversation(senderId, receiverId);
        if (messages != null) {
            for (Chat msg : messages) {
                if (msg.getUserReceiver() == receiverId && msg.getChatlineStatus() == 0) {
                    msg.setChatlineStatus(1);
                    chatDAO.update(msg);
                }
            }
        }
    }
}
