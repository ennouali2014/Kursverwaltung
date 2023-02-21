package com.example.kursverwaltung.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
//@Table(name = "kurs")
public class Kurs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long kurs_id;
    @Column(name = "kursname", length = 100, nullable = false)
    private String kursname;

    public Long getKurs_id() {
        return kurs_id;
    }

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

    @ManyToMany(mappedBy = "kurse")
    @JsonIgnore
    private Set<Person> personen = new HashSet<>();

    public Kurs() {
    }

    public Kurs(Long id, String kursname, String status, String anzahlTage, int zyklus, Date startDatum, int minTnZahl, int maxTnZahl, Double gebuehrBrutto, Double mwstProzent, String kursBeschreibung) {
        this.kurs_id = id;
        this.kursname = kursname;
        this.status = status;
        this.anzahlTage = anzahlTage;
        this.zyklus = zyklus;
        this.startDatum = startDatum;
        this.minTnZahl = minTnZahl;
        this.maxTnZahl = maxTnZahl;
        this.gebuehrBrutto = gebuehrBrutto;
        this.mwstProzent = mwstProzent;
        this.kursBeschreibung = kursBeschreibung;
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

    public Date getStartDatum() {
        return startDatum;
    }

    public void setStartDatum(Date startDatum) {
        this.startDatum = startDatum;
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

    public Set<Person> getPersonen() {
        return personen;
    }

    public void setPersonen(Set<Person> personen) {
        this.personen = personen;
    }
}
