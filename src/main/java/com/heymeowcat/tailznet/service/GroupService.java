package com.heymeowcat.tailznet.service;

import com.heymeowcat.tailznet.dao.GroupChatDAO;
import com.heymeowcat.tailznet.dao.GroupEntityDAO;
import com.heymeowcat.tailznet.dao.GroupMembersDAO;
import com.heymeowcat.tailznet.entities.GroupEntity;
import com.heymeowcat.tailznet.entities.GroupMembers;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GroupService {
    private static final Logger logger = LoggerFactory.getLogger(GroupService.class);

    private final GroupEntityDAO groupDAO;
    private final GroupMembersDAO membersDAO;
    private final GroupChatDAO chatDAO;

    public GroupService() {
        this.groupDAO = new GroupEntityDAO();
        this.membersDAO = new GroupMembersDAO();
        this.chatDAO = new GroupChatDAO();
    }

    public GroupEntity getGroupById(String groupId) {
        return groupDAO.findById(groupId);
    }

    public List<GroupEntity> getGroupsByOwner(int ownerId) {
        return groupDAO.findByOwner(ownerId);
    }

    public List<GroupMembers> getGroupMembers(String groupId) {
        return membersDAO.findByGroup(groupId);
    }

    public List<GroupMembers> getUserGroups(int userId) {
        return membersDAO.findByUser(userId);
    }

    public boolean isUserMember(int userId, String groupId) {
        return membersDAO.isMember(userId, groupId);
    }

    public void createGroup(GroupEntity group, GroupMembers founder) {
        groupDAO.save(group);
        membersDAO.save(founder);
    }

    public void addMember(GroupMembers member) {
        membersDAO.save(member);
    }

    public void removeMember(GroupMembers member) {
        membersDAO.delete(member);
    }

    public void deleteGroupCascade(String groupId) {
        chatDAO.deleteByGroup(groupId);
        membersDAO.deleteByGroup(groupId);
        groupDAO.deleteById(groupId);
    }
}
