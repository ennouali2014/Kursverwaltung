package com.example.kursverwaltung.service;

import com.example.kursverwaltung.domain.Kurs;
import com.example.kursverwaltung.domain.Person;
import com.example.kursverwaltung.domain.UserInfo;
import com.example.kursverwaltung.repository.KursRepository;
import com.example.kursverwaltung.repository.PersonRepository;
import com.example.kursverwaltung.repository.UserInfoRepository;

import com.mysql.cj.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Set;

@Service
public class PersonService {
    @Autowired
    private PersonRepository repo;

  /*  @Autowired
    private UserInfoRepository userInfoRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;*/

    @Autowired
    private KursRepository repoK;

    public void save(Person person) {
        repo.save(person);
    }

    public Person get(long id) {
        return repo.findById(id).get();
    }

    public void delete(long id) {
        repo.deleteById(id);
    }


    public List<Person> listAll() {
        return repo.findAll();
    }

    public Person getPersonId(long personId) {
        return repo.findByPersonId(personId);
    }

    public Person assignKursToPerson(Long kurs_id, Long person_id) {
        Set<Kurs> kurslist;
        Person person = repo.findById(person_id).get();
        Kurs kurs = repoK.findById(kurs_id).get();
        kurslist = person.getInKursteilnehmen();
        kurslist.add(kurs);
        person.setKurse(kurslist);  //TODO setKurse in Person Klasse?!
        return repo.save(person);
    }

    public List<Kurs> getAllkurs() {
        return repoK.findAll();
    }

    public Kurs getKurs(long kursId) {
        return repoK.findByKursId(kursId);
    }

//        model.addAttribute("title","Data Student");
//        model.addAttribute("kurse",repoK.findAll());
//        model.addAttribute("personen",findPersontId);
//
//        return"add_student_course";
//}*/
/*
    public String addUser(UserInfo userInfo) {
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        userInfoRepository.save(userInfo);
        return "user added to system";
    }*/

}
