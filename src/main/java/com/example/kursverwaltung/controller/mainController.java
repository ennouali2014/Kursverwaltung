package com.example.kursverwaltung.controller;

import com.example.kursverwaltung.service.UserInfoUserDetailsService;
import com.example.kursverwaltung.service.KursService;
import com.example.kursverwaltung.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class mainController {

    @Autowired
    private PersonService personService;
    @Autowired
    private KursService kursService;
    @Autowired
    private UserInfoUserDetailsService userDetailsService;

    @GetMapping("/")
    public String viewHomePage(Model model) {

        return "index";
    }
}
