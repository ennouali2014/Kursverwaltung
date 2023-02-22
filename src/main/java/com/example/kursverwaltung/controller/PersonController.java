package com.example.kursverwaltung.controller;

import com.example.kursverwaltung.domain.Kurs;
import com.example.kursverwaltung.domain.Person;
import com.example.kursverwaltung.service.KursService;
import com.example.kursverwaltung.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class PersonController {

    @Autowired
    private PersonService service;

    @GetMapping("/personen")
    public String viewHomePage(Model model) {
        List<Person> listPerson = service.listAll();
        model.addAttribute("listPerson", listPerson);
        //System.out.print("Get / ");
        return "personen";
    }

    @GetMapping("/newperson")
    public String add(Model model){
        model.addAttribute("person",new Person());
        return "newperson";
    }
    @PostMapping("/saveperson")
    public String saveStudent(@ModelAttribute("person") Person person) {
        service.save(person);
        return "redirect:/personen";
    }
    @RequestMapping("/editperson/{personId}")
    public ModelAndView showEditPersonpage(@PathVariable(name = "personId") int personId){
        ModelAndView mav = new ModelAndView("newperson");
        Person person =service.get(personId);
        mav.addObject("person",person);
        return mav;
    }
    @RequestMapping("/deleteperson/{personId}")
    public String deletePerson(@PathVariable(name="personId") int personId){
        service.delete(personId);
        return "redirect:/personen";
    }
    @RequestMapping("/addKursToPerson/{personId}")
    public String assignKursToPerson(@RequestParam Long kursId,@PathVariable Long personId){
        service.assignKursToPerson(kursId,personId);
        return "redirect:/personen";

    }
    @RequestMapping ("/get/{personId}")
    public ModelAndView getPersonId(@PathVariable Long personId){
        ModelAndView mav = new ModelAndView("addPersonToKurs");
        mav.addObject("person",service.getPersonId(personId));
        mav.addObject("kurse",service.getAllkurs());
        return mav;
    }
}
