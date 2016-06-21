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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Arrays;

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
    public String save(@Valid User user, BindingResult bindingResult, Model model, HttpServletRequest req, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors() && bindingResult.hasFieldErrors("username")) {
            model.addAttribute("ros", roleServiceImpl.list());
            model.addAttribute("message", "Save data not successful");
            model.addAttribute("messageResult", "error");
            return "admin/users/edit";
        } else {
            Role role = roleServiceImpl.getRoleByName(req.getParameter("roles"));
            user.setRoles(Arrays.asList(role));
            userServiceImpl.save(user);
            model.addAttribute("ros", roleServiceImpl.list());
            model.addAttribute("message", "Save data successful");
            model.addAttribute("messageResult", "success");
            return "admin/users/edit";
        }
    }

    @RequestMapping(value = "/users/edit/{username}", method = RequestMethod.GET)
    public String edit(@PathVariable("username") String username, Model model) {
        model.addAttribute("user", userServiceImpl.getUser(username));
        model.addAttribute("ros", roleServiceImpl.list());
        return "admin/users/edit";
    }

    @RequestMapping("/users/delete/{username}")
    public String delete(@PathVariable("username") String username, RedirectAttributes redirectAttributes) {
        userServiceImpl.delete(username);
        redirectAttributes.addFlashAttribute("message", username + " was delete");
        return "redirect:/admin/users/";
    }
}
