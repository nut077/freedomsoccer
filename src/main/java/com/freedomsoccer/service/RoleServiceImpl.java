package com.freedomsoccer.service;

import com.freedomsoccer.domain.Role;
import com.freedomsoccer.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<Role> list() {
        return roleRepository.findAll();
    }
}
