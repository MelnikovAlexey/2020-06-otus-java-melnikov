package org.otus.education.hw17.config;

import lombok.val;
import org.otus.education.hw17.services.DBServiceUser;
import org.otus.education.hw17.services.UserService;
import org.otus.education.hw17.services.UserServiceRegistrar;
import org.otus.education.hw17.services.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

@Configuration
public class RemoteConfig {
    @Value("${app.rmiExportPort}")
    private int rmiPort;
    @Value("#{'rmi://localhost:${app.rmiPort}/${app.rmiService}'}")
    private String rmiUrl;

    @Bean
    public RmiProxyFactoryBean service() {
        val rmiProxyFactory = new RmiProxyFactoryBean();
        rmiProxyFactory.setServiceUrl(rmiUrl);
        rmiProxyFactory.setServiceInterface(UserServiceRegistrar.class);
        return rmiProxyFactory;
    }

    @Bean
    public UserService userService(@Qualifier("defaultDbUserService") DBServiceUser dbServiceUser, UserServiceRegistrar registrar) throws RemoteException {
        val userService = new UserServiceImpl(dbServiceUser);
        UnicastRemoteObject.exportObject(userService, rmiPort);
        registrar.registerService(userService);
        return userService;
    }

}
