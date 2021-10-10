package com.heymeowcat.tailznet.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "app_layout")
public class AppLayout {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idapp_layout")
    private int id;

    @Column(name = "users_idusers")
    private int userId;

    @Column(name = "layout")
    private String layout;

    public AppLayout() {}

    public AppLayout(int id, int userId, String layout) {
        this.id = id;
        this.userId = userId;
        this.layout = layout;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public String getLayout() { return layout; }
    public void setLayout(String layout) { this.layout = layout; }
}
