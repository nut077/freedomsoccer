package com.freedomsoccer.controller;

import com.freedomsoccer.domain.User;
import com.freedomsoccer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@RequestMapping("/admin")
@Controller
public class UserAdminController {

    @Autowired
    private UserService userServiceImpl;
    @RequestMapping("/users")
    public String user(Model model) {
        model.addAttribute("users", userServiceImpl.list());
        return "admin/users/users";
    }

    public String save(@Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {

        } else {
            userServiceImpl.save(user);
        }
        return "admin/users/edit";
    }

    @RequestMapping("/users/edit/{username}")
    public String edit(@PathVariable("username") String username, Model model) {
        model.addAttribute("user", userServiceImpl.getUsername(username));
        return "admin/users/edit";
    }
}
