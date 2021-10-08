package com.heymeowcat.tailznet.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "app_theme")
public class AppTheme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idapp_theme")
    private int id;

    @Column(name = "users_idusers")
    private int userId;

    @Column(name = "themename")
    private String themeName;

    public AppTheme() {}

    public AppTheme(int id, int userId, String themeName) {
        this.id = id;
        this.userId = userId;
        this.themeName = themeName;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public String getThemeName() { return themeName; }
    public void setThemeName(String themeName) { this.themeName = themeName; }
}
