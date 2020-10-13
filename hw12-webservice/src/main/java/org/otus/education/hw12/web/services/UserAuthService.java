package org.otus.education.hw12.web.services;

public interface UserAuthService {
    boolean authenticate(String login, String password);
}
