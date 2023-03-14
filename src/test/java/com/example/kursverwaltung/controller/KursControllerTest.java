package com.example.kursverwaltung.controller;



import com.example.kursverwaltung.domain.Kurs;
import com.example.kursverwaltung.repository.KursRepository;
import com.example.kursverwaltung.repository.PersonRepository;
import com.example.kursverwaltung.service.KursService;
import org.junit.Before;
import org.springframework.format.annotation.DateTimeFormat;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KursControllerTest {
    @InjectMocks
    KursController controller;

    @Mock
    KursService service;
    @Mock
    PersonService personService;

    @Mock
    private KursService kursService;

    @Mock
    private KursRepository kursRepository;

    @Mock
    private PersonRepository personRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeEach
    public void setup() {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }






    @Test
    void viewHomePage() {
        Model model = mock(Model.class);
        List<Kurs> listKurs = new ArrayList<>();
        when(service.listAll()).thenReturn(listKurs);

        String viewHomePage = controller.viewHomePage(model, null);

        Assertions.assertEquals("kurse", viewHomePage);
        verify(model, times(1)).addAttribute("listKurse", listKurs);
    }

    @Test
    void add() {
        // Erstellen der Objekte request und model
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setMethod("GET");
        Model model = new ConcurrentModel();

        // Aufrufen der Methode add
        String viewName = controller.add(new Kurs(), model);

        assertEquals("newkurs", viewName);
        assertTrue(model.containsAttribute("kurs1"));
        assertNotNull(model.getAttribute("kurs1"));

        // Überprüfen, dass der Dienst nicht aufgerufen wurde
        verifyNoInteractions(service);
    }


    @Test
    void showEditKursPage() {
        // Setup
        int kursId = 1;
        Kurs mockKurs = new Kurs();
        mockKurs.setKursId((long) kursId);
        mockKurs.setKursname("kursname");
        mockKurs.setStatus("active");
        mockKurs.setStart_datum(LocalDate.parse("2030-12-24"));
        mockKurs.setAnzahl_tage(2);
        mockKurs.setZyklus(1);
        mockKurs.setEnde_datum(LocalDate.parse("2031-02-02"));
        mockKurs.setMin_tn_anzahl(1);
        mockKurs.setMax_tn_anzahl(2);
        mockKurs.setFreie_plaetze(1);
        mockKurs.setAktuelle_tn_anzahl(0);
        mockKurs.setGebuehr_brutto(2.2);
        mockKurs.setGebuehr_netto(1.1);
        mockKurs.setMwst_prozent(2.2);
        mockKurs.setKurs_beschreibung("test");

        when(service.get(kursId)).thenReturn(mockKurs);

        ModelAndView mav = controller.showEditKursPage(kursId);

        assertEquals("newkurs", mav.getViewName());
        Kurs actualKurs = (Kurs) mav.getModel().get("kurs1");
        assertEquals(mockKurs, actualKurs);
        verify(service, times(1)).get(kursId);
    }


    @Test
    void getKursId() {
    }


    @Test
    void assignPersonToKurs() {
    }
}