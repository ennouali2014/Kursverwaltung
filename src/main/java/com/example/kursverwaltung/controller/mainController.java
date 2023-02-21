package com.example.kursverwaltung.controller;

import com.example.kursverwaltung.domain.Person;
import com.example.kursverwaltung.service.KursService;
import com.example.kursverwaltung.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
@Controller
public class mainController {

    @Autowired
    private PersonService personService;
    @Autowired
    private KursService kursService;

    @GetMapping("/")
    public String viewHomePage(Model model) {

        return "index1";
    }
}
