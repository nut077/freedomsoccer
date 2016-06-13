package com.freedomsoccer.controller;

import com.freedomsoccer.domain.Role;
import com.freedomsoccer.domain.User;
import com.freedomsoccer.service.RoleService;
import com.freedomsoccer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@RequestMapping("/admin")
@Controller
public class UserAdminController {

    @Autowired
    private UserService userServiceImpl;

    @Autowired
    private RoleService roleServiceImpl;

    @RequestMapping("/users")
    public String user(Model model) {
        model.addAttribute("users", userServiceImpl.list());
        return "admin/users/users";
    }

    @RequestMapping(value = "/users/edit", method = RequestMethod.POST)
    public String save(@Valid User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "admin/users/edit";
        } else {
            List<User> users = Arrays.asList(user);
            Role role = new Role();
            role.setName("USER");
            role.setRole("ROLE_USER");
            role.setUsers(users);
            List<Role> roleList = Arrays.asList(role);
            user.setRoles(roleList);
            userServiceImpl.save(user);
            model.addAttribute("ros", roleServiceImpl.list());
            return "admin/users/edit";
        }
    }

    @RequestMapping(value = "/users/edit/{username}", method = RequestMethod.GET)
    public String edit(@PathVariable("username") String username, Model model) {
        model.addAttribute("user", userServiceImpl.getUsername(username));
        model.addAttribute("ros", roleServiceImpl.list());
        return "admin/users/edit";
    }
}
