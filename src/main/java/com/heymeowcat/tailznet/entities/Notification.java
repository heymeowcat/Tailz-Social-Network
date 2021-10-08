package com.heymeowcat.tailznet.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "notification")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idnotification")
    private int id;

    @Column(name = "notificationfor")
    private int notificationFor;

    @Column(name = "notificationfrom")
    private int notificationFrom;

    @Column(name = "notificationtype")
    private String notificationType;

    @Column(name = "status")
    private String status;

    @Column(name = "time")
    private String time;

    @Column(name = "target")
    private int target;

    public Notification() {}

    public Notification(int id, int notificationFor, int notificationFrom,
                        String notificationType, String status, String time, int target) {
        this.id = id;
        this.notificationFor = notificationFor;
        this.notificationFrom = notificationFrom;
        this.notificationType = notificationType;
        this.status = status;
        this.time = time;
        this.target = target;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getNotificationFor() { return notificationFor; }
    public void setNotificationFor(int notificationFor) { this.notificationFor = notificationFor; }

    public int getNotificationFrom() { return notificationFrom; }
    public void setNotificationFrom(int notificationFrom) { this.notificationFrom = notificationFrom; }

    public String getNotificationType() { return notificationType; }
    public void setNotificationType(String notificationType) { this.notificationType = notificationType; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getTime() { return time; }
    public void setTime(String time) { this.time = time; }

    public int getTarget() { return target; }
    public void setTarget(int target) { this.target = target; }
}
