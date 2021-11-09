package com.heymeowcat.tailznet.service;

import com.heymeowcat.tailznet.dao.GroupChatDAO;
import com.heymeowcat.tailznet.entities.GroupChat;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GroupChatService {

    private static final Logger logger = LoggerFactory.getLogger(GroupChatService.class);
    private final GroupChatDAO groupChatDAO;

    public GroupChatService() {
        this.groupChatDAO = new GroupChatDAO();
    }

    public List<GroupChat> getGroupMessages(String groupId) {
        return groupChatDAO.findByGroup(groupId);
    }

    public void saveMessage(GroupChat chat) {
        groupChatDAO.save(chat);
    }
}
