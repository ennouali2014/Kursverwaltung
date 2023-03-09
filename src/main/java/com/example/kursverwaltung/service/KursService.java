package com.example.kursverwaltung.service;

import com.example.kursverwaltung.domain.Kurs;
import com.example.kursverwaltung.domain.Person;
import com.example.kursverwaltung.repository.KursRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class KursService {
    @Autowired
    private KursRepository kursRepository;

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
    public List<Kurs> findByKeyword(String keyword){
        return kursRepository.findByKeyword(keyword);}

}