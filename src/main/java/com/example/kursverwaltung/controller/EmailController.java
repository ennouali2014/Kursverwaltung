package com.example.kursverwaltung.controller;


import com.example.kursverwaltung.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class EmailController {
    @Autowired
    private EmailService service;
    @RequestMapping("/person/sendmail")
    public String triggerMail(Model model,@RequestParam("subject") String subject,@RequestParam("body") String body) {
        service.sendEmail("fatimazahraeennouali@gmail.com",
                body,subject);
        model.addAttribute("success", "Email gesendet");
        return "helpdesk";
    }
    @GetMapping("/person/helpdesk")
    public String helpdesk() {
        return "helpdesk";
    }

}
