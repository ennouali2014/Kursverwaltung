package com.example.kursverwaltung.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "kurs")
public class Kurs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long kursId;
    @Column(name = "kursname", length = 100, nullable = false)
    private String kursname;
    @Column(name = "status", length = 100, nullable = false)
    private String status;
    @Column(name = "anzahl_tage", nullable = false)
    private int anzahl_tage;
    @Column(name = "zyklus", nullable = false)
    private int zyklus;
    @Temporal(TemporalType.DATE)
    @Column(name = "start_datum", nullable = false)
    private LocalDate start_datum;
    @Temporal(TemporalType.DATE)
    @Column(name = "ende_datum")
    private LocalDate ende_datum;
    @Column(name = "min_tn_anzahl", nullable = false)
    private int min_tn_anzahl;
    @Column(name = "max_tn_anzahl", nullable = false)
    private int max_tn_anzahl;
    @Column(name = "freie_plaetze")
    private int freie_plaetze=0;
    @Column(name = "aktuelle_tn_anzahl", nullable = false)
    // spaeter wird nicht nullable sondern in add person in kurs Kalkuliert
    private int aktuelle_tn_anzahl;
    @Column(name = "gebuehr_brutto", nullable = false)
    private Double gebuehr_brutto;
    @Column(name = "gebuehr_netto")
    private Double gebuehr_netto;
    @Column(name = "mwst_euro")
    private Double mwst_euro;
    @Column(name = "mwst_prozent", nullable = false)
    private Double mwst_prozent;
    @Column(name = "kurs_beschreibung", length = 400, nullable = false)
    private String kurs_beschreibung;

//    @DateTimeFormat
//    private Date convertedStartDate;
    @DateTimeFormat
    private Date convertedEndeDate;

    @ManyToMany(mappedBy = "inKursinteressieren")
    @JsonIgnore
    private Set<Person> interessant = new HashSet<>();

    @ManyToMany(mappedBy = "inKursteilnehmen")
    @JsonIgnore
    private Set<Person> teilnehmer = new HashSet<>();


    public Kurs() {
    }

    public Kurs(String kursname, String status, int anzahl_tage, int zyklus, LocalDate start_datum, int min_tn_anzahl, int max_tn_anzahl, Double gebuehr_brutto, Double mwst_prozent, String kurs_beschreibung) {

        this.kursname = kursname;
        this.status = status;
        this.anzahl_tage = anzahl_tage;
        this.zyklus = zyklus;
        this.start_datum = start_datum;
        // this.ende_datum = ende_datum;
        this.min_tn_anzahl = min_tn_anzahl;
        this.max_tn_anzahl = max_tn_anzahl;
        this.gebuehr_brutto = gebuehr_brutto;
        this.mwst_prozent = mwst_prozent;
        this.kurs_beschreibung = kurs_beschreibung;
       // setStart_datum();
        getEnde_datum();
        getFreie_plaetze();
        getGebuehr_netto();
        getMwst_euro();
    }

    public Long getKursId() {
        return kursId;
    }

    public String getKursname() {
        return kursname;
    }

    public void setKursname(String kursname) {
        this.kursname = kursname;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getAnzahl_tage() {
        return anzahl_tage;
    }

    public void setAnzahl_tage(int anzahlTage) {
        this.anzahl_tage = anzahlTage;
    }

    public int getZyklus() {
        return zyklus;
    }

    public void setZyklus(int zyklus) {
        this.zyklus = zyklus;
//        if(this.start_datum!=null){
//
//        }
    }

    public LocalDate getStart_datum() {
        return start_datum;
    }

    public void setStart_datum(LocalDate startDatum) {
        this.start_datum=startDatum;

//        String pattern = "dd-MM-yyyy";
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
//
//        try {
//            if (startDatum == null || startDatum.isEmpty()) {
//                this.convertedStartDate = new Date();
//            } else {
//
//                this.convertedStartDate = simpleDateFormat.parse(startDatum);
//            }
//        } catch (ParseException e) {
//            throw new RuntimeException(e);
//        }
    }

//    public Date getEnde_datum() {
//        return convertedEndeDate;
//
//    }

    /* public void setEnde_datum(String endeDatum) {
         String pattern = "dd-MM-yyyy";
         SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
         try {
             this.convertedEndeDate = simpleDateFormat.parse(endeDatum);
         } catch (ParseException e) {
             throw new RuntimeException(e);
         }

     }*/
//    public void setEnde_datum() {
//        Calendar cal = Calendar.getInstance();
//        cal.setTime(start_datum);
//        cal.add(Calendar.DAY_OF_MONTH, anzahl_tage);
//        this.ende_datum = cal.getTime();
//       // Math.round((float) anzahl_tage / zyklus)) * 7 * 86400000L;
//    }


     public LocalDate getEnde_datum() {
         return ende_datum;
     }

     public void setEnde_datum(LocalDate ende_datum) {
         this.ende_datum = ende_datum;
     }

    public int getMin_tn_anzahl() {
        return min_tn_anzahl;
    }

    public void setMin_tn_anzahl(int minTnZahl) {
        this.min_tn_anzahl = minTnZahl;
    }

    public int getMax_tn_anzahl() {
        return max_tn_anzahl;
    }

    public void setMax_tn_anzahl(int maxTnZahl) {
        this.max_tn_anzahl = maxTnZahl;
    }

    public int getFreie_plaetze() {
        return freie_plaetze;
    }

    public void setFreie_plaetze() {
        this.freie_plaetze = max_tn_anzahl - aktuelle_tn_anzahl;
    }

    public int getAktuelle_tn_anzahl() {
        return aktuelle_tn_anzahl;
    }

    public void setAktuelle_tn_anzahl(int aktuelle_tn_anzahl) {
        this.aktuelle_tn_anzahl = aktuelle_tn_anzahl;
    }

    public Double getGebuehr_brutto() {
        return gebuehr_brutto;
    }

    public void setGebuehr_brutto(Double gebuehrBrutto) {
        this.gebuehr_brutto = gebuehrBrutto;
    }

    public Double getGebuehr_netto() {
        return gebuehr_netto;
    }

    public void setGebuehr_netto() {
        this.gebuehr_netto = Math.round((gebuehr_brutto / (100 + mwst_prozent) * 100) * 100.0) / 100.0;
    }

    public Double getMwst_euro() {
        return mwst_euro;
    }

    public void setMwst_euro() {

        this.mwst_euro = Math.round((gebuehr_brutto / (100 + mwst_prozent) * mwst_prozent) * 100.0) / 100.0;
    }

    public Double getMwst_prozent() {
        return mwst_prozent;
    }

    public void setMwst_prozent(Double mwstProzent) {
        this.mwst_prozent = mwstProzent;
    }

    public String getKurs_beschreibung() {
        return kurs_beschreibung;
    }

    public void setKurs_beschreibung(String kursBeschreibung) {
        this.kurs_beschreibung = kursBeschreibung;
    }

   /* public Set<Person> getPersonen() {
        return personen;
    }

    public void setPersonen(Set<Person> personen) {
        this.personen = personen;
    }*/

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
}
