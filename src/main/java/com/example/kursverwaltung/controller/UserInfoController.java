package com.example.kursverwaltung.controller;

import com.example.kursverwaltung.domain.Person;
import com.example.kursverwaltung.service.UserInfoUserDetailsService;
import com.example.kursverwaltung.domain.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/user")
public class UserInfoController {
    @Autowired
    private UserInfoUserDetailsService serviceInfoUser;

    @GetMapping("/newUser")
    public String addNewUser(Model model) {
        model.addAttribute("user", new UserInfo());
        return "newUser";
    }

    @PostMapping("/saveUser")
    public String saveUser( @ModelAttribute("user") UserInfo userInfo) {
        serviceInfoUser.addUser(userInfo);
        return "redirect:/user/users";
    }

    @GetMapping("/users")
    public String viewHomePageUser(Model model, String keyword) {

        List<UserInfo> listUsers = serviceInfoUser.listAll();
        if (keyword != null) {
            model.addAttribute("listUsers", serviceInfoUser.findByKeyword(keyword));
        } else {
            model.addAttribute("listUsers", listUsers);

        }
        return "users";
    }
    @RequestMapping("/deleteUser/{id}")
    public String deleteUser( @PathVariable(name = "id") int id) {
        serviceInfoUser.delete(id);
        return "redirect:/user/users";
    }


}
