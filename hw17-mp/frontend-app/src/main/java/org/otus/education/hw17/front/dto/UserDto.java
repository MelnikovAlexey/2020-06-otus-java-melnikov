package org.otus.education.hw17.front.dto;

import ru.otus.messagesystem.client.ResultDataType;

import java.io.Serializable;

public class UserDto extends ResultDataType {
    private static final long serialVersionUID = 1L;

    private long userId;

    private String name;

    private String login;

    private String password;

    public UserDto() {
        this(0, null, null, null);
    }

    public UserDto(long userId, String name, String login, String password) {
        this.userId = userId;
        this.name = name;
        this.login = login;
        this.password = password;
    }
    public UserDto(long userId) {
        this(userId, null, null, null);
    }

    public UserDto(String name, String login, String password) {
        this(0, name, login, password);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
