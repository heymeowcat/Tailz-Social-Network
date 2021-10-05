package com.heymeowcat.tailznet.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idusers")
    private int id;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    @Column(name = "image")
    private String image;

    @Column(name = "email")
    private String email;

    @Column(name = "status")
    private String status;

    @Column(name = "hash")
    private String hash;

    @Column(name = "user_type_iduser_type")
    private int userTypeId;

    public User() {}

    public User(int id, String firstName, String lastName, String image,
                String email, String status, String hash, int userTypeId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.image = image;
        this.email = email;
        this.status = status;
        this.hash = hash;
        this.userTypeId = userTypeId;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getHash() { return hash; }
    public void setHash(String hash) { this.hash = hash; }

    public int getUserTypeId() { return userTypeId; }
    public void setUserTypeId(int userTypeId) { this.userTypeId = userTypeId; }
}
