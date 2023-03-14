package com.example.kursverwaltung.controller;

import com.example.kursverwaltung.domain.Kurs;
import com.example.kursverwaltung.domain.Person;
import com.example.kursverwaltung.service.KursService;
import com.example.kursverwaltung.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.validation.Valid;
import java.util.List;
import java.util.Set;


/**
 * Die Klasse KurpersonController erhält die Requests vom Client und verarbeitett diese
 * Mittels Annotationen werden Verbindungen von Spring und der Datenbank hergestellt und auch spezifiziert, wie zb Beziehungen zwischen den Enteties
 */
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

    /**
     * Diese Methode erzeugt die Liste aller Personen im Templ personen, ggf auch sortiert nach einem Suchbegriff
     * @param model
     * @param keyword
     * @return
     */
    @GetMapping("/personen")
    public String viewHomePage(Model model, String keyword) {
        List<Person> listPerson = service.listAll();
        if (keyword != null) {
            model.addAttribute("listPerson", service.findByKeyword(keyword));
        } else {
            model.addAttribute("listPerson", listPerson);
        }
        return "personen";
    }

    /**
     * Diese Methode fügt eine neue Person der Liste hinzu, im Templ newperson
      */

    @GetMapping("/newperson")
    public String add(Person person, Model model) {
        model.addAttribute("person", new Person());
        return "newperson";
    }

    /**
     *  Diese Methode speichert eine neue Person, prüft ob sie das erste mal angelegt wird oder schon vorhonden ist,
     *
     * @param person
     * @param bindingResult
     * @param model
     * @return
     */
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

                if(p.getPersonId() != null){
                    service.save(person);
                    return "redirect:/person/personen";
                }
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

    /**
     * Diese Methode prüft den Pfad und liest die Id der Person aus, die editiert werden soll.
     * Wenn einzelne Attribute geändert wurden, werden diese hier für das Personobjekt neu gespeichert
     * @param id
     * @return
     */
    @RequestMapping("/editperson/{person_id}")
    public ModelAndView showEditPersonpage(@PathVariable(name = "person_id") int id) {
        ModelAndView mav = new ModelAndView("newperson");
        Person person = service.getPersonId(id);
        mav.addObject("person", person);
        return mav;
    }

    /**
     * Diese Methode prüft den Pfad und liest die Id der Person aus, die gelöscht werden soll und löscht diese
     * die Anzahl der freien Plätze wird neu gesetzt.
     * @param personId
     * @return
     */
    @RequestMapping("/deleteperson/{personId}")
    public String deletePerson(@PathVariable(name = "personId") int personId) {
        Person person=service.getPersonId(personId);
        Set<Kurs> kurse = person.getInKursteilnehmen();
        for (Kurs kurs:person.getInKursteilnehmen()){
            kurs.setFreie_plaetze(kurs.getFreie_plaetze()-1);
            serviceK.save(kurs);
        }
        service.delete(personId);
        return "redirect:/person/personen";
    }


    /**
     * Diese Methode liest die Id einer Person anhand des Pfades aus, übernimmmt die Parameter,
     * und validiert zb ob ein Kurs noch freie Plätze hat und fügt de kurs und Person in der entsprechenden Liste hinzu, ggf als TN oder Interessent
     * und validiert ob ein Kurs noch freie Plätze hat, erst dann wird gespeichert,
     * bei Speicherung wird die Anzahl an freien Plätzen neu gesetzt
     * @param personId
     * @param kursId
     * @param choix
     * @return
     */
    @RequestMapping("/addKursToPerson/{personId}")
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
        return "redirect:/person/get/" + personId + "?success=true";
    }
    /**
     * Diese Methode erzeugt ein Array mit zwei Werten für die Einordnung von Personen als TN oder Int
     * wird im Templ genutzt um eine Person als TN oder Interessent hinzuzufügen
     */
    @RequestMapping("/get/{personId}")
    public ModelAndView getPersonId(@PathVariable Long personId) {
        String[] teilnehmer_interessant_arr = {"Teilnehmer", "Interessent"};
        ModelAndView mav = new ModelAndView("addKursToPerson");
        mav.addObject("person", service.getPersonId(personId));
        mav.addObject("kurse", service.getAllkurs());
        mav.addObject("choix", teilnehmer_interessant_arr);
        return mav;
    }

    /**
     * Diese Methode zeigt eine Person mit allen Attributen(ohne Edit_möglichkeit), dessen Id anhand des Pfades ausgelesen wurde
     * @param personId
     * @return
     */
    @RequestMapping("/showPerson/{personId}")
    public ModelAndView showPerson(@PathVariable Long personId) {
        ModelAndView mav = new ModelAndView("showPerson");
        mav.addObject("person", service.getPersonId(personId));
        return mav;
    }

}
