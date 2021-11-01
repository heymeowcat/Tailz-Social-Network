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
    @Column(name = "group_id", length = 64)
    private String groupId;

    @Column(name = "groupname")
    private String groupName;

    @Column(name = "groupimg")
    private String groupImage;

    @Column(name = "groupadmin")
    private int groupAdmin;

    public GroupEntity() {}

    public GroupEntity(String groupId, String groupName, String groupImage, int groupAdmin) {
        this.groupId = groupId;
        this.groupName = groupName;
        this.groupImage = groupImage;
        this.groupAdmin = groupAdmin;
    }

    public String getGroupId() { return groupId; }
    public void setGroupId(String groupId) { this.groupId = groupId; }

    public String getGroupName() { return groupName; }
    public void setGroupName(String groupName) { this.groupName = groupName; }

    public String getGroupImage() { return groupImage; }
    public void setGroupImage(String groupImage) { this.groupImage = groupImage; }

    public int getGroupAdmin() { return groupAdmin; }
    public void setGroupAdmin(int groupAdmin) { this.groupAdmin = groupAdmin; }
}
