package com.example.kursverwaltung.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "kurs1")
public class Kurs1 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long kurs1Id;

    @Column(name = "kursname1", length = 100, nullable = false)
    private String kursname1;

    @Temporal(TemporalType.DATE)
    @Column(name = "start_datum1", nullable = false)
    private LocalDate start_datum1;

    @Column(name = "anzahl_tage1", nullable = false)
    private int anzahl_tage1;
    @Column(name = "zyklus1", nullable = false)
    private int zyklus1;
    @Temporal(TemporalType.DATE)
    @Column(name = "ende_datum1")
    private LocalDate ende_datum1;

    public Kurs1(String kursname1, LocalDate start_datum1, int anzahl_tage1, int zyklus1) {
        this.kursname1 = kursname1;
        this.start_datum1 = start_datum1;
        this.anzahl_tage1 = anzahl_tage1;
        this.zyklus1 = zyklus1;
    }

    public Kurs1() {
    }

    public Long getKurs1Id() {
        return kurs1Id;
    }

    public String getKursname1() {
        return kursname1;
    }

    public void setKursname1(String kursname1) {
        this.kursname1 = kursname1;
    }



    public LocalDate getStart_datum1() {
        return start_datum1;
    }

//        public void setStart_datum1 (String start_datum1){
//
//            String pattern = "dd-MM-yyyy";
//            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
//
//            try {
//                if (start_datum1 == null || start_datum1.isEmpty()) {
//                    this.convertedStartDate1 = new Date();
//                } else {
//
//                    this.convertedStartDate1 = simpleDateFormat.parse(start_datum1);
//                }
//            } catch (ParseException e) {
//                throw new RuntimeException(e);
//            }
//        }


    public void setStart_datum1(LocalDate start_datum1) {
        this.start_datum1 = start_datum1;
    }

    public int getAnzahl_tage1() {
        return anzahl_tage1;
    }

    public void setAnzahl_tage1(int anzahl_tage1) {
        this.anzahl_tage1 = anzahl_tage1;
    }

    public int getZyklus1() {
        return zyklus1;
    }

    public void setZyklus1(int zyklus1) {
        this.zyklus1 = zyklus1;
    }

    public LocalDate getEnde_datum1() {
        return ende_datum1;
    }

    public void setEnde_datum1(LocalDate ende_datum1) {
        this.ende_datum1 = ende_datum1;
    }
}
