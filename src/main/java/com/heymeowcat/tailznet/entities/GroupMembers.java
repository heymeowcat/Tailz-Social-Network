package com.heymeowcat.tailznet.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "group_members")
public class GroupMembers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idgroup_members")
    private int id;

    @Column(name = "groups_group_id", length = 64)
    private String groupId;

    @Column(name = "members")
    private int memberId;

    public GroupMembers() {}

    public GroupMembers(int id, String groupId, int memberId) {
        this.id = id;
        this.groupId = groupId;
        this.memberId = memberId;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getGroupId() { return groupId; }
    public void setGroupId(String groupId) { this.groupId = groupId; }

    public int getMemberId() { return memberId; }
    public void setMemberId(int memberId) { this.memberId = memberId; }
}
