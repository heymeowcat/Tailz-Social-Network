package com.heymeowcat.tailznet.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "post_comment")
public class PostComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idpost_comment")
    private int id;

    @Column(name = "post_idpost")
    private int postId;

    @Column(name = "users_idusers")
    private int userId;

    @Column(name = "comment_text")
    private String commentText;

    @Column(name = "image")
    private String image;

    @Column(name = "datetime")
    private String datetime;

    @Column(name = "likes")
    private int likes;

    public PostComment() {}

    public PostComment(int id, int postId, int userId, String commentText,
                       String image, String datetime, int likes) {
        this.id = id;
        this.postId = postId;
        this.userId = userId;
        this.commentText = commentText;
        this.image = image;
        this.datetime = datetime;
        this.likes = likes;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getPostId() { return postId; }
    public void setPostId(int postId) { this.postId = postId; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public String getCommentText() { return commentText; }
    public void setCommentText(String commentText) { this.commentText = commentText; }

    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }

    public String getDatetime() { return datetime; }
    public void setDatetime(String datetime) { this.datetime = datetime; }

    public int getLikes() { return likes; }
    public void setLikes(int likes) { this.likes = likes; }
}
