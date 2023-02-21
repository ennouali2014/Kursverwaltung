package com.example.kursverwaltung.controller;

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

    @GetMapping("/person")
    public String viewHomePage(Model model) {
        List<Person> listPerson = service.listAll();
        model.addAttribute("listPerson", listPerson);
        System.out.print("Get / ");
        return "index";
    }

    @GetMapping("/new")
    public String add(Model model){
        model.addAttribute("person",new Person());
        return "new";
    }
    @PostMapping("/save")
    public String saveStudent(@ModelAttribute("person") Person person) {
        service.save(person);
        return "redirect:/";
    }
    @RequestMapping("/edit/{id}")
    public ModelAndView showEditPersonpage(@PathVariable(name = "id") int id){
        ModelAndView mav = new ModelAndView("new");
        Person person =service.get(id);
        mav.addObject("person",person);
        return mav;
    }
    @RequestMapping("/delete/{id}")
    public String deletePerson(@PathVariable(name="id") int id){
        service.delete(id);
        return "redirect:/";
    }
}
