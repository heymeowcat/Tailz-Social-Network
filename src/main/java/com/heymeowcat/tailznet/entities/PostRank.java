package com.heymeowcat.tailznet.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "post_rank")
public class PostRank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idpost_rank")
    private int id;

    @Column(name = "likes")
    private int likes;

    @Column(name = "post_idpost")
    private int postId;

    @Column(name = "likedby")
    private int likedBy;

    public PostRank() {}

    public PostRank(int id, int likes, int postId, int likedBy) {
        this.id = id;
        this.likes = likes;
        this.postId = postId;
        this.likedBy = likedBy;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getLikes() { return likes; }
    public void setLikes(int likes) { this.likes = likes; }

    public int getPostId() { return postId; }
    public void setPostId(int postId) { this.postId = postId; }

    public int getLikedBy() { return likedBy; }
    public void setLikedBy(int likedBy) { this.likedBy = likedBy; }
}
