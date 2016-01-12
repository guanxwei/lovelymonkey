package com.lovelymonkey.core.builder;

import com.lovelymonkey.core.model.User;

import lombok.Data;

@Data
public class UserBuilder {
    private String userName;
    private int level;
    private String email;
    private String phoneNumber;

    public static UserBuilder builder() {
        return new UserBuilder();
    }

    public UserBuilder userName(final String userName) {
        this.userName = userName;
        return this;
    }

    public UserBuilder level(final int level) {
        this.level = level;
        return this;
    }

    public UserBuilder email(final String email) {
        this.email = email;
        return this;
    }

    public UserBuilder phoneNumber(final String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public User build() {
        User u = new User();
        u.setEmail(this.email);
        u.setLevel(this.level);
        u.setPhoneNumber(this.phoneNumber);
        u.setUserName(this.userName);

        return u;
    }
}
