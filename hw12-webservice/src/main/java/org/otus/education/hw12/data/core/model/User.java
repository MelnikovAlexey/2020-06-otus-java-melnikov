package org.otus.education.hw12.data.core.model;

import javax.persistence.*;

/**
 * @author sergey
 * created on 03.02.19.
 */
@Entity
@Table(name = "users", indexes = {
        @Index(name = "IDX_USERS_LOGIN", columnList = "login", unique = true)
})
@NamedQueries({
        @NamedQuery(name = "get_user_by_login", query = "select u from User u where login = :login"),
        @NamedQuery(name = "get_all_users", query = "select u from User u")
})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_seq")
    @SequenceGenerator(name = "users_seq",
            sequenceName = "users_sequence", allocationSize = 1)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    private User() {
    }

    public User(String name, String login, String password) {
        this.name = name;
        this.login = login;
        this.password = password;
    }

    public User(long id, String name, String login, String password) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    @Override
    public String toString() {
        return super.toString();
    }
}
