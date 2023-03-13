package com.example.kursverwaltung.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;
import org.hibernate.validator.constraints.br.CNPJ;
import org.springframework.validation.annotation.Validated;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "person")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "personId")
    private Long personId;
    @Column(name = "anrede", length = 150, nullable = false)
    private int anrede;
    @Column(name = "titel", length = 150, nullable = true)
    private String titel;

    private String vorname;
    @NotBlank
    private String nachname;
    @NotBlank
    @Email
    @Column(name = "email", unique = true)
    private String email;
    @NotBlank
    private String strasse;
    @NotBlank
    private String plz;
    @NotBlank
    private String ort;

    @ManyToMany(cascade = {CascadeType.REFRESH, CascadeType.PERSIST}, fetch = FetchType.EAGER)
    @JoinTable(name = "person_kurs_interessant",
            joinColumns = {@JoinColumn(name = "personId")},
            inverseJoinColumns = {@JoinColumn(name = "kursId")})
    private Set<Kurs> inKursinteressieren = new HashSet<>();
    @ManyToMany(cascade = {CascadeType.REFRESH, CascadeType.PERSIST}, fetch = FetchType.EAGER)
    @JoinTable(name = "person_kurs_teilnehmer",
            joinColumns = {@JoinColumn(name = "personId")},
            inverseJoinColumns = {@JoinColumn(name = "kursId")})
    private Set<Kurs> inKursteilnehmen = new HashSet<>();

/*    public Person() {
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
    }*/

/*    public Set<Kurs> getInKursteilnehmen() {
        return inKursteilnehmen;
    }

    public void setInKursteilnehmen(Set<Kurs> inKursteilnehmen) {
        this.inKursteilnehmen = inKursteilnehmen;
    }*/

/*
    public Long getPersonId() {
        return personId;
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
        if (checkIsEmpty(vorname)) {
            this.vorname = vorname;
        } else {
            System.out.println("Vorname ist leer oder zu kurz!");
        }
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        if (checkIsEmpty(nachname)) {
            this.nachname = nachname;
        } else {
            System.out.println("Nachname ist leer oder zu kurz!");
        }
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
        if (checkValidEmail(email)) {
            this.email = email;
        } else {
            System.out.println("Email ist falsch!");
        }
    }*/

    public Set<Kurs> getInKursinteressieren() {
        return inKursinteressieren;
    }

    public void setInKursinteressieren(Set<Kurs> inKursinteressieren) {
        this.inKursinteressieren = inKursinteressieren;
    }
/*
    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public static boolean checkIsEmpty(String wert) {
        return wert != null && wert.length() >= 2;
    }

    public static boolean checkValidEmail(String email) {
        String pattern = ("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$");
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(pattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }*/

    public boolean isIdentitySame() {
        return vorname.equals(nachname) && nachname.equals(email);
    }
    @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Person person = (Person) o;
            return Objects.equals(vorname, person.vorname) && Objects.equals(nachname, person.nachname) && Objects.equals(email, person.email);
        }

        @Override
        public int hashCode() {
            return Objects.hash(vorname, nachname, strasse, plz, ort, email);
        }

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
                kursp.getTeilnehmer().remove(this);

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

    public void setKurse(Set<Kurs> kurslist) {
    }
}
