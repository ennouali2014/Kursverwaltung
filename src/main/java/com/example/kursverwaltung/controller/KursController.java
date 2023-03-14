package com.example.kursverwaltung.controller;

import com.example.kursverwaltung.domain.Kurs;
import com.example.kursverwaltung.domain.Person;
import com.example.kursverwaltung.service.KursService;
import com.example.kursverwaltung.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/k1")
public class KursController {

    @Autowired
    private KursService service;
    @Autowired
    private PersonService personService;

    @GetMapping("/kurs1/kurse")
    // @PreAuthorize("hasAuthority('ADMIN')")
    public String viewHomePage(Model model, String keyword) {
        List<Kurs> listKurse = service.listAll();
//
//        model.addAttribute("listkurse", listkurse);
//        // System.out.println("Get / ");
//        return "kurse";

        if (keyword != null) {
            model.addAttribute("listKurse", service.findByKeyword(keyword));
        } else {
            model.addAttribute("listKurse", listKurse);
        }
        return "kurse";
//
//        model.addAttribute("kurse", listKurse);
//        // System.out.println("Get / ");
//        return "kurse";

//        return "kurse";

    }

    @GetMapping("/kurs1/newkurs")
    public String add(Model model) {
        model.addAttribute("kurs1", new Kurs());
        return "newkurs";
    }


    @PostMapping("/kurs1/savekurs")
    //public String save(@ModelAttribute("yourModelObject") YourModelClass model)
    public String saveKurs(@ModelAttribute("kurs1") @Valid Kurs kurs1,
                           BindingResult result,
                           @RequestParam("start_datum") @DateTimeFormat(pattern = "dd.MM.yyyy") LocalDate start_datum1_L,
                           //@RequestParam("aktuelle_tn_anzahl") int aktuelle_tn_anzahl1,
                           @RequestParam("max_tn_anzahl") int max_tn_anzahl1,
                           @RequestParam("gebuehr_brutto") Double gebuehr_brutto1,
                           @RequestParam("mwst_prozent") Double mwst_prozent1,
                           Model model) {
//        if (kurs1 == null || start_datum1_L == null || aktuelle_tn_anzahl1 == null || max_tn_anzahl1 == null) {
//
//            return "error";
//        }

        // Validation of the startdatum
        LocalDate today = LocalDate.now();
        if (kurs1.getKursId() == null) {
            if (start_datum1_L.isBefore(today)) {
                result.rejectValue("start_datum", "invalid.date", "Startdatum darf nicht vor dem heutigen Datum liegen.");
                return "newkurs";
            }
        } else {
            kurs1.setStart_datum(start_datum1_L);

        }
        //Calculating the Endedatum
        long milliseconds = 0L;
        if (kurs1.getAnzahl_tage() > kurs1.getZyklus()) {
            milliseconds = (Math.round((float) (kurs1.getAnzahl_tage() / kurs1.getZyklus()) - 1) * 7L + kurs1.getZyklus()) * 86400000L;

        } else {
            milliseconds = kurs1.getAnzahl_tage() * 86400000L;
        }
        kurs1.setEnde_datum(kurs1.getStart_datum().plusDays((milliseconds / 86400000L)));


        //validation
        if (result.hasErrors()) {

            model.addAttribute("max_tn_anzahl1_error", "has-error");
            return "newkurs";
        }

//        if (kurs1.getMax_tn_anzahl() < 0) {
//            result.rejectValue("max_tn_anzahl", "negative.value", "Der Wert darf nicht negative sein.");
//        }

        // Validation of Max Teilnehmer Anzahl
        if (kurs1.getMax_tn_anzahl() < kurs1.getMin_tn_anzahl()) {
            System.out.println("Vorher");
            result.rejectValue("max_tn_anzahl", "invalid.value", " Der Wert muss groesser oder gleich der Min Teilnehmer Anzahl sein.");
            System.out.println("Nachher");
        } else {

            kurs1.setMax_tn_anzahl(max_tn_anzahl1);
        }


        if (result.hasErrors()) {
            System.out.println("Fehler");
            model.addAttribute("max_tn_anzahl1_error", "has-error");
            return "newkurs";
        }

        if (kurs1.getKursId() != null) {
            Kurs kurs = service.get(kurs1.getKursId());
            System.out.println("kurs.getTeilnehmer().size():--" + kurs.getTeilnehmer().size());
            kurs1.setFreie_plaetze(kurs1.getMax_tn_anzahl() - kurs.getTeilnehmer().size());
            kurs1.setAktuelle_tn_anzahl(kurs.getTeilnehmer().size());
        } else {
            kurs1.setFreie_plaetze(kurs1.getMax_tn_anzahl() - kurs1.getTeilnehmer().size());
            kurs1.setAktuelle_tn_anzahl(kurs1.getTeilnehmer().size());
        }


        // Calculating Gebuer_brutto & Mehrwertsteuer_Euro

        kurs1.setGebuehr_brutto(gebuehr_brutto1);
        kurs1.setMwst_prozent(mwst_prozent1);
        kurs1.setGebuehr_netto(Math.round((kurs1.getGebuehr_brutto() / (100 + kurs1.getMwst_prozent()) * 100) * 100.0) / 100.0);
        kurs1.setMwst_euro(Math.round((kurs1.getGebuehr_brutto() / (100 + kurs1.getMwst_prozent()) * kurs1.getMwst_prozent()) * 100.0) / 100.0);

        service.save(kurs1);
        return "redirect:/k1/kurs1/kurse";

        //(pattern = "dd.MM.yyyy") (iso = DateTimeFormat.ISO.DATE)

    }  //@ModelAttribute annotation is used to bind the model object from the form submission.


