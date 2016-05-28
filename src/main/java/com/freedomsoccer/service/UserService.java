package com.freedomsoccer.service;

import com.freedomsoccer.domain.User;

import java.util.List;

public interface UserService {
    public List<User> list();
    public User save(User user);
    public void delete(User user);
    public User getUsername(String username);
}
