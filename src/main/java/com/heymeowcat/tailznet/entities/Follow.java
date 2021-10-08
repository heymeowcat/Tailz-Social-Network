package com.heymeowcat.tailznet.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "follow")
public class Follow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idfollow")
    private int id;

    @Column(name = "sender")
    private int sender;

    @Column(name = "receiver")
    private int receiver;

    public Follow() {}

    public Follow(int id, int sender, int receiver) {
        this.id = id;
        this.sender = sender;
        this.receiver = receiver;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getSender() { return sender; }
    public void setSender(int sender) { this.sender = sender; }

    public int getReceiver() { return receiver; }
    public void setReceiver(int receiver) { this.receiver = receiver; }
}
