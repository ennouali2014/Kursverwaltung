package com.example.kursverwaltung.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    @Column(name = "anzahlTage", nullable = false)
    private String anzahlTage;
    @Column(name = "zyklus", nullable = false)
    private int zyklus;
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date startDatum;
    @Temporal(TemporalType.DATE)
    private Date endeDatum;
    @Column(name = "minTnZahl", nullable = false)
    private int minTnZahl;
    @Column(name = "maxTnZahl", nullable = false)
    private int maxTnZahl;
    @Column(name = "freiePlaetze")
    private int freiePlaetze;
    @Column(name = "aktuelleTnZahl")
    private int aktuelleTnZahl;
    @Column(name = "gebuehrBrutto", nullable = false)
    private Double gebuehrBrutto;
    @Column(name = "gebuehrNetto")
    private Double gebuehrNetto;
    @Column(name = "mwstEuro")
    private Double mwstEuro;
    @Column(name = "mwstProzent", nullable = false)
    private Double mwstProzent;
    @Column(name = "kursBeschreibung", nullable = false)
    private String kursBeschreibung;


    @DateTimeFormat
    private Date convertedStartDate;
    @DateTimeFormat
    private Date convertedEndeDate;

    @ManyToMany(mappedBy = "inKursinteressieren")
    @JsonIgnore
    private Set<Person> interessant = new HashSet<>();

    @ManyToMany(mappedBy = "inKursteilnehmen")
    @JsonIgnore
    private Set<Person> teilhnehmer = new HashSet<>();

    public Kurs() {
    }

    public Kurs(String kursname, String status, String anzahlTage, int zyklus, Date startDatum, Date endeDatum, int minTnZahl, int maxTnZahl, Double gebuehrBrutto, Double mwstProzent, String kursBeschreibung) {

        this.kursname = kursname;
        this.status = status;
        this.anzahlTage = anzahlTage;
        this.zyklus = zyklus;
        this.startDatum = startDatum;
        this.endeDatum = endeDatum;
        this.minTnZahl = minTnZahl;
        this.maxTnZahl = maxTnZahl;
        this.gebuehrBrutto = gebuehrBrutto;
        this.mwstProzent = mwstProzent;
        this.kursBeschreibung = kursBeschreibung;
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

    public String getAnzahlTage() {
        return anzahlTage;
    }

    public void setAnzahlTage(String anzahlTage) {
        this.anzahlTage = anzahlTage;
    }

    public int getZyklus() {
        return zyklus;
    }

    public void setZyklus(int zyklus) {
        this.zyklus = zyklus;
    }

    /*public Date getStartDatum() {
        return startDatum;
    }*/

    public void setStartDatum(Date startDatum) {
        this.startDatum = startDatum;
    }
    public Date getStartDatum() {
        return convertedStartDate;
    }

    public void setStartDatum(String startDatum) {
        String pattern = "dd-MM-yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        try {
            this.convertedStartDate = simpleDateFormat.parse(startDatum);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

    }

    /*public Date getEndeDatum() {
        return convertedEndeDate;
    }*/

    public void setEndeDatum(String endeDatum) {
        String pattern = "dd-MM-yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        try {
            this.convertedEndeDate = simpleDateFormat.parse(endeDatum);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

    }
    public Date getEndeDatum() {
        return endeDatum;
    }

    public void setEndeDatum(Date endeDatum) {
        this.endeDatum = endeDatum;
    }

    public int getMinTnZahl() {
        return minTnZahl;
    }

    public void setMinTnZahl(int minTnZahl) {
        this.minTnZahl = minTnZahl;
    }

    public int getMaxTnZahl() {
        return maxTnZahl;
    }

    public void setMaxTnZahl(int maxTnZahl) {
        this.maxTnZahl = maxTnZahl;
    }

    public int getFreiePlaetze() {
        return freiePlaetze;
    }

    public void setFreiePlaetze(int freiePlaetze) {
        this.freiePlaetze = freiePlaetze;
    }

    public int getAktuelleTnZahl() {
        return aktuelleTnZahl;
    }

    public void setAktuelleTnZahl(int aktuelleTnZahl) {
        this.aktuelleTnZahl = aktuelleTnZahl;
    }

    public Double getGebuehrBrutto() {
        return gebuehrBrutto;
    }

    public void setGebuehrBrutto(Double gebuehrBrutto) {
        this.gebuehrBrutto = gebuehrBrutto;
    }

    public Double getGebuehrNetto() {
        return gebuehrNetto;
    }

    public void setGebuehrNetto(Double gebuehrNetto) {
        this.gebuehrNetto = gebuehrNetto;
    }

    public Double getMwstEuro() {
        return mwstEuro;
    }

    public void setMwstEuro(Double mwstEuro) {
        this.mwstEuro = mwstEuro;
    }

    public Double getMwstProzent() {
        return mwstProzent;
    }

    public void setMwstProzent(Double mwstProzent) {
        this.mwstProzent = mwstProzent;
    }

    public String getKursBeschreibung() {
        return kursBeschreibung;
    }

    public void setKursBeschreibung(String kursBeschreibung) {
        this.kursBeschreibung = kursBeschreibung;
    }

    public void setKursId(Long kursId) {
        this.kursId = kursId;
    }

    public Set<Person> getInteressant() {
        return interessant;
    }

    public void setInteressant(Set<Person> interessant) {
        this.interessant = interessant;
    }

    public Set<Person> getTeilhnehmer() {
        return teilhnehmer;
    }

    public void setTeilhnehmer(Set<Person> teilhnehmer) {
        this.teilhnehmer = teilhnehmer;
    }
}
