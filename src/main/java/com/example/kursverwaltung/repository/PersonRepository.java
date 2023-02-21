package com.example.kursverwaltung.repository;

import com.example.kursverwaltung.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person,Long> {
    Person findByPersonId(Long person_id);
}
