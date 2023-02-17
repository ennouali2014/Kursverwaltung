package com.example.kursverwaltung.controller;

import com.example.kursverwaltung.domain.Kurs;
import com.example.kursverwaltung.service.KursService;
import com.example.kursverwaltung.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class KursController {

    @Autowired
    private KursService service;

    @GetMapping("/")
    public String viewHomePage(Model model) {
        List<Kurs> listKurse = service.listAll();
        model.addAttribute("listKurs", listKurse);
        System.out.println("Get / ");
        return "index";
    }

    @GetMapping("/new")
    public String add(Model model) {
        model.addAttribute("kurs", new Kurs());
        return "new";
    }

    @PostMapping("/save")
    public String saveKurs(@ModelAttribute("kurs") Kurs kurs) {
        return "redirect:/";
    }
    @RequestMapping("/edit/{id}")
    public ModelAndView showEditKursPage(@PathVariable(name = "id")int id){
        ModelAndView modelAndView=new ModelAndView("new");
        Kurs kurs=service.get(id);
        modelAndView.addObject("kurs",kurs);
        return modelAndView;
    }
    @RequestMapping("/delete/{id}")
    public String deleteKurs(@PathVariable(name = "id") int id){
        service.delete(id);
        return "redirect:/";
    }

}