    @RequestMapping("/kurs1/editkurs/{kursId}")
    public ModelAndView showEditKursPage(@PathVariable(name = "kursId") int kursId) {
        ModelAndView modelAndView1 = new ModelAndView("newkurs");
        Kurs kurs1 = service.get(kursId);
        modelAndView1.addObject("kurs1", kurs1);
        System.out.println("kurs1.getTeilnehmer().size():++" + kurs1.getTeilnehmer().size());
        return modelAndView1;
    }

    @RequestMapping("/kurs1/deletekurs/{kursId}")
    public String deleteKurs(@PathVariable(name = "kursId") int kursId) {
        service.delete(kursId);
        return "redirect:/k1/kurs1/kurse";
    }

    @RequestMapping("/kurs1/get/{kursId}")
    public ModelAndView getKursId(@PathVariable Long kursId) {
        String[] teilnehmer_interessant_arr = {"Teilnehmer", "Interessent"};
        ModelAndView mav = new ModelAndView("addPersonToKurs");
        mav.addObject("kurs", service.get(kursId));
        mav.addObject("personen", service.getAllPerson());
        mav.addObject("choix", teilnehmer_interessant_arr);
        return mav;
    }

    @RequestMapping("/kurs1/showKurs/{kursId}")
    public ModelAndView showKurs(@PathVariable Long kursId) {
        ModelAndView mav = new ModelAndView("showKurs");
        mav.addObject("kurs", service.get(kursId));
        return mav;
    }

    @RequestMapping("/kurs1/addPersonToKurs/{kursId}")
    public String assignPersonToKurs(@PathVariable Long kursId, @RequestParam Long personId, @RequestParam String
            choix) {
        Person person = service.getPerson(personId);
        Kurs kurs = service.get(kursId);
        if (choix.equals("Teilnehmer")) {
            if (kurs.getFreie_plaetze() > 0) {
                kurs.hadInteressant(person);
                kurs.setTeilnehmer(kurs.add(person, kurs.getTeilnehmer()));
                kurs.setFreie_plaetze(kurs.getMax_tn_anzahl() - kurs.getTeilnehmer().size());
                person.add(kurs, person.getInKursteilnehmen());

            }

            kurs.getTeilnehmer().add(person);

            kurs.setTeilnehmer(kurs.getTeilnehmer());

            person.getInKursteilnehmen().add(kurs);

            //Calculating actual participant count
            kurs.setAktuelle_tn_anzahl(kurs.getTeilnehmer().size());
        } else {
            kurs.hadTeilnehmer(person);
            kurs.getInteressant().add(person);
            kurs.setInteressant(kurs.getInteressant());
            person.getInKursinteressieren().add(kurs);
        }
        //Calculating Free Places
        if (kurs.getMax_tn_anzahl() > kurs.getAktuelle_tn_anzahl()) {
            kurs.setFreie_plaetze(kurs.getMax_tn_anzahl() - kurs.getAktuelle_tn_anzahl());
        } else {
            kurs.setFreie_plaetze(0);
        }
        service.save(kurs);
        personService.save(person);
        //return "redirect:/k1/kurs1/kurse";
        return "redirect:/k1/kurs1/get/" + kursId + "?success=true";
    }


}




