package com.example.kursverwaltung.domain;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="personId")
    private Long personId;
    @Column(name = "anrede", length = 150, nullable = false)
    private int anrede;
    @Column(name = "titel", length = 150, nullable = true)
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

    @ManyToMany(cascade = {CascadeType.REFRESH, CascadeType.PERSIST}, fetch = FetchType.EAGER)
    @JoinTable(name = "person_kurs_interessant",
            joinColumns = {@JoinColumn(name = "personId")},
            inverseJoinColumns = {@JoinColumn(name = "kursId")})
    public Set<Kurs> inKursinteressieren = new HashSet<>();
    @ManyToMany(cascade = {CascadeType.REFRESH, CascadeType.PERSIST}, fetch = FetchType.EAGER)
    @JoinTable(name = "person_kurs_teilnehmer",
            joinColumns = {@JoinColumn(name = "personId")},
            inverseJoinColumns = {@JoinColumn(name = "kursId")})
    public Set<Kurs> inKursteilnehmen = new HashSet<>();

    public Person() {


    }

    public Person(Long personId,int anrede, String title, String vorname, String nachname, String strasse, String plz, String ort, String email) {
        this.personId=personId;
        this.anrede = anrede;
        this.titel = title;
        this.vorname = vorname;
        this.nachname = nachname;
        this.strasse = strasse;
        this.plz = plz;
        this.ort = ort;
        this.email = email;
    }

    public Long getPersonId() {
        return personId;
    }

    public Set<Kurs> getInKursteilnehmen() {
        return inKursteilnehmen;
    }

    public void setInKursteilnehmen(Set<Kurs> inKursteilnehmen) {
        this.inKursteilnehmen = inKursteilnehmen;
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

    public Set<Kurs> getInKursinteressieren() {
        return inKursinteressieren;
    }

    public void setInKursinteressieren(Set<Kurs> inKursinteressieren) {
        this.inKursinteressieren = inKursinteressieren;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    /*@Override
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
    */
    @Override
    public String toString() {
        return "Person{" +
                "personID=" + personId +
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

    public void schonTeilnehmer(Kurs kurs) {
        for (Kurs kursp : this.inKursteilnehmen) {
            if (kursp.getKursId().equals(kurs.getKursId())) {
                inKursteilnehmen.remove(kursp);
                kursp.getTeilhnehmer().remove(this);

            }
        }
    }

    public void schonInteressant(Kurs kurs) {
        for (Kurs kursp : this.inKursinteressieren) {
            if (kursp.getKursId().equals(kurs.getKursId())) {
                inKursinteressieren.remove(kursp);
                kursp.getInteressant().remove(this);
            }
        }
    }
}
