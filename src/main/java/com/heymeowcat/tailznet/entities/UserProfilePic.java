package com.heymeowcat.tailznet.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_profile_pic")
public class UserProfilePic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "users_idusers")
    private int userId;

    @Column(name = "image")
    private String image;

    public UserProfilePic() {}

    public UserProfilePic(int userId, String image) {
        this.userId = userId;
        this.image = image;
    }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }
}
