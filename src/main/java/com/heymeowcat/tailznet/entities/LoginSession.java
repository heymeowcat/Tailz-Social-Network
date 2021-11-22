package com.heymeowcat.tailznet.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "login_sessions")
public class LoginSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idlogin_sessions")
    private int id;

    @Column(name = "ip_address")
    private String ipAddress;

    @Column(name = "in_time")
    private String inTime;

    @Column(name = "user_login_iduser_login")
    private int userLoginId;

    @Column(name = "out_time")
    private String outTime;

    public LoginSession() {}

    public LoginSession(int id, String ipAddress, String inTime, int userLoginId, String outTime) {
        this.id = id;
        this.ipAddress = ipAddress;
        this.inTime = inTime;
        this.userLoginId = userLoginId;
        this.outTime = outTime;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getIpAddress() { return ipAddress; }
    public void setIpAddress(String ipAddress) { this.ipAddress = ipAddress; }

    public String getInTime() { return inTime; }
    public void setInTime(String inTime) { this.inTime = inTime; }

    public int getUserLoginId() { return userLoginId; }
    public void setUserLoginId(int userLoginId) { this.userLoginId = userLoginId; }

    public String getOutTime() { return outTime; }
    public void setOutTime(String outTime) { this.outTime = outTime; }
}
