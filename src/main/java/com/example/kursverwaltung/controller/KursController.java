package com.example.kursverwaltung.controller;

import com.example.kursverwaltung.domain.Kurs;
import com.example.kursverwaltung.domain.Kurs1;
import com.example.kursverwaltung.service.KursService;
import com.example.kursverwaltung.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

@Controller
//@RequestMapping("/k")
public class KursController {

    @Autowired
    private KursService service;

    @GetMapping("/kurs")
    public String viewHomePage(Model model) {
        List<Kurs> listKurse = service.listAll();
        model.addAttribute("listKurse", listKurse);
        // System.out.println("Get / ");
        return "kurs";
    }

    @GetMapping("/kurs/newkurs")
    public String add(Model model) {
        model.addAttribute("kurs", new Kurs());
        return "newkurs";
    }

    @PostMapping("/savekurs")
    public String saveKurs(@ModelAttribute Kurs kurs, @RequestParam("start_datum") @DateTimeFormat(pattern = "dd.MM.yyyy") LocalDate start_datum1_L) {
        kurs.setStart_datum(start_datum1_L);
        long milliseconds = Math.round((float) kurs.getAnzahl_tage() / kurs.getZyklus()) * 7 * 86400000L;
        kurs.setEnde_datum(kurs.getStart_datum().plusDays((milliseconds / 86400000L)));
        service.save(kurs);
        return "redirect:/kurs";

        // @PostMapping("/save")
//        public String saveKurs(@ModelAttribute Kurs kurs, @RequestParam("start_datum") String startDatumStr) {
//            try {
//                LocalDate startDatum = LocalDate.parse(startDatumStr);
//                kurs.setStart_datum(startDatum);
//                kurs.setEnde_datum(startDatum.plusDays(kurs.getAnzahl_tage() * kurs.getZyklus()));
//                kurs.setAktuelle_tn_anzahl(0);
//                kurs.setFreie_plaetze(kurs.getMax_tn_anzahl()-kurs.getAktuelle_tn_anzahl);
//                kurs.setGebuehr_netto(Math.round((kurs.getGebuehr_brutto() / (100 + kurs.getMwst_prozent()) * 100) * 100.0) / 100.0);
//                kurs.setMwst_euro(Math.round((kurs.getGebuehr_brutto() / (100 + kurs.getMwst_prozent()) * kurs.getMwst_prozent()) * 100.0) / 100.0);
//                service.save(kurs);
//                return "redirect:/";
//            } catch (DateTimeParseException e) {
//                return "redirect:/?error=startdatum";
//            }
//        }
//        we're using the @RequestParam annotation to retrieve the value of the `start

    }

    @RequestMapping("/editkurs/{kursId}")
    public ModelAndView showEditKursPage(@PathVariable(name = "kursId") int kursId) {
        ModelAndView modelAndView = new ModelAndView("newkurs");
        Kurs kurs = service.get(kursId);
        modelAndView.addObject("kurs", kurs);
        return modelAndView;
    }

    @RequestMapping("/deletekurs/{kursId}")
    public String deleteKurs(@PathVariable(name = "kursId") int kursId) {
        service.delete(kursId);
        return "redirect:/kurs";
    }

//    @RequestMapping("/get/{kursId}")
//    public ModelAndView getKursId(@PathVariable Long kursId) {
//    }
}
