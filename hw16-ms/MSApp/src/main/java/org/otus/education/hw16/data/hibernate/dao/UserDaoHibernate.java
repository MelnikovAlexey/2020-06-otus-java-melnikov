package org.otus.education.hw16.data.hibernate.dao;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.otus.education.hw16.data.core.dao.UserDao;
import org.otus.education.hw16.data.core.dao.UserDaoException;
import org.otus.education.hw16.data.core.model.User;
import org.otus.education.hw16.data.core.sessionmanager.SessionManager;
import org.otus.education.hw16.data.hibernate.sessionmanager.DatabaseSessionHibernate;
import org.otus.education.hw16.data.hibernate.sessionmanager.SessionManagerHibernate;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
public class UserDaoHibernate implements UserDao {

    private final SessionManagerHibernate sessionManager;

    public UserDaoHibernate(SessionManagerHibernate sessionManager) {
        this.sessionManager = sessionManager;
    }

    @Override
    public Optional<User> findById(long id) {
        DatabaseSessionHibernate currentSession = sessionManager.getCurrentSession();
        try {
            return Optional.ofNullable(currentSession.getHibernateSession().find(User.class, id));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return Optional.empty();
    }

    @Override
    public long insertUser(User user) {
        DatabaseSessionHibernate currentSession = sessionManager.getCurrentSession();
        try {
            Session hibernateSession = currentSession.getHibernateSession();
            hibernateSession.persist(user);
            hibernateSession.flush();
            return user.getId();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new UserDaoException(e);
        }
    }

    @Override
    public void updateUser(User user) {
        DatabaseSessionHibernate currentSession = sessionManager.getCurrentSession();
        try {
            Session hibernateSession = currentSession.getHibernateSession();
            hibernateSession.merge(user);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new UserDaoException(e);
        }
    }

    @Override
    public void insertOrUpdate(User user) {
        DatabaseSessionHibernate currentSession = sessionManager.getCurrentSession();
        try {
            Session hibernateSession = currentSession.getHibernateSession();
            if (user.getId() > 0) {
                hibernateSession.merge(user);
            } else {
                hibernateSession.persist(user);
            }
            hibernateSession.flush();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new UserDaoException(e);
        }
    }

    @Override
    public SessionManager getSessionManager() {
        return sessionManager;
    }

    @Override
    public Optional<User> findByLogin(String login) {
        DatabaseSessionHibernate currentSession = sessionManager.getCurrentSession();
        try {
            return Optional.ofNullable(currentSession
                    .getHibernateSession()
                    .createQuery("select u from User u where u.login =:login", User.class)
                    .setParameter("login", login)
                    .getSingleResult());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return Optional.empty();
    }

    @Override
    public List<User> getUsers() {
        DatabaseSessionHibernate currentSession = sessionManager.getCurrentSession();
        try {
            return currentSession
                    .getHibernateSession()
                    .createNamedQuery("get_all_users", User.class)
                    .getResultList();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return Collections.emptyList();
    }

    @Override
    public boolean removeUserById(long id) {
        DatabaseSessionHibernate currentSession = sessionManager.getCurrentSession();

        try {
            Session hibernateSession = currentSession.getHibernateSession();
            hibernateSession.createQuery("delete from User u where u.id =:id").setParameter("id", id).executeUpdate();

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new UserDaoException(e);
        }

        return true;
    }
}
