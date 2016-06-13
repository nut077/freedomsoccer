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

import javax.servlet.http.HttpServletRequest;
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
    public String save(@Valid User user, BindingResult bindingResult, Model model, HttpServletRequest req) {
        User user1 = userServiceImpl.getUsername(user.getUsername());
        user.setPassword(user1.getPassword());
        if (bindingResult.hasErrors() && bindingResult.hasFieldErrors("username")) {
            model.addAttribute("ros", roleServiceImpl.list());
            model.addAttribute("success", false);
            return "admin/users/edit";
        } else {
            Role role = roleServiceImpl.getRoleByName(req.getParameter("roles"));
            user.setRoles(Arrays.asList(role));
            userServiceImpl.save(user);
            model.addAttribute("ros", roleServiceImpl.list());
            model.addAttribute("success", true);
            return "admin/users/edit";
        }
    }

    @RequestMapping(value = "/users/edit/{username}", method = RequestMethod.GET)
    public String edit(@PathVariable("username") String username, Model model) {
        model.addAttribute("user", userServiceImpl.getUsername(username));
        model.addAttribute("ros", roleServiceImpl.list());
        model.addAttribute("success", false);
        return "admin/users/edit";
    }
}
