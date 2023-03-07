package com.example.kursverwaltung.controller;

import com.example.kursverwaltung.domain.Kurs1;
import com.example.kursverwaltung.service.Kurs1Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/k1")
public class Kurs1Controller {

    @Autowired
    private Kurs1Service service;

    @GetMapping("/kurs1")
    public String viewHomePage(Model model) {
        List<Kurs1> listKurse1 = service.listAll();
        model.addAttribute("listKurse1", listKurse1);
        // System.out.println("Get / ");
        return "kurs1";
    }

    @GetMapping("/kurs1/newkurs1")
    public String add(Model model) {
        model.addAttribute("kurs1", new Kurs1());
        return "newkurs1";
    }

    @PostMapping("/savekurs1")
    //public String save(@ModelAttribute("yourModelObject") YourModelClass model)

    public String saveKurs1(@ModelAttribute ("kurs1") Kurs1 kurs1,
                            @RequestParam("start_datum1") @DateTimeFormat(pattern = "dd.MM.yyyy") LocalDate start_datum1_L,
                            @RequestParam("aktuelle_tn_anzahl1") int aktuelle_tn_anzahl1,
                            @RequestParam("max_tn_anzahl1") int max_tn_anzahl1,
                            @RequestParam("gebuehr_brutto1") Double gebuehr_brutto1,
                            @RequestParam("mwst_prozent1") Double mwst_prozent1) {
//        if (kurs1 == null || start_datum1_L == null || aktuelle_tn_anzahl1 == null || max_tn_anzahl1 == null) {
//
//            return "error";
//        }

        kurs1.setStart_datum1(start_datum1_L);
        // long milliseconds = Math.round((float) kurs1.getAnzahl_tage1() / kurs1.getZyklus1()) * 7 * 86400000L;
        long milliseconds = (Math.round((float) (kurs1.getAnzahl_tage1() / kurs1.getZyklus1()) - 1) * 7L + kurs1.getZyklus1()) * 86400000L;
        kurs1.setEnde_datum1(kurs1.getStart_datum1().plusDays((milliseconds / 86400000L)));

        kurs1.setMax_tn_anzahl1(max_tn_anzahl1);
        kurs1.setAktuelle_tn_anzahl1(aktuelle_tn_anzahl1);
        if (kurs1.getMax_tn_anzahl1() >= kurs1.getAktuelle_tn_anzahl1()) {
            kurs1.setFreie_plaetze1(kurs1.getMax_tn_anzahl1() - kurs1.getAktuelle_tn_anzahl1());

        } else {

            kurs1.setFreie_plaetze1(0);
        }
        kurs1.setGebuehr_brutto1(gebuehr_brutto1);
        kurs1.setMwst_prozent1(mwst_prozent1);
        kurs1.setGebuehr_netto1(Math.round((kurs1.getGebuehr_brutto1() / (100 + kurs1.getMwst_prozent1()) * 100) * 100.0) / 100.0);
        kurs1.setMwst_euro1(Math.round((kurs1.getGebuehr_brutto1() / (100 + kurs1.getMwst_prozent1()) * kurs1.getMwst_prozent1()) * 100.0) / 100.0);

        service.save(kurs1);
        return "redirect:/k1/kurs1";

        //(pattern = "dd.MM.yyyy") (iso = DateTimeFormat.ISO.DATE)

    }  //@ModelAttribute annotation is used to bind the model object from the form submission.


    @RequestMapping("/editkurs1/{kurs1Id}")
    public ModelAndView showEditKursPage(@PathVariable(name = "kurs1Id") int kurs1Id) {
        ModelAndView modelAndView1 = new ModelAndView("new");
        Kurs1 kurs1 = service.get(kurs1Id);
        modelAndView1.addObject("kurs1", kurs1);
        return modelAndView1;
    }

    @RequestMapping("/deletekurs1/{kurs1Id}")
    public String deleteKurs(@PathVariable(name = "kurs1Id") int kurs1Id) {
        service.delete(kurs1Id);
        return "redirect:/k1/kurs1";
    }

}


