package org.otus.education.hw17.services;


import org.otus.education.hw17.dto.UserDto;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface UserService extends Remote {
    long save(UserDto user) throws RemoteException;

    List<UserDto> getAll() throws RemoteException;
}
