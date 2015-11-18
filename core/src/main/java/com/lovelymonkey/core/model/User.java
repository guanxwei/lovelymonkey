package com.lovelymonkey.core.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Data
@Entity
@Table(name="user")
public class User {
    @Id
    @GeneratedValue(generator = "system-uuid")   
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name="id")
    private String ID;

    @Column(name="email", length=30)
    private String email;

    @Column(name="passworld")
    private String passWord;

    @Column(name="emojipath")
    private String emojiPath;

    @Column(name="phonenumber", length=13)
    private String phoneNumber;

    @Column(name="username", unique=true, nullable=false, length=30)
    private String userName;
}
