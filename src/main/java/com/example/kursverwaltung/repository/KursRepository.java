package com.example.kursverwaltung.repository;

import com.example.kursverwaltung.domain.Kurs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KursRepository extends JpaRepository<Kurs,Long> {
    //Kurs findByKursId(Long kurs_id);
}
