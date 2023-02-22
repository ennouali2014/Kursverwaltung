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
    @RequestMapping("/editperson/{person_id}")
    public ModelAndView showEditPersonpage(@PathVariable(name = "person_id") int id){
        ModelAndView mav = new ModelAndView("newperson");
        Person person =service.get(id);
        mav.addObject("person",person);
        return mav;
    }
    @RequestMapping("/deleteperson/{person_id}")
    public String deletePerson(@PathVariable(name="person_id") int id){
        service.delete(id);
        return "redirect:/personen";
    }
    @PutMapping("/{person_id}/kurs/{kurs_id}")
    public Person assignKursToPerson(@PathVariable Long kurs_id,@PathVariable Long person_id){
        return service.assignKursToPerson(kurs_id,person_id);
    }
   /* @RequestMapping ("/get/{person_id}")
    public String getPersonId(@PathVariable Long person_id, Model model){
        return service.getPersonId(person_id,model);
    }*/
}
