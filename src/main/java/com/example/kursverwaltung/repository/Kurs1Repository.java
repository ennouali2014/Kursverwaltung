package com.example.kursverwaltung.repository;

import com.example.kursverwaltung.domain.Kurs1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Kurs1Repository extends JpaRepository<Kurs1,Long> {
    Kurs1 findByKurs1Id(Long kurs1Id);

}
