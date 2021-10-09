package com.heymeowcat.tailznet.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_bookmarks")
public class UserBookmark {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iduser_bookmarks")
    private int id;

    @Column(name = "users_idusers")
    private int userId;

    @Column(name = "post_idpost")
    private int postId;

    @Column(name = "notice_time")
    private String noticeTime;

    public UserBookmark() {}

    public UserBookmark(int id, int userId, int postId, String noticeTime) {
        this.id = id;
        this.userId = userId;
        this.postId = postId;
        this.noticeTime = noticeTime;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public int getPostId() { return postId; }
    public void setPostId(int postId) { this.postId = postId; }

    public String getNoticeTime() { return noticeTime; }
    public void setNoticeTime(String noticeTime) { this.noticeTime = noticeTime; }
}
