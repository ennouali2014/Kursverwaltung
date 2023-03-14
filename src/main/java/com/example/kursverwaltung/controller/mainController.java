package com.example.kursverwaltung.controller;

import com.example.kursverwaltung.domain.Kurs;
import com.example.kursverwaltung.service.KursService;
import com.example.kursverwaltung.service.UserInfoUserDetailsService;
import com.example.kursverwaltung.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Controller
public class mainController {

    @Autowired
    private PersonService personService;
    @Autowired
    private KursService kursService;
    @Autowired
    private UserInfoUserDetailsService userDetailsService;

    @GetMapping("/home")
    public String viewHomePage(Model model) {
        Double umsatztdesMonat=0d;
        Double EinkommendesMonat=0d;
        Double UmsatzsteuerdesJahr=0d;
        Double EinkommendesJahr=0d;
        LocalDate today=LocalDate.now();
        LocalDate firstMonat = LocalDate.parse("2023-03-01");
        LocalDate neujahr = LocalDate.parse("2023-01-01");
        List<String> kursnamen = new ArrayList<>();
        for (Kurs kurs: kursService.listAll()){
            if(today.isAfter(kurs.getStart_datum()) && firstMonat.isBefore(kurs.getStart_datum())){
                umsatztdesMonat +=kurs.getGebuehr_brutto()*kurs.getTeilnehmer().size();
                EinkommendesMonat +=kurs.getGebuehr_netto()*kurs.getTeilnehmer().size();
            }
            if(neujahr.isBefore(kurs.getStart_datum())){
                UmsatzsteuerdesJahr +=kurs.getMwst_euro()*kurs.getTeilnehmer().size();
                EinkommendesJahr +=kurs.getGebuehr_netto()*kurs.getTeilnehmer().size();
            }
            if(kurs.getFreie_plaetze()==0){
                kursnamen.add(kurs.getKursname());
            }
        }
        model.addAttribute("umsatztdesMonat", umsatztdesMonat);
        model.addAttribute("EinkommendesMonat", EinkommendesMonat);
        model.addAttribute("UmsatzsteuerdesJahr", UmsatzsteuerdesJahr);
        model.addAttribute("EinkommendesJahr", EinkommendesJahr);
        model.addAttribute("kursnamen", kursnamen);
        return "home";
    }
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("listKurse",kursService.listAll());
        return "index";

    }
    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    @GetMapping("/public")
    public String personenlistePublic(Model model) {
        return "redirect:/person/personen";
    }



}
