package com.example.kursverwaltung.controller;


import com.example.kursverwaltung.domain.Kurs;
import com.example.kursverwaltung.repository.KursRepository;
import com.example.kursverwaltung.repository.PersonRepository;
import com.example.kursverwaltung.service.KursService;
import org.junit.Before;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.request.RequestPostProcessor;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;
import com.example.kursverwaltung.domain.Person;
import com.example.kursverwaltung.service.PersonService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class PersonControllerTest {

    @InjectMocks
    PersonController controller;

    @Mock
    PersonService service;
    @Mock
    KursService kursService;

    @Mock
    private PersonService personService;

    @Mock
    private PersonRepository personRepository;

    @Mock
    private KursRepository kursRepository;
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeEach
    public void setup() {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }



    @Test
    public void testViewHomePage() {
        Model model = mock(Model.class);
        List<Person> listPerson = new ArrayList<>();
        when(service.listAll()).thenReturn(listPerson);

        String viewHomePage = controller.viewHomePage(model, null);

        Assertions.assertEquals("personen", viewHomePage);
        verify(model, times(1)).addAttribute("listPerson", listPerson);
    }

    @Test
    public void testAdd() throws Exception {
        // Erstellen der Objekte request und model
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setMethod("GET");
        Model model = new ConcurrentModel();

        // Aufrufen der Methode add
        String viewName = controller.add(new Person(), model);

        assertEquals("newperson", viewName);
        assertTrue(model.containsAttribute("person"));
        assertNotNull(model.getAttribute("person"));

        // Überprüfen, dass der Dienst nicht aufgerufen wurde
        verifyNoInteractions(service);
    }


    @Test
    public void testSavePerson() throws Exception {
        Person person = new Person();
        person.setVorname("John");
        person.setNachname("Doe");
        person.setEmail("john.doe@example.com");

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        mockMvc.perform(post("/person/saveperson")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("vorname", person.getVorname())
                        .param("nachname", person.getNachname())
                        .param("email", person.getEmail()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/person/personen"));

        verify(service, times(1)).save(person);
    }


    @Test
    public void testSavePersonWithDuplicate() throws Exception {
        Person person = new Person();
        person.setVorname("John");
        person.setNachname("Doe");
        person.setEmail("john.doe@example.com");

        List<Person> listPerson = new ArrayList<>();
        listPerson.add(person);

        when(service.listAll()).thenReturn(listPerson);
        doNothing().when(service).save(person);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        mockMvc.perform(post("/person/saveperson")
                        .flashAttr("person", person))

                .andExpect(status().isOk())
                .andExpect(view().name("newperson"))
                .andExpect(model().attributeExists("person", "error", "isSame"));

        verify(service, times(1)).listAll();
        verify(service, times(0)).save(person);
    }


    @Test
    public void testShowEditPersonpage() {
        // Setup
        int personId = 1;
        Person mockPerson = new Person();
        mockPerson.setPersonId((long) personId);
        mockPerson.setVorname("Max");
        mockPerson.setNachname("Mustermann");
        mockPerson.setEmail("max.mustermann@example.com");

        when(service.getPersonId(personId)).thenReturn(mockPerson);

        ModelAndView mav = controller.showEditPersonpage(personId);

        assertEquals("newperson", mav.getViewName());
        Person actualPerson = (Person) mav.getModel().get("person");
        assertEquals(mockPerson, actualPerson);
        verify(service, times(1)).getPersonId(personId);
    }

    @Test
    public void testDeletePerson() throws Exception {
        int personId = 1;
        doNothing().when(service).delete(personId);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        mockMvc.perform(get("/person/deleteperson/{personId}", personId))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/person/personen"));

        verify(service, times(1)).delete(personId);
    }



  /*  @Test
    public void testAssignKursToPerson() throws Exception {
        Long personId = 1L;
        Person person = new Person();
        person.setPersonId(personId);

        Kurs kurs = new Kurs();
        kurs.setKursId(1L);
        kurs.setMax_tn_anzahl(5);
        kurs.setTeilnehmer(new HashSet<>());
        kurs.setInteressant(new HashSet<>());
        kurs.setFreie_plaetze(5);
        List<Kurs> kurse = Arrays.asList(kurs);
        when(service.getPersonId(person.getPersonId())).thenReturn(person);
        when(service.getKurs(kurs.getKursId())).thenReturn(kurs);
        when(kursRepository.save(kurs)).thenReturn(kurs);
        when(personRepository.save(person)).thenReturn(person);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        // Test des Hinzufügens der Person zum Kurs als Teilnehmer
        mockMvc.perform(post("/person/addKursToPerson/" + person.getPersonId())
                        .param("kursId", kurs.getKursId().toString())
                        .param("choix", "Teilnehmer"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/person/get/" + person.getPersonId() + "?success=true"));

        assertEquals(1, person.getInKursteilnehmen().size());
        assertEquals(1, kurs.getTeilnehmer().size());
        assertEquals(4, kurs.getFreie_plaetze());

        // Testen des Hinzufügens der Person zum Kurs als Interessent
        mockMvc.perform(post("/person/addKursToPerson/" + person.getPersonId())
                        .param("kursId", kurs.getKursId().toString())
                        .param("choix", "Interessent"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/person/get/" + person.getPersonId() + "?success=true"));

        assertEquals(1, person.getInKursinteressieren().size());
        assertEquals(1, kurs.getInteressant().size());
    }*/

    @Test
    public void testGetPersonId() throws Exception {
        // Setup
        Long personId = 1L;
        Person person = new Person();
        person.setPersonId(personId);
        Kurs kurs = new Kurs();
        kurs.setKursId(1L);
        kurs.setMax_tn_anzahl(5);
        kurs.setTeilnehmer(new HashSet<>());
        kurs.setInteressant(new HashSet<>());
        kurs.setFreie_plaetze(5);

        List<Kurs> kurse = Arrays.asList(kurs);

        when(service.getPersonId(personId)).thenReturn(person);
        when(service.getAllkurs()).thenReturn(kurse);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        // Exercise
        mockMvc.perform(get("/person/get/" + personId))
                .andExpect(status().isOk())
                .andExpect(view().name("addKursToPerson"))
                .andExpect(model().attribute("person", person))
                .andExpect(model().attribute("kurse", kurse))
                .andExpect(model().attribute("choix", new String[]{"Teilnehmer", "Interessent"}));

        // Verify
        verify(service, times(1)).getPersonId(personId);
        verify(service, times(1)).getAllkurs();
    }


    @Test
    public void testShowPerson() throws Exception {
        // Setup
        Long personId = 1L;
        Person person = new Person();
        person.setPersonId(personId);

        when(service.getPersonId(personId)).thenReturn(person);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        // Exercise
        mockMvc.perform(get("/person/showPerson/" + personId))
                .andExpect(status().isOk())
                .andExpect(view().name("showPerson"))
                .andExpect(model().attribute("person", person));

        // Verify
        verify(service, times(1)).getPersonId(personId);
    }

}