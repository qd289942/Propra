package de.fernuni.kurs01584.ss23.modell;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;

import org.junit.jupiter.api.*;

public class ZeitTest {
    private Zeit zeit;
    
    @BeforeEach
    void init() {
        zeit = new Zeit();
    }
    
    @Test
    @DisplayName("Test Default-Konstruktor")
    void testDefaultKontruktor() {
        assertNotNull(zeit);
        assertNull(zeit.getEinheit());
        assertEquals(0.0, zeit.getVorgabe());
        assertEquals(0.0, zeit.getAbgabe());
    }
    
    @Test
    @DisplayName("Test Parameterisiert Konstruktor")
    void testParameterisiertKontruktor() {
        String einheit = "min";
        double vorgabe = 2.0;
        double abgabe = 1.0;
        
        zeit = new Zeit(einheit, vorgabe, abgabe);
        
        assertEquals(einheit, zeit.getEinheit());
        assertEquals(vorgabe, zeit.getVorgabe());
        assertEquals(abgabe, zeit.getAbgabe());
    }
    
    @Test
    @DisplayName("Test Setters und Getters")
    void testSettersUndGetters() {
        String einheit = "min";
        double vorgabe = 2.0;
        double abgabe = 1.0;
        
        zeit.setEinheit(einheit);
        zeit.setVorgabe(vorgabe);
        zeit.setAbgabe(abgabe);
        
        assertEquals(einheit, zeit.getEinheit());
        assertEquals(vorgabe, zeit.getVorgabe());
        assertEquals(abgabe, zeit.getAbgabe());
    }
    
    @DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
    @Nested
    class Wiederholte_Tests {
        static Random zufallszahlenGenerator;

        @BeforeAll
        static void initAll() {
            zufallszahlenGenerator = new Random(1);
        }

        @DisplayName("Wiederholter getter Test.")
        @RepeatedTest(10)
        void testeSetVorgabe(RepetitionInfo repetitionInfo) {
            double vorgabe = zufallszahlenGenerator.nextFloat();
            zeit.setVorgabe(vorgabe);
            assertEquals(vorgabe, zeit.getVorgabe());
        }
    }
}
