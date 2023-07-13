package de.fernuni.kurs01584.ss23.modell;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

import org.junit.jupiter.api.*;



public class DschungelTest {
    private Dschungel dschungel;
    private List<Feld> felder;
    
    @BeforeEach
    void init() {
        // initialisierung Dschungel und Feld Instanzen
        dschungel = new Dschungel();
        felder = new ArrayList<>();

        dschungel.setFelder(felder);
    }
    
    @Test
    @DisplayName("Test DefaultKonstruktor")
    void testDefaultKonstruktor() {
        
        dschungel.setZeichenmenge("ABCDEFGHIJKLMNOPQRSTUVWXYZÄÖÜ");
        assertNotNull(dschungel);
        assertEquals(0, dschungel.getZeilen());
        assertEquals(0, dschungel.getZeilen());
        char[] expectedChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZÄÖÜ".toCharArray();
        char[] actualChars = dschungel.getzeichenmenge().toCharArray();
        assertTrue(Arrays.equals(expectedChars, actualChars));
        assertNotNull(dschungel.getFelder());
        assertTrue(dschungel.getFelder().isEmpty());
    }
    
    @Test
    @DisplayName("Test DefaultKonstruktormitParametern")
    void testParameterizedKonstruktor() {
        int zeilen = 5;
        int spalten = 7;
        String zeichenmenge = "ABC";
        
        dschungel = new Dschungel(zeilen, spalten, zeichenmenge, felder);
        assertEquals(zeilen, dschungel.getZeilen());
        assertEquals(spalten, dschungel.getSpalten());
        assertEquals(zeichenmenge, dschungel.getzeichenmenge());
        assertEquals(felder, dschungel.getFelder());
    }
    @Test
    @DisplayName("Test Getters und Setters von Attribute")
    void testSetterundGettersAttribute() {
        int zeilen = 3;
        int spalten = 4;
        String zeichenmenge = "DEF";
        dschungel.setZeilen(zeilen);
        dschungel.setSpalten(spalten);
        dschungel.setZeichenmenge(zeichenmenge);
        dschungel.setFelder(felder);
        
        assertEquals(zeilen, dschungel.getZeilen());
        assertEquals(spalten, dschungel.getSpalten());
        assertEquals(zeichenmenge, dschungel.getzeichenmenge());
        assertEquals(felder, dschungel.getFelder());
    } 
    
    
    
}
