package com.lovelymonkey.core.builder;

import com.lovelymonkey.core.model.User;

import lombok.Data;

/**
 * User object builder.
 * @author guanxwei
 *
 */
@Data
public class UserBuilder {
    private String userName;
    private int level;
    private String email;
    private String phoneNumber;
    private String passWord;

    /**
     * Builder.
     * @return UserBuilder.
     */
    public static UserBuilder builder() {
        return new UserBuilder();
    }

    /**
     * Set userName.
     * @param userName userName.
     * @return UserBuilder.
     */
    public UserBuilder userName(final String userName) {
        this.userName = userName;
        return this;
    }

    /**
     * Set level.
     * @param level level.
     * @return UserBuilder.
     */
    public UserBuilder level(final int level) {
        this.level = level;
        return this;
    }

    /**
     * Set email.
     * @param email email.
     * @return UserBuilder.
     */
    public UserBuilder email(final String email) {
        this.email = email;
        return this;
    }

    /**
     * Set phoneNumber.
     * @param phoneNumber phoneNumber.
     * @return UserBuilder.
     */
    public UserBuilder phoneNumber(final String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    /**
     * Set passWord.
     * @param passWord passWord.
     * @return UserBuilder.
     */
    public UserBuilder passWord(final String passWord) {
        this.passWord = passWord;
        return this;
    }

    /**
     * Return built user object.
     * @return User object.
     */
    public User build() {
        User u = new User();
        u.setEmail(this.email);
        u.setLevel(this.level);
        u.setPhoneNumber(this.phoneNumber);
        u.setUserName(this.userName);
        u.setPassWord(this.passWord);

        return u;
    }
}
