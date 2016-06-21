package com.freedomsoccer.service;

import com.freedomsoccer.domain.User;

import java.util.List;

public interface UserService {
    public List<User> list();
    public User save(User user);
    public void delete(String username);
    public void delete(User user);
    public User getUser(String username);
    public User getEmail(String email);
}
