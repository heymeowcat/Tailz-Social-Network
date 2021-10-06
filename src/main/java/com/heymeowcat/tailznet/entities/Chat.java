package com.heymeowcat.tailznet.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "chat")
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idchat")
    private int id;

    @Column(name = "chat_text")
    private String chatText;

    @Column(name = "src")
    private String src;

    @Column(name = "user_sender")
    private int userSender;

    @Column(name = "users_receiver")
    private int userReceiver;

    @Column(name = "chattype_idchattype")
    private int chatTypeId;

    @Column(name = "chatlinestatus")
    private int chatlineStatus;

    @Column(name = "chat_datetime")
    private String chatDatetime;

    public Chat() {}

    public Chat(int id, String chatText, String src, int userSender, int userReceiver,
                int chatTypeId, int chatlineStatus, String chatDatetime) {
        this.id = id;
        this.chatText = chatText;
        this.src = src;
        this.userSender = userSender;
        this.userReceiver = userReceiver;
        this.chatTypeId = chatTypeId;
        this.chatlineStatus = chatlineStatus;
        this.chatDatetime = chatDatetime;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getChatText() { return chatText; }
    public void setChatText(String chatText) { this.chatText = chatText; }

    public String getSrc() { return src; }
    public void setSrc(String src) { this.src = src; }

    public int getUserSender() { return userSender; }
    public void setUserSender(int userSender) { this.userSender = userSender; }

    public int getUserReceiver() { return userReceiver; }
    public void setUserReceiver(int userReceiver) { this.userReceiver = userReceiver; }

    public int getChatTypeId() { return chatTypeId; }
    public void setChatTypeId(int chatTypeId) { this.chatTypeId = chatTypeId; }

    public int getChatlineStatus() { return chatlineStatus; }
    public void setChatlineStatus(int chatlineStatus) { this.chatlineStatus = chatlineStatus; }

    public String getChatDatetime() { return chatDatetime; }
    public void setChatDatetime(String chatDatetime) { this.chatDatetime = chatDatetime; }
}
