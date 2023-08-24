package de.fernuni.kurs01584.ss23.modell;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

import org.junit.jupiter.api.*;

public class SchlangenjagdTest {
    
    private Schlangenjagd schlangenjagd;
    
    /**
     * Instanz initialisieren
     */
    @BeforeEach
    void init() {
        schlangenjagd = new Schlangenjagd();
    }
    /**
     * Testfälle für Default Konstruktor
     * Test, ob leere Konstruktor funktioniert ist und Default Wert von Vorgabe und Abgabe
     */
    @Test
    @DisplayName("Test Default-Konstruktor")
    void testDefaultKontruktor() {
        assertNotNull(schlangenjagd);
        assertNull(schlangenjagd.getZeit());
        assertNull(schlangenjagd.getDschungel());
        assertNull(schlangenjagd.getSchlangenarten());
        assertNull(schlangenjagd.getSchlangen());
    }
    
    /**
     * Testfälle für Default Konstruktor mit Parametern
     * Test, ob parameterisierte Konstruktor funktioniert und richtige Wert aufnehmen kann
     */
    @Test
    @DisplayName("Test Parameterisiert Konstruktor")
    void testParameterisiertKontruktor() {
        Zeit zeit = new Zeit();
        Dschungel dschungel = new Dschungel();
        List<Schlangenart> schlangenarten = new ArrayList<>();
        List<Schlange> schlangen = new ArrayList<>();
        
        schlangenjagd = new Schlangenjagd(zeit, dschungel, schlangenarten, schlangen);
        
        assertEquals(zeit, schlangenjagd.getZeit());
        assertEquals(dschungel, schlangenjagd.getDschungel());
        assertEquals(schlangenarten, schlangenjagd.getSchlangenarten());
        assertEquals(schlangen, schlangenjagd.getSchlangen());
    }
    /**
     * Test Getters und Setters von Methoden
     */
    @Test
    @DisplayName("Test Setters und Getters")
    void testSettersUndGetters() {
        Zeit zeit = new Zeit();
        Dschungel dschungel = new Dschungel();
        List<Schlangenart> schlangenarten = new ArrayList<>();
        List<Schlange> schlangen = new ArrayList<>();
        
        schlangenjagd.setZeit(zeit);
        schlangenjagd.setDschungel(dschungel);
        schlangenjagd.setSchlangenarten(schlangenarten);
        schlangenjagd.setSchlangen(schlangen);
        
        assertEquals(zeit, schlangenjagd.getZeit());
        assertEquals(dschungel, schlangenjagd.getDschungel());
        assertEquals(schlangenarten, schlangenjagd.getSchlangenarten());
        assertEquals(schlangen, schlangenjagd.getSchlangen());
    }
    
}
