package com.example.kursverwaltung.service;

import com.example.kursverwaltung.domain.Kurs;
import com.example.kursverwaltung.domain.Person;
import com.example.kursverwaltung.repository.KursRepository;
import com.example.kursverwaltung.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class KursService {
    @Autowired
    private KursRepository kursRepository;
    @Autowired
    private PersonRepository personRepository;
    public void save(Kurs kurs1){
        //
        kursRepository.save(kurs1);
    }
    public Kurs get(long id){
        return kursRepository.findByKursId(id);
    }
    public void delete(long id){
        kursRepository.deleteById(id);
    }
    public List<Kurs> listAll() {
        return kursRepository.findAll();
    }
    public List<Person> getAllPerson(){ return personRepository.findAll();}
    public Person getPerson(long personId){ return personRepository.findByPersonId(personId);}

    public List<Kurs> findByKeyword(String keyword){
        return kursRepository.findByKeyword(keyword);}


}