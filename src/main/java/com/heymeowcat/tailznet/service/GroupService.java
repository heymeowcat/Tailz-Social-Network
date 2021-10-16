package com.heymeowcat.tailznet.service;

import com.heymeowcat.tailznet.dao.GroupEntityDAO;
import com.heymeowcat.tailznet.dao.GroupMembersDAO;
import com.heymeowcat.tailznet.entities.GroupEntity;
import com.heymeowcat.tailznet.entities.GroupMembers;
import java.util.List;

public class GroupService {

    private final GroupEntityDAO groupDAO;
    private final GroupMembersDAO membersDAO;

    public GroupService() {
        this.groupDAO = new GroupEntityDAO();
        this.membersDAO = new GroupMembersDAO();
    }

    public GroupEntity getGroupById(int groupId) {
        return groupDAO.findById(groupId);
    }

    public List<GroupEntity> getGroupsByOwner(int ownerId) {
        return groupDAO.findByOwner(ownerId);
    }

    public List<GroupMembers> getGroupMembers(int groupId) {
        return membersDAO.findByGroup(groupId);
    }

    public List<GroupMembers> getUserGroups(int userId) {
        return membersDAO.findByUser(userId);
    }

    public boolean isUserMember(int userId, int groupId) {
        return membersDAO.isMember(userId, groupId);
    }

    public void createGroup(GroupEntity group) {
        groupDAO.save(group);
    }

    public void addMember(GroupMembers member) {
        membersDAO.save(member);
    }

    public void removeMember(GroupMembers member) {
        membersDAO.delete(member);
    }
}
