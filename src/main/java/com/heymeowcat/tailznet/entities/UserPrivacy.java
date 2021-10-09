package com.heymeowcat.tailznet.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_privacy")
public class UserPrivacy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iduser_privacy")
    private int id;

    @Column(name = "users_idusers")
    private int userId;

    @Column(name = "privacy_name")
    private String privacyName;

    public UserPrivacy() {}

    public UserPrivacy(int id, int userId, String privacyName) {
        this.id = id;
        this.userId = userId;
        this.privacyName = privacyName;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public String getPrivacyName() { return privacyName; }
    public void setPrivacyName(String privacyName) { this.privacyName = privacyName; }
}
