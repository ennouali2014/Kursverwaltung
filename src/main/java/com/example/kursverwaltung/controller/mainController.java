package com.example.kursverwaltung.controller;

import com.example.kursverwaltung.service.KursService;
import com.example.kursverwaltung.service.UserInfoUserDetailsService;
import com.example.kursverwaltung.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
        model.addAttribute("listKurse", kursService.listAll());
        return "index";
    }
    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    @GetMapping("/public")
    public String personenlistePublic(Model model) {
        return "redirect:/person/personen";
    }



   /* @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/index";
    }*/

}
