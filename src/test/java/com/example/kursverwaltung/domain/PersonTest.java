package com.example.kursverwaltung.domain;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

        @Test
        public void testEquals() {

            Person person1 = new Person();
            person1.setVorname("Max");
            person1.setNachname("Mustermann");
            person1.setEmail("max.mustermann@example.com");

            Person person2 = new Person();
            person2.setVorname("Max");
            person2.setNachname("Mustermann");
            person2.setEmail("max.mustermann@example.com");

            assertEquals(person1, person2);
            assertEquals(person2, person1);

            Person person3 = new Person();
            person3.setVorname("Max");
            person3.setNachname("Muster");
            person3.setEmail("max.mustermann@example.com");

            Person person4 = new Person();
            person4.setVorname("Anna");
            person4.setNachname("Musterfrau");
            person4.setEmail("anna.musterfrau@example.com");

            assertNotEquals(person1, person3);
            assertNotEquals(person1, person4);
            assertNotEquals(person3, person4);
        }

    @Test
    public void testAdd() {
        // Create a person and two kurs objects
        Person person = new Person();
        Kurs kurs1 = new Kurs();
        kurs1.setKursId(1L);
        Kurs kurs2 = new Kurs();
        kurs2.setKursId(2L);

        // Add the first kurs to the person
        Set<Kurs> kursSet1 = new HashSet<>();
        kursSet1 = person.add(kurs1, kursSet1);

        // Verify that the kurs was added
        assertEquals(1, kursSet1.size());
        assertTrue(kursSet1.contains(kurs1));

        // Add the same kurs again
        Set<Kurs> kursSet2 = new HashSet<>(kursSet1);
        kursSet2 = person.add(kurs1, kursSet2);

        // Verify that the kurs was not added again
        assertEquals(1, kursSet2.size());
        assertTrue(kursSet2.contains(kurs1));

        // Add a different kurs
        Set<Kurs> kursSet3 = new HashSet<>(kursSet1);
        kursSet3 = person.add(kurs2, kursSet3);

        // Verify that the different kurs was added
        assertEquals(2, kursSet3.size());
        assertTrue(kursSet3.contains(kurs1));
        assertTrue(kursSet3.contains(kurs2));
    }


    @Test
    public void testSchonInteressant() {
        // Create a person and two kurs objects
        Person person = new Person();
        Kurs kurs1 = new Kurs();
        kurs1.setKursId(1L);
        Kurs kurs2 = new Kurs();
        kurs2.setKursId(2L);

        // Add both kurs objects to the person's list of interests
        person.getInKursinteressieren().add(kurs1);
        person.getInKursinteressieren().add(kurs2);

        // Verify that both kurs objects are in the person's list of interests
        assertEquals(2, person.getInKursinteressieren().size());
        assertTrue(person.getInKursinteressieren().contains(kurs1));
        assertTrue(person.getInKursinteressieren().contains(kurs2));

        // Call the schonInteressant method with one of the kurs objects
        person.schonInteressant(kurs1);

        // Verify that the specified kurs object was removed from the person's list of interests
        assertEquals(1, person.getInKursinteressieren().size());
        assertFalse(person.getInKursinteressieren().contains(kurs1));
        assertTrue(person.getInKursinteressieren().contains(kurs2));

        // Überprüfen, ob die Person aus der Liste der interessierten
        // Personen des angegebenen Kursobjekts entfernt wurde
        assertEquals(0, kurs1.getInteressant().size());
        assertFalse(kurs2.getInteressant().contains(person));
    }

    @Test
    void testHashCode() {
    }
}