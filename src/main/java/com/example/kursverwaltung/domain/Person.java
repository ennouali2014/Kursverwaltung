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
import java.util.Iterator;
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
    @Size(min=2)
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


    public Set<Kurs> getInKursinteressieren() {
        return inKursinteressieren;
    }

    public void setInKursinteressieren(Set<Kurs> inKursinteressieren) {
        this.inKursinteressieren = inKursinteressieren;
    }

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
        Iterator<Kurs> iterator = this.inKursteilnehmen.iterator();
        while (iterator.hasNext()) {
            Kurs kursp = iterator.next();
            if (kursp.getKursId().equals(kurs.getKursId())) {
                iterator.remove();
                kurs.getTeilnehmer().remove(this);
                if(kurs.getFreie_plaetze()<kurs.getMax_tn_anzahl()) {
                    kurs.setFreie_plaetze(kurs.getFreie_plaetze() + 1);
                }
            }
        }
    }

    public void schonInteressant(Kurs kurs) {
        Iterator<Kurs> iterator = this.inKursinteressieren.iterator();
        while (iterator.hasNext()) {
            Kurs kursp = iterator.next();
            if (kursp.getKursId().equals(kurs.getKursId())) {
                iterator.remove();
                kurs.getInteressant().remove(this);
            }
        }
    }

    public Set<Kurs> add(Kurs kurs,Set<Kurs> kursSet){
        for (Kurs k: kursSet){
            if(kurs.getKursId().equals(k.getKursId())){
                return kursSet;
            }
        }
        kursSet.add(kurs);
        return kursSet;
    }
}
