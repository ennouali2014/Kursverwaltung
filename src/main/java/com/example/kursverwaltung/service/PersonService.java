package com.example.kursverwaltung.service;

import com.example.kursverwaltung.domain.Kurs;
import com.example.kursverwaltung.domain.Person;
import com.example.kursverwaltung.repository.KursRepository;
import com.example.kursverwaltung.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Set;

@Service
public class PersonService {
    @Autowired
    private PersonRepository repo;
    @Autowired
    private KursRepository repoK;

    public void save(Person person){
        repo.save(person);
    }
    public Person get(long id){
        return repo.findById(id).get();
    }
    public void delete(long id){
        repo.deleteById(id);
    }

    public List<Person> listAll() {
        return repo.findAll();
    }

    public Person assignKursToPerson(Long kurs_id, Long person_id) {
        Set<Kurs> kurslist;
        Person person=repo.findById(person_id).get();
        Kurs kurs = repoK.findById(kurs_id).get();
        kurslist=person.getKurse();
        kurslist.add(kurs);
        person.setKurse(kurslist);
        return repo.save(person);
    }

    /*public String getPersonId(Long person_id, Model model) {
        Person findPersontId = repo.findByPersonId(person_id);

        model.addAttribute("title", "Data Student");
        model.addAttribute("kurse", repoK.findAll());
        model.addAttribute("personen", findPersontId);

        return "add_student_course";
    }*/
}
