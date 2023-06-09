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
import java.util.Iterator;
import java.util.Set;
import javax.validation.constraints.Min;

/**
 * Die Klasse Kurs definiert die Objekte 'Kurs', mit deren Attributen und Datentypen. (Einige Attribute sind Pflichtangaben, andere werden automatisch berechnet)
 * aus der Klasse wird eine Tabelle in der Datenbank erzeugt, sowie aus den Attributen entsprechende Spalten
 * Mittels Annotationen werden Verbindungen Springboot und der Datenbank hergestellt und auch spezifiziert, wie zb Beziehungen zwischen den Enteties
 */
@Entity
@Table(name = "kurs")
public class Kurs {

    /**
     * Id wird automatisch in Datenbank vergeben und nicht vom user
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="kursId")
    private Long kursId;

    /**
     * Gibt den Namen eines Kurses an, kann mehrfach vorkommen
     */
    @Column(name = "kursname", length = 100, nullable = false)
    private String kursname;

    /**
     * Gibt den Status eines Kurses an, zB geplant, wenn der Kurs noch nicht angefangen hat
     */
    @Column(name = "status", length = 100, nullable = false)
    private String status;


    /**
     * Gibt das Datum an, an dem ein Kurs startet,
     * hier wird das Datumsformat im englischen Format festgelegt
     */
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "start_datum", nullable = false)
    private LocalDate start_datum;

    /**
     * Anzahl_tage legt die gesamte Zahl an Veranstaltungstagen fest
     */
    @Column(name = "anzahl_tage", nullable = false)
    private int anzahl_tage;

    /**
     * Zyklus legt die Anzahl der Veranstaltungstage je Woche fest
     */
    @Column(name = "zyklus", nullable = false)
    private int zyklus;

    /**
     * Gibt das Datum an, an dem ein Kurs beendet ist,
     * wird automatisch berechnet
     * hier wird das Datumsformat im englischen Format festgelegt
     */
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "ende_datum")
    private LocalDate ende_datum;

    /**
     * Gibt die Anzahl an Teilnehmern an, die ein Kurs haben muss damit ein Kurs stattfindet
     */
    @Column(name = "min_tn_anzahl", nullable = false)
    private int min_tn_anzahl;

    /**
     *  Gibt die Anzahl an Teilnehmern an, die maximal an einem Kurs teilnehmen können
     */
    @Column(name = "max_tn_anzahl", nullable = false)
    private int max_tn_anzahl;

    /**
     * Gibt die Zahl an noch verfügbaren Plätzen für potentielle Teilnehmer an,
     * wird automatisch berechnet
     */
    @Column(name = "freie_plaetze")
    private int freie_plaetze = 0;

    /**
     * Gibt die aktuelle Anzahl an Teilnehmern eines Kurses an,
     * wird automatisch berechnet
     */
   @Column(name = "aktuelle_tn_anzahl")
    private int aktuelle_tn_anzahl;

    /**
     * Gibt die gesamte Gebuehr für eine Kursteilnahme an, inklusive Mehrwertsteuer
     */
    @Column(name = "gebuehr_brutto", nullable = false)
    private Double gebuehr_brutto;

    /**
     * Gibt die gesamte Gebuehr für eine Kursteilnahme an, exkusive MEhrwertsteuer
     * wird automatisch berechnet
     */
    @Column(name = "gebuehr_netto")
    private Double gebuehr_netto;

    /**
     * Gibt den Prozentsatz der Mehrwertsteuer einer Kursgebuehr an,
     * kann vom User festgelegt werden
     */
    @Column(name = "mwst_prozent", nullable = false)
    private Double mwst_prozent;

    /**
     * Gibt die Mehrwertsteuer einer Kursgebuehr in Euro an
     * wird automatisch berechnet
     */
    @Column(name = "mwst_euro", nullable = false)
    private Double mwst_euro;

    /**
     * Bietet Raum für ausfuehrliche Informationen ueber einen Kurs
     */
    @Column(name = "kurs_beschreibung", length = 600, nullable = false)
    private String kurs_beschreibung;

    /**
     * Die Interessenten an einem Kurs werden in einer Hash-Tabelle gespeichert, wodurch ein schneller Zugriff möglich ist.
     */
    @ManyToMany(mappedBy = "inKursinteressieren")
    @JsonIgnore
    private Set<Person> interessant = new HashSet<>();


    /**
     * Die Teilnehmer an einem Kurs werden in einer Hash-Tabelle gespeichert, wodurch ein schneller Zugriff möglich ist.
     */
    @ManyToMany(mappedBy = "inKursteilnehmen")
    @JsonIgnore
    private Set<Person> teilnehmer = new HashSet<>();



    /**
     * Der Konstruktor zum Kurs hat neun Parameter und alle sind Pflichtfelder
     * Die Attribute werden über die Validation gecheckt, andere Attribute werden anhand dieser Atribute berechnet
     *
     * @param start_datum1
     * @param anzahl_tage1
     * @param zyklus1
     * @param gebuehr_brutto1
     * @param mwst_prozent1
     * @param min_tn_anzahl1
     * @param max_tn_anzahl1
     * @param status1
     * @param kurs_beschreibung
     */
    public Kurs( LocalDate start_datum1, int anzahl_tage1, int zyklus1, Double gebuehr_brutto1, Double mwst_prozent1, int min_tn_anzahl1, int max_tn_anzahl1,  String status1, String kurs_beschreibung) {

        this.start_datum = start_datum1;
        this.anzahl_tage = anzahl_tage1;
        this.zyklus = zyklus1;
        this.gebuehr_brutto = gebuehr_brutto1;
        this.mwst_prozent = mwst_prozent1;
        this.min_tn_anzahl = min_tn_anzahl1;
        this.max_tn_anzahl = max_tn_anzahl1;
        //this.aktuelle_tn_anzahl = aktuelle_tn_anzahl1; //int aktuelle_tn_anzahl1,
        this.status = status1;
        this.kurs_beschreibung=kurs_beschreibung;
    }

    /**
     * Dieser Konstruktor hat keine Parameter wg hibernate
     */
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




    public void setKursId(Long kursId) {
        this.kursId = kursId;
    }

    public Set<Person> getInteressant() {
        return interessant;
    }

    public void setInteressant(Set<Person> interessant) {
        this.interessant = new HashSet<>();
    }

    public Set<Person> getTeilnehmer() {
        return teilnehmer;
    }

    public void setTeilnehmer(Set<Person> teilnehmer) {
        this.teilnehmer = teilnehmer;
    }

    public void hadTeilnehmer(Person person) {
        person.schonTeilnehmer(this);
    }

    public void hadInteressant(Person person) {
        person.schonInteressant(this);
    }

    /**
     * Methode add wird genutzt um eine Person hinzuzufügen, wird im KursController und im PersonenController eingesetzt,
     * @param person
     * @param personSet
     * @return
     */
    public Set<Person> add(Person person,Set<Person> personSet){
        for (Person p: personSet){
            if(p.getPersonId().equals(person.getPersonId())){
                return personSet;
            }
        }
        personSet.add(person);
        return personSet;
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
