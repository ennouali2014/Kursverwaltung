package com.example.kursverwaltung.controller;

import com.example.kursverwaltung.domain.Kurs;
import com.example.kursverwaltung.domain.Person;
import com.example.kursverwaltung.service.KursService;
import com.example.kursverwaltung.service.PersonService;
//import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;


@Controller
@RequestMapping("/person")
public class PersonController {
    @Autowired
    private PersonService service;
    @Autowired
    private KursService serviceK;

    @GetMapping("/welcome")
    public String welcome() {
        return "welcome";
    }

    @GetMapping("/personen")
    // @PreAuthorize("hasAuthority('ADMIN')")
    public String viewHomePage(Model model, String keyword) {
        List<Person> listPerson = service.listAll();
        if (keyword != null) {
            model.addAttribute("listPerson", service.findByKeyword(keyword));
        } else {
            model.addAttribute("listPerson", listPerson);
        }
        return "personen";
    }

    @GetMapping("/newperson")
    //@PreAuthorize("hasAuthority('ADMIN')")
    public String add(Person person, Model model) {
        System.out.println("Form");
        model.addAttribute("person", new Person());
        return "newperson";
    }

    @PostMapping("/saveperson")
    public String savePerson(@Valid Person person,
                             BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().stream().forEach(System.out::println);
            model.addAttribute("person", person);
            return "newperson";
        }

        List<Person> listPerson = service.listAll();
        if(!listPerson.isEmpty()) {
            for (Person p : listPerson) {
                boolean isSame = p.equals(person);

                if (isSame) {
                    model.addAttribute("isSame", isSame);
                    model.addAttribute("error", "Eine Person mit denselben Details(Vorname, Nachname, Email) existiert bereits.");
                    return "newperson";
                }
            }
        }
        service.save(person);
        return "redirect:/person/personen";
    }


    @RequestMapping("/editperson/{person_id}")
    public ModelAndView showEditPersonpage(@PathVariable(name = "person_id") int id) {
        ModelAndView mav = new ModelAndView("newperson");
        Person person = service.getPersonId(id);
        mav.addObject("person", person);
        //person = service.get(id);
        //mav.addObject("person", person);
        return mav;
    }

    @RequestMapping("/deleteperson/{personId}")
    public String deletePerson(@PathVariable(name = "personId") int personId) {
        service.delete(personId);
        return "redirect:/person/personen";
    }

    @RequestMapping("/addPersonToKurs/{personId}")
    public String assignKursToPerson(@PathVariable Long personId, @RequestParam Long kursId, @RequestParam String choix) {
        Person person = service.getPersonId(personId);
        Kurs kurs = service.getKurs(kursId);
        if (choix.equals("Teilnehmer")) {
            if(kurs.getFreie_plaetze()>0){
                person.schonInteressant(kurs);
                person.setInKursteilnehmen(person.add(kurs,person.getInKursteilnehmen()));
                kurs.add(person,kurs.getTeilnehmer());
                kurs.setFreie_plaetze(kurs.getMax_tn_anzahl()-kurs.getTeilnehmer().size());
            }
        } else {
            person.schonTeilnehmer(kurs);
            person.getInKursinteressieren().add(kurs) ;
            person.setInKursinteressieren(person.getInKursinteressieren());
            kurs.getInteressant().add(person);
        }
        serviceK.save(kurs);
        service.save(person);
        //return "redirect:/person/personen";
        return "redirect:/person/get/" + personId + "?success=true";
    }
    @RequestMapping("/get/{personId}")
    public ModelAndView getPersonId(@PathVariable Long personId) {
        String[] teilnehmer_interessant_arr = {"Teilnehmer", "Interessent"};
        ModelAndView mav = new ModelAndView("addKursToPerson");
        mav.addObject("person", service.getPersonId(personId));
        mav.addObject("kurse", service.getAllkurs());
        mav.addObject("choix", teilnehmer_interessant_arr);
        return mav;
    }
    @RequestMapping("/showPerson/{personId}")
    public ModelAndView showPerson(@PathVariable Long personId) {
        ModelAndView mav = new ModelAndView("showPerson");
        mav.addObject("person", service.getPersonId(personId));
        return mav;
    }

}
