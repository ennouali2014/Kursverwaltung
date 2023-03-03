package com.example.kursverwaltung.repository;

import com.example.kursverwaltung.domain.Kurs;
import com.example.kursverwaltung.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface PersonRepository extends JpaRepository<Person,Long> {
    Person findByPersonId(Long persondId);
    @Query(value = "select * from person p where p.vorname like %:keyword% or p.nachname like %:keyword% or p.email like %:keyword% or p.ort like %:keyword% or p.strasse like %:keyword% ",nativeQuery = true)
    List<Person> findByKeyword(@Param("keyword") String keyword);

}
