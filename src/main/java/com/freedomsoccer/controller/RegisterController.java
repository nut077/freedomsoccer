package com.freedomsoccer.controller;

import com.freedomsoccer.domain.Role;
import com.freedomsoccer.domain.User;
import com.freedomsoccer.service.NotificationService;
import com.freedomsoccer.service.UserService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@Controller
@Log4j
public class RegisterController {

    @Autowired
    private UserService userServiceImpl;

    @Autowired
    private NotificationService notificationService;

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
            role.setName("USER");
            role.setRole("ROLE_USER");
            List<Role> roleList = Arrays.asList(role);
            user.setRoles(roleList);
            notificationService.sendNotification(user);
            userServiceImpl.save(user);
            return "redirect:/success-register";
        }
    }

    @RequestMapping("/success-register")
    public String successRegister() {
        return "auth/success-register";
    }

    @RequestMapping(value = "/check-username-register", method = RequestMethod.GET)
    @ResponseBody
    public String checkUsernameRegister(String username) {
        User user = userServiceImpl.getUser(username);
        if (user != null) {
            return "false";
        }
        return "true";
    }

    @RequestMapping(value = "/check-email-register", method = RequestMethod.GET)
    @ResponseBody
    public String checkEmailRegister(String email) {
        try {
            User user = userServiceImpl.getEmail(email);
            if (user == null) {
                return "true";
            }
        } catch (Exception e) {
            log.info(e.getMessage());
        }
        return "false";
    }

}
