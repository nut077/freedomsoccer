package com.freedomsoccer.service;

import com.freedomsoccer.domain.User;

public interface UserService {
    public User findByUsername(String username);
}
