package com.example.kursverwaltung.repository;

import com.example.kursverwaltung.domain.Kurs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KursRepository extends JpaRepository<Kurs,Long> {
    //Kurs findByKurs1Id(Long kurs1Id);
    Kurs findByKursId(Long kursId);
    @Query(value="select kursname from kurs", nativeQuery = true)
    List<String> getAllnameKurs();
    @Query(value = "select * from kurs k where k.kursname like %:keyword%",nativeQuery = true)
    List<Kurs> findByKeyword(@Param("keyword") String keyword);

}
