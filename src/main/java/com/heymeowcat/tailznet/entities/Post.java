package com.heymeowcat.tailznet.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idpost")
    private int id;

    @Column(name = "post_heading")
    private String heading;

    @Column(name = "post_detial")
    private String detail;

    @Column(name = "post_img_area")
    private String image;

    @Column(name = "post_time")
    private String postTime;

    @Column(name = "users_idusers")
    private int userId;

    @Column(name = "post_type_idpost_type")
    private int postTypeId;

    @Column(name = "Post_Privacy")
    private int postPrivacy;

    public Post() {}

    public Post(int id, String heading, String detail, String image,
                String postTime, int userId, int postTypeId, int postPrivacy) {
        this.id = id;
        this.heading = heading;
        this.detail = detail;
        this.image = image;
        this.postTime = postTime;
        this.userId = userId;
        this.postTypeId = postTypeId;
        this.postPrivacy = postPrivacy;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getHeading() { return heading; }
    public void setHeading(String heading) { this.heading = heading; }

    public String getDetail() { return detail; }
    public void setDetail(String detail) { this.detail = detail; }

    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }

    public String getPostTime() { return postTime; }
    public void setPostTime(String postTime) { this.postTime = postTime; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public int getPostTypeId() { return postTypeId; }
    public void setPostTypeId(int postTypeId) { this.postTypeId = postTypeId; }

    public int getPostPrivacy() { return postPrivacy; }
    public void setPostPrivacy(int postPrivacy) { this.postPrivacy = postPrivacy; }
}
