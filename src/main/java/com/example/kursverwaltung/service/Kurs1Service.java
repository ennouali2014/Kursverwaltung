package com.example.kursverwaltung.service;

import com.example.kursverwaltung.domain.Kurs1;
import com.example.kursverwaltung.repository.Kurs1Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class Kurs1Service {
    @Autowired
    private Kurs1Repository kurs1Repository;

    public void save(Kurs1 kurs1){
        //
        kurs1Repository.save(kurs1);
    }
    public Kurs1 get(long id){
        return kurs1Repository.findById(id).get();
    }
    public void delete(long id){
        kurs1Repository.deleteById(id);
    }
    public List<Kurs1> listAll() {
        return kurs1Repository.findAll();
    }

}