package com.freedomsoccer.controller;

import com.freedomsoccer.domain.Role;
import com.freedomsoccer.domain.User;
import com.freedomsoccer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@Controller
public class RegisterController {

    @Autowired
    private UserService userServiceImpl;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register() {
        return "auth/register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String save(@Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "auth/register";
        } else {
            Role role = new Role();
            role.setName("ADMIN");
            role.setRole("ROLE_ADMIN");
            List<Role> roleList = Arrays.asList(role);
            user.setRoles(roleList);
            User user1 = userServiceImpl.save(user);
            return "auth/register";
        }
    }

}
