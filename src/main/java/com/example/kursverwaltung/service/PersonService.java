package com.example.kursverwaltung.service;

import com.example.kursverwaltung.domain.Kurs;
import com.example.kursverwaltung.domain.Person;
import com.example.kursverwaltung.repository.KursRepository;
import com.example.kursverwaltung.repository.PersonRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {
    @Autowired
    private PersonRepository repo;

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
    public List<Kurs> getAllkurs(){
        return repoK.findAll();
    }
    public Kurs getKurs(long kursId){return repoK.findByKursId(kursId);}
    public List<Person> findByKeyword(String keyword){
        return repo.findByKeyword(keyword);}


}
