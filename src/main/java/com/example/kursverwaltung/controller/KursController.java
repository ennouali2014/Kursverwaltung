package com.example.kursverwaltung.controller;

import com.example.kursverwaltung.domain.Kurs;
import com.example.kursverwaltung.domain.Person;
import com.example.kursverwaltung.service.KursService;
import com.example.kursverwaltung.service.PersonService;
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

        if(keyword!=null){
            model.addAttribute("listKurse",service.findByKeyword(keyword));
        }else{
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
    public String saveKurs(@ModelAttribute ("kurs1") Kurs kurs1,
                            @RequestParam("start_datum") LocalDate start_datum1_L,
                            @RequestParam("aktuelle_tn_anzahl") int aktuelle_tn_anzahl1,
                            @RequestParam("max_tn_anzahl") int max_tn_anzahl1,
                            @RequestParam("gebuehr_brutto") Double gebuehr_brutto1,
                            @RequestParam("mwst_prozent") Double mwst_prozent1) {
//        if (kurs1 == null || start_datum1_L == null || aktuelle_tn_anzahl1 == null || max_tn_anzahl1 == null) {
//
//            return "error";
//        }

        kurs1.setStart_datum(start_datum1_L);
        // long milliseconds = Math.round((float) kurs1.getAnzahl_tage1() / kurs1.getZyklus1()) * 7 * 86400000L;
        long milliseconds = (Math.round((float) (kurs1.getAnzahl_tage() / kurs1.getZyklus()) - 1) * 7L + kurs1.getZyklus()) * 86400000L;
        kurs1.setEnde_datum(kurs1.getStart_datum().plusDays((milliseconds / 86400000L)));

        kurs1.setMax_tn_anzahl(max_tn_anzahl1);
        kurs1.setAktuelle_tn_anzahl(aktuelle_tn_anzahl1);
        if (kurs1.getMax_tn_anzahl() >= kurs1.getAktuelle_tn_anzahl()) {
            kurs1.setFreie_plaetze(kurs1.getMax_tn_anzahl() - kurs1.getAktuelle_tn_anzahl());

        } else {

            kurs1.setFreie_plaetze(0);
        }
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
        return modelAndView1;
    }

    @RequestMapping("/kurs1/deletekurs/{kursId}")
    public String deleteKurs(@PathVariable(name = "kursId") int kursId) {
        service.delete(kursId);
        return "redirect:/k1/kurs1/kurse";
    }
    @RequestMapping("/kurs1/get/{kursId}")
    public ModelAndView getKursId(@PathVariable Long kursId) {
        String[] teilnehmer_interessant_arr = {"teilnehmer", "interessant"};
        ModelAndView mav = new ModelAndView("addKursToPerson");
        mav.addObject("kurs", service.get(kursId));
        mav.addObject("personen", service.getAllPerson());
        mav.addObject("choix", teilnehmer_interessant_arr);
        return mav;
    }
    @RequestMapping ("/kurs1/addKursToPerson/{kursId}")
    public String assignPersonToKurs(@PathVariable Long kursId,@RequestParam Long personId,@RequestParam String choix){
        Person person = service.getPerson(personId);
        Kurs kurs = service.get(kursId);
        if (choix.equals("teilnehmer")) {
            System.out.println("---1-----"+person);
            if(kurs.getInteressant().size()>0){kurs.hadInteressant(person);}
            System.out.println("---2-----"+kurs.getTeilnehmer());
            kurs.getTeilnehmer().add(person);
            System.out.println("---3-----"+kurs.getTeilnehmer());
            kurs.setTeilnehmer(kurs.getTeilnehmer());
            System.out.println("---4-----"+kurs.getTeilnehmer());
            person.getInKursteilnehmen().add(kurs);
            System.out.println("---5-----"+person.getInKursteilnehmen());
        } else {
            if(kurs.getTeilnehmer().size()>0){kurs.hadTeilnehmer(person);}
            kurs.getInteressant().add(person);
            kurs.setInteressant(kurs.getInteressant());
            person.getInKursinteressieren().add(kurs);
        }
        service.save(kurs);
        personService.save(person);
        return "redirect:/k1/kurs1/kurse";
    }

}


