package com.heymeowcat.tailznet.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "groups")
public class GroupEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_id")
    private int groupId;

    @Column(name = "groupname")
    private String groupName;

    @Column(name = "groupimg")
    private String groupImage;

    @Column(name = "groupadmin")
    private int groupAdmin;

    public GroupEntity() {}

    public GroupEntity(int groupId, String groupName, String groupImage, int groupAdmin) {
        this.groupId = groupId;
        this.groupName = groupName;
        this.groupImage = groupImage;
        this.groupAdmin = groupAdmin;
    }

    public int getGroupId() { return groupId; }
    public void setGroupId(int groupId) { this.groupId = groupId; }

    public String getGroupName() { return groupName; }
    public void setGroupName(String groupName) { this.groupName = groupName; }

    public String getGroupImage() { return groupImage; }
    public void setGroupImage(String groupImage) { this.groupImage = groupImage; }

    public int getGroupAdmin() { return groupAdmin; }
    public void setGroupAdmin(int groupAdmin) { this.groupAdmin = groupAdmin; }
}
