package com.example.kursverwaltung.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long person_id;
    @Column(name = "anrede", length = 150, nullable = false)
    private int anrede;
    @Column(name = "titel",length = 150, nullable = true)
    private String titel;
    @Column(name = "vorname", length = 150, nullable = false)
    private String vorname;
    @Column(name = "nachname", length = 150, nullable = false)
    private String nachname;
    @Column(name = "strasse", length = 150, nullable = false)
    private String strasse;
    @Column(name = "plz", length = 150, nullable = false)
    private String plz;
    @Column(name = "ort", length = 150, nullable = false)
    private String ort;
    @Column(name = "email", length = 150, nullable = false)
    private String email;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "person_kurs",
            joinColumns = {@JoinColumn(name = "person_id")},
            inverseJoinColumns = {@JoinColumn(name = "kurs_id")})
    public Set<Kurs> kurse = new HashSet<>();
    public Person() {

    }

    public Person(int anrede, String title, String vorname, String nachname, String strasse, String plz, String ort, String email) {
        this.anrede = anrede;
        this.titel = title;
        this.vorname = vorname;
        this.nachname = nachname;
        this.strasse = strasse;
        this.plz = plz;
        this.ort = ort;
        this.email = email;
    }

    public Long getPerson_id() {
        return person_id;
    }


    public int getAnrede() {
        return anrede;
    }

    public void setAnrede(int anrede) {
        this.anrede = anrede;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public String getStrasse() {
        return strasse;
    }

    public void setStrasse(String strasse) {
        this.strasse = strasse;
    }

    public String getPlz() {
        return plz;
    }

    public void setPlz(String plz) {
        this.plz = plz;
    }

    public String getOrt() {
        return ort;
    }

    public void setOrt(String ort) {
        this.ort = ort;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Kurs> getKurse() {
        return kurse;
    }

    public void setKurse(Set<Kurs> kurse) {
        this.kurse = kurse;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(vorname, person.vorname) && Objects.equals(nachname, person.nachname) && Objects.equals(strasse, person.strasse) && Objects.equals(plz, person.plz) && Objects.equals(ort, person.ort) && Objects.equals(email, person.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vorname, nachname, strasse, plz, ort, email);
    }

    @Override
    public String toString() {
        return "Person{" +
                "anrede=" + anrede +
                ", titel='" + titel + '\'' +
                ", vorname='" + vorname + '\'' +
                ", nachname='" + nachname + '\'' +
                ", strasse='" + strasse + '\'' +
                ", plz='" + plz + '\'' +
                ", ort='" + ort + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
