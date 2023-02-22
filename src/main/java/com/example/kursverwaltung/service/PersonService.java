package com.example.kursverwaltung.service;

import com.example.kursverwaltung.domain.Kurs;
import com.example.kursverwaltung.domain.Person;
import com.example.kursverwaltung.repository.KursRepository;
import com.example.kursverwaltung.repository.PersonRepository;

import com.mysql.cj.Session;
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
    public Person get(long personId){
        return repo.findById(personId).get();
    }
    public void delete(long personId){
        repo.deleteById(personId);
    }

    public List<Person> listAll() {
        return repo.findAll();
    }

    /*public Person assignKursToPerson(Long kursId, Long personId) {

        // Set<Kurs> kurslist;
        // Start a transaction

        Person person=
        Kurs kurs =
        person.kurse.add(kurs);
        //kurslist.add(kurs);
        //person.setKurse(kurslist);
        return repo.save(person);
    }*/

    public Person getPersonId(Long personId) {
        return repo.findByPersonId(personId);
    }
    public List<Kurs> getAllkurs(){
        return repoK.findAll();
    }
    public Kurs getKurs(Long kursId){

        return repoK.findByKursId(kursId);
    }


}
