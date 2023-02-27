package com.example.kursverwaltung.controller;

import com.example.kursverwaltung.config.UserInfoUserDetailsService;
import com.example.kursverwaltung.domain.Kurs;
import com.example.kursverwaltung.domain.Person;
import com.example.kursverwaltung.domain.UserInfo;
import com.example.kursverwaltung.service.KursService;
import com.example.kursverwaltung.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/p")
public class PersonController {

    @Autowired
    private PersonService service;
    @Autowired
    private UserInfoUserDetailsService serviceInfoUser;

    @PostMapping("/newUser")
    //@PreAuthorize("hasAuthority('ADMIN')")
    public String addNewUser(@RequestBody UserInfo userInfo) {
        return serviceInfoUser.addUser(userInfo);
    }

    @GetMapping("/welcome")
    public String wel() {
        return "Willkommen in Bremen";
    }

    @GetMapping("/personen")
    @PreAuthorize("hasAuthority('USER','ADMIN')")
    public String viewHomePage(Model model) {
        List<Person> listPerson = service.listAll();
        model.addAttribute("listPerson", listPerson);

        return "personen";
    }

    @GetMapping("/newperson")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String add(Model model) {
        model.addAttribute("person", new Person());
        return "redirect:/personen";
    }

    @PostMapping("/saveperson")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String saveStudent(@ModelAttribute("person") Person person) {
        service.save(person);
        return "redirect:/personen";
    }

    @RequestMapping("/editperson/{person_id}")
    public ModelAndView showEditPersonpage(@PathVariable(name = "person_id") int id) {
        ModelAndView mav = new ModelAndView("newperson");
        Person person = service.getPersonId(id);
        mav.addObject("person", person);
        person = service.get(id);
        mav.addObject("person", person);
        return mav;
    }


    @RequestMapping("/deleteperson/{personId}")
    public String deletePerson(@PathVariable(name = "personId") int personId) {
        service.delete(personId);
        return "redirect:/personen";
    }

    @RequestMapping("/addKursToPerson/{personId}")
    public String assignKursToPerson(@PathVariable Long personId, @RequestParam Long kursId, @RequestParam String choix) {
        Person person = service.getPersonId(personId);
        Kurs kurs = service.getKurs(kursId);
        if (choix.equals("teilnehmer")) {
            person.schonInteressant(kurs);
            person.inKursteilnehmen.add(kurs);
            person.setInKursteilnehmen(person.inKursteilnehmen);
        } else {
            person.schonTeilnehmer(kurs);
            person.inKursinteressieren.add(kurs);
            person.setInKursinteressieren(person.inKursinteressieren);
        }
        service.save(person);
        return "redirect:/personen";

    }

    @PutMapping("/{person_id}/kurs/{kurs_id}")
    public Person assignKursToPerson(@PathVariable Long kurs_id, @PathVariable Long person_id) {
        return service.assignKursToPerson(kurs_id, person_id);
    }

    @RequestMapping("/get/{personId}")
    public ModelAndView getPersonId(@PathVariable Long personId) {
        String[] teilnehmer_interessant_arr = {"teilnehmer", "interessant"};
        ModelAndView mav = new ModelAndView("addPersonToKurs");
        mav.addObject("person", service.getPersonId(personId));
        mav.addObject("kurse", service.getAllkurs());
        mav.addObject("choix", teilnehmer_interessant_arr);
        return mav;
    }

}
