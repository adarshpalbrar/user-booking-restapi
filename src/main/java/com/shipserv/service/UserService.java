package com.shipserv.service;

import com.shipserv.beans.User;

import java.util.List;

public interface UserService {
    List<User> findAll();
    User findById(Integer id);
    void saveUser(User user);
}
