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
    @Column(name = "status1", length = 100, nullable = false)
    private String status1;
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
    @Column(name = "min_tn_anzahl1", nullable = false)
    private int min_tn_anzahl1;
    @Column(name = "max_tn_anzahl1", nullable = false)
    private int max_tn_anzahl1;
    @Column(name = "freie_plaetze1")
    private int freie_plaetze1 = 0;
    @Column(name = "aktuelle_tn_anzahl1", nullable = false)
    // spaeter wird nicht nullable sondern in add person in kurs Kalkuliert
    private int aktuelle_tn_anzahl1;

    @Column(name = "gebuehr_brutto1", nullable = false)
    private Double gebuehr_brutto1;

    @Column(name = "gebuehr_netto1")
    private Double gebuehr_netto1;

    @Column(name = "mwst_prozent1", nullable = false)
    private Double mwst_prozent1;

    @Column(name = "mwst_euro1", nullable = false)
    private Double mwst_euro1;

    @Column(name = "kurs_beschreibung", length = 600, nullable = false)
    private String kurs_beschreibung;

    public Kurs1(String kursname1, LocalDate start_datum1, int anzahl_tage1, int zyklus1, Double gebuehr_brutto1, Double mwst_prozent1, int min_tn_anzahl1, int max_tn_anzahl1, int aktuelle_tn_anzahl1, String status1,String kurs_beschreibung) {
        this.kursname1 = kursname1;
        this.start_datum1 = start_datum1;
        this.anzahl_tage1 = anzahl_tage1;
        this.zyklus1 = zyklus1;
        this.gebuehr_brutto1 = gebuehr_brutto1;
        this.mwst_prozent1 = mwst_prozent1;
        this.min_tn_anzahl1 = min_tn_anzahl1;
        this.max_tn_anzahl1 = max_tn_anzahl1;
        this.aktuelle_tn_anzahl1 = aktuelle_tn_anzahl1;
        this.status1 = status1;
        this.kurs_beschreibung=kurs_beschreibung;
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

    public Double getGebuehr_brutto1() {
        return gebuehr_brutto1;
    }

    public void setGebuehr_brutto1(Double gebuehr_brutto1) {
        this.gebuehr_brutto1 = gebuehr_brutto1;
    }

    public Double getMwst_prozent1() {
        return mwst_prozent1;
    }

    public void setMwst_prozent1(Double mwst_prozent1) {
        this.mwst_prozent1 = mwst_prozent1;
    }

    public int getMin_tn_anzahl1() {
        return min_tn_anzahl1;
    }

    public void setMin_tn_anzahl1(int min_tn_anzahl1) {
        this.min_tn_anzahl1 = min_tn_anzahl1;
    }

    public int getMax_tn_anzahl1() {
        return max_tn_anzahl1;
    }

    public void setMax_tn_anzahl1(int max_tn_anzahl1) {
        this.max_tn_anzahl1 = max_tn_anzahl1;
    }

    public int getAktuelle_tn_anzahl1() {
        return aktuelle_tn_anzahl1;
    }

    public void setAktuelle_tn_anzahl1(int aktuelle_tn_anzahl1) {
        this.aktuelle_tn_anzahl1 = aktuelle_tn_anzahl1;
    }

    public int getFreie_plaetze1() {
        return freie_plaetze1;
    }

    public void setFreie_plaetze1(int freie_plaetze1) {
        this.freie_plaetze1 = freie_plaetze1;
    }

    public String getStatus1() {
        return status1;
    }

    public void setStatus1(String status1) {
        this.status1 = status1;
    }

    public Double getGebuehr_netto1() {
        return gebuehr_netto1;
    }

    public void setGebuehr_netto1(Double gebuehr_netto1) {
        this.gebuehr_netto1 = gebuehr_netto1;
    }

    public Double getMwst_euro1() {
        return mwst_euro1;
    }

    public void setMwst_euro1(Double mwst_euro1) {
        this.mwst_euro1 = mwst_euro1;
    }

    public String getKurs_beschreibung() {
        return kurs_beschreibung;
    }

    public void setKurs_beschreibung(String kurs_beschreibung) {
        this.kurs_beschreibung = kurs_beschreibung;
    }
    //    public void setFreie_plaetze1(Integer freie_plaetze1) {
//        if (freie_plaetze1 != null) {
//            this.freie_plaetze1 = freie_plaetze1;
//        } else {
//            this.freie_plaetze1 = 0; // or any other default value you want to use
//        }
//    }
}
