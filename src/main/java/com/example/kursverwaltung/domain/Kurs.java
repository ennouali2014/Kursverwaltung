package com.example.kursverwaltung.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "kurs")
public class Kurs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="kursId")
    private Long kursId;

    @Column(name = "kursname", length = 100, nullable = false)
    private String kursname;
    @Column(name = "status", length = 100, nullable = false)
    private String status;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "start_datum", nullable = false)
    private LocalDate start_datum;

    @Column(name = "anzahl_tage", nullable = false)
    private int anzahl_tage;
    @Column(name = "zyklus", nullable = false)
    private int zyklus;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "ende_datum")
    private LocalDate ende_datum;
    @Column(name = "min_tn_anzahl", nullable = false)
    private int min_tn_anzahl;
    @Column(name = "max_tn_anzahl", nullable = false)
    private int max_tn_anzahl;
    @Column(name = "freie_plaetze")
    private int freie_plaetze = 0;
    @Column(name = "aktuelle_tn_anzahl", nullable = false)
    // spaeter wird nicht nullable sondern in add person in kurs Kalkuliert
    private int aktuelle_tn_anzahl;

    @Column(name = "gebuehr_brutto", nullable = false)
    private Double gebuehr_brutto;

    @Column(name = "gebuehr_netto")
    private Double gebuehr_netto;

    @Column(name = "mwst_prozent", nullable = false)
    private Double mwst_prozent;

    @Column(name = "mwst_euro", nullable = false)
    private Double mwst_euro;

    @Column(name = "kurs_beschreibung", length = 600, nullable = false)
    private String kurs_beschreibung;

    @ManyToMany(mappedBy = "inKursinteressieren")
    @JsonIgnore
    private Set<Person> interessant = new HashSet<>();

    @ManyToMany(mappedBy = "inKursteilnehmen")
    @JsonIgnore
    private Set<Person> teilnehmer = new HashSet<>();

    public Kurs( LocalDate start_datum1, int anzahl_tage1, int zyklus1, Double gebuehr_brutto1, Double mwst_prozent1, int min_tn_anzahl1, int max_tn_anzahl1, int aktuelle_tn_anzahl1, String status1, String kurs_beschreibung) {

        this.start_datum = start_datum1;
        this.anzahl_tage = anzahl_tage1;
        this.zyklus = zyklus1;
        this.gebuehr_brutto = gebuehr_brutto1;
        this.mwst_prozent = mwst_prozent1;
        this.min_tn_anzahl = min_tn_anzahl1;
        this.max_tn_anzahl = max_tn_anzahl1;
        this.aktuelle_tn_anzahl = aktuelle_tn_anzahl1;
        this.status = status1;
        this.kurs_beschreibung=kurs_beschreibung;
    }

    public Kurs() {
    }

    public Long getKursId() {
        return kursId;
    }

    public String getKursname() {
        return kursname;
    }

    public void setKursname(String kursname1) {
        this.kursname = kursname1;
    }


    public LocalDate getStart_datum() {
        return start_datum;
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


    public void setStart_datum(LocalDate start_datum1) {
        this.start_datum = start_datum1;
    }

    public int getAnzahl_tage() {
        return anzahl_tage;
    }

    public void setAnzahl_tage(int anzahl_tage1) {
        this.anzahl_tage = anzahl_tage1;
    }

    public int getZyklus() {
        return zyklus;
    }

    public void setZyklus(int zyklus1) {
        this.zyklus = zyklus1;
    }

    public LocalDate getEnde_datum() {
        return ende_datum;
    }

    public void setEnde_datum(LocalDate ende_datum1) {
        this.ende_datum = ende_datum1;
    }

    public Double getGebuehr_brutto() {
        return gebuehr_brutto;
    }

    public void setGebuehr_brutto(Double gebuehr_brutto1) {
        this.gebuehr_brutto = gebuehr_brutto1;
    }

    public Double getMwst_prozent() {
        return mwst_prozent;
    }

    public void setMwst_prozent(Double mwst_prozent1) {
        this.mwst_prozent = mwst_prozent1;
    }

    public int getMin_tn_anzahl() {
        return min_tn_anzahl;
    }

    public void setMin_tn_anzahl(int min_tn_anzahl1) {
        this.min_tn_anzahl = min_tn_anzahl1;
    }

    public int getMax_tn_anzahl() {
        return max_tn_anzahl;
    }

    public void setMax_tn_anzahl(int max_tn_anzahl1) {
        this.max_tn_anzahl = max_tn_anzahl1;
    }

    public int getAktuelle_tn_anzahl() {
        return aktuelle_tn_anzahl;
    }

    public void setAktuelle_tn_anzahl(int aktuelle_tn_anzahl1) {
        this.aktuelle_tn_anzahl = aktuelle_tn_anzahl1;
    }

    public int getFreie_plaetze() {
        return freie_plaetze;
    }

    public void setFreie_plaetze(int freie_plaetze1) {
        this.freie_plaetze = freie_plaetze1;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status1) {
        this.status = status1;
    }

    public Double getGebuehr_netto() {
        return gebuehr_netto;
    }

    public void setGebuehr_netto(Double gebuehr_netto1) {
        this.gebuehr_netto = gebuehr_netto1;
    }

    public Double getMwst_euro() {
        return mwst_euro;
    }

    public void setMwst_euro(Double mwst_euro1) {
        this.mwst_euro = mwst_euro1;
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

    public void setKursId(Long kursId) {
        this.kursId = kursId;
    }

    public Set<Person> getInteressant() {
        return interessant;
    }

    public void setInteressant(Set<Person> interessant) {
        this.interessant = interessant;
    }

    public Set<Person> getTeilnehmer() {
        return teilnehmer;
    }

    public void setTeilnehmer(Set<Person> teilnehmer) {
        this.teilnehmer = teilnehmer;
    }
    public String convertDateToString(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return date.format(formatter);
        //DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        //return dateFormat.format(date);
    }
    @Override
    public String toString() {
        return "Kurs{" +
                "kursId=" + kursId +
                ", kursname='" + kursname + '\'' +
                ", status='" + status + '\'' +
                ", start_datum=" + start_datum +
                ", anzahl_tage=" + anzahl_tage +
                ", zyklus=" + zyklus +
                ", ende_datum=" + ende_datum +
                ", min_tn_anzahl=" + min_tn_anzahl +
                ", max_tn_anzahl=" + max_tn_anzahl +
                ", freie_plaetze=" + freie_plaetze +
                ", aktuelle_tn_anzahl=" + aktuelle_tn_anzahl +
                ", gebuehr_brutto=" + gebuehr_brutto +
                ", gebuehr_netto=" + gebuehr_netto +
                ", mwst_prozent=" + mwst_prozent +
                ", mwst_euro=" + mwst_euro +
                ", kurs_beschreibung='" + kurs_beschreibung + '\'' +
                '}';
    }
}
