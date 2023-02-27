package com.example.kursverwaltung.controller;

import com.example.kursverwaltung.domain.Kurs;
import com.example.kursverwaltung.domain.Person;
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

    @GetMapping("/index2")
    public String widget(){return "index2";}
    @GetMapping("/personen")
    public String viewHomePage(Model model, String keyword) {
        List<Person> listPerson = service.listAll();
        if(keyword!=null){
            model.addAttribute("listPerson",service.findByKeyword(keyword));
        }else{
            model.addAttribute("listPerson", listPerson);
        }
        return "personen";
    }

    @GetMapping("/newperson")
    public String add(Model model){
        model.addAttribute("person",new Person());
        return "newperson";
    }
    @PostMapping("/saveperson")
    public String saveperson(@ModelAttribute("person") Person person) {
        service.save(person);
        return "redirect:/personen";
    }
    @RequestMapping("/editperson/{personId}")
    public ModelAndView showEditPersonpage(@PathVariable(name = "personId") Long personId){
        ModelAndView mav = new ModelAndView("newperson");
        Person person =service.getPersonId(personId);
        mav.addObject("person",person);
        return mav;
    }
    @RequestMapping("/deleteperson/{personId}")
    public String deletePerson(@PathVariable(name="personId") int personId){
        service.delete(personId);
        return "redirect:/personen";
    }
    @RequestMapping("/addKursToPerson/{personId}")
    public String assignKursToPerson(@PathVariable Long personId,@RequestParam Long kursId,@RequestParam String choix){
        Person person=service.getPersonId(personId);
        Kurs kurs=service.getKurs(kursId);
       if (choix.equals("teilnehmer")){
           person.schonInteressant(kurs);
           person.inKursteilnehmen.add(kurs);
           person.setInKursteilnehmen(person.inKursteilnehmen);
       }else{
           person.schonTeilnehmer(kurs);
           person.inKursinteressieren.add(kurs);
           person.setInKursinteressieren(person.inKursinteressieren);
       }
        service.save(person);
        return "redirect:/personen";

    }
    @RequestMapping ("/get/{personId}")
    public ModelAndView getPersonId(@PathVariable Long personId){
        String[] teilnehmer_interessant_arr={"teilnehmer","interessant"};
        ModelAndView mav = new ModelAndView("addPersonToKurs");
        mav.addObject("person",service.getPersonId(personId));
        mav.addObject("kurse",service.getAllkurs());
        mav.addObject("choix",teilnehmer_interessant_arr);
        return mav;
    }

}
