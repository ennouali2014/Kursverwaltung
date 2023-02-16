package com.example.kursverwaltung.service;

import com.example.kursverwaltung.domain.Person;
import com.example.kursverwaltung.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {
    @Autowired
    private PersonRepository repo;


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
}
