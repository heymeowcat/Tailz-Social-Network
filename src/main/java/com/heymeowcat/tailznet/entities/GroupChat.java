package com.heymeowcat.tailznet.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "group_chat")
public class GroupChat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idgroup_chat")
    private int id;

    @Column(name = "Groups_group_id")
    private int groupId;

    @Column(name = "chat_text")
    private String chatText;

    @Column(name = "src")
    private String src;

    @Column(name = "users_idusers")
    private int userId;

    @Column(name = "chatstatus")
    private int chatStatus;

    @Column(name = "chat_datetime")
    private String chatDatetime;

    public GroupChat() {}

    public GroupChat(int id, int groupId, String chatText, String src,
                     int userId, int chatStatus, String chatDatetime) {
        this.id = id;
        this.groupId = groupId;
        this.chatText = chatText;
        this.src = src;
        this.userId = userId;
        this.chatStatus = chatStatus;
        this.chatDatetime = chatDatetime;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getGroupId() { return groupId; }
    public void setGroupId(int groupId) { this.groupId = groupId; }

    public String getChatText() { return chatText; }
    public void setChatText(String chatText) { this.chatText = chatText; }

    public String getSrc() { return src; }
    public void setSrc(String src) { this.src = src; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public int getChatStatus() { return chatStatus; }
    public void setChatStatus(int chatStatus) { this.chatStatus = chatStatus; }

    public String getChatDatetime() { return chatDatetime; }
    public void setChatDatetime(String chatDatetime) { this.chatDatetime = chatDatetime; }
}
