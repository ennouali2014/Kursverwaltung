package com.example.kursverwaltung.controller;

import com.example.kursverwaltung.config.UserInfoUserDetailsService;
import com.example.kursverwaltung.domain.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/u")
public class UserInfoController {

    @Autowired
    private UserInfoUserDetailsService serviceInfoUser;

//    @PostMapping("/newUser")
//    //@PreAuthorize("hasAuthority('ADMIN')")
//    public String addNewUser(@RequestBody UserInfo userInfo) {
//        return serviceInfoUser.addUser(userInfo);
//    }

    @GetMapping("/users")
    @PreAuthorize("hasAuthority('USER','ADMIN')")
    public String viewHomePage(Model model) {
        List<UserInfo> listUsers = serviceInfoUser.listAll();
        model.addAttribute("listUsers", listUsers);
        return "user";
    }

    @RequestMapping("/editUser/{id}")
    public ModelAndView showEditUserpage(@PathVariable(name = "id") int id) {
        ModelAndView mav = new ModelAndView("newUser");
        Optional<UserInfo> user = serviceInfoUser.getUserId(id);
        mav.addObject("user", user);
        user = serviceInfoUser.get(id);
        mav.addObject("user", user);
        return mav;
    }

    @RequestMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable(name = "id") int id) {
        serviceInfoUser.delete(id);
        return "redirect:/user";
    }

}
