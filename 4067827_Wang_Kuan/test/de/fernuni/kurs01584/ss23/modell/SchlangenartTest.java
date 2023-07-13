package de.fernuni.kurs01584.ss23.modell;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

public class SchlangenartTest {
    
    private Schlangenart schlangenart;
    
    @BeforeEach
    void init() {
        schlangenart = new Schlangenart();
    }
    
    @Test
    @DisplayName("Test Default-Konstruktor")
    void testDefaultKonstruktor() {
        assertNotNull(schlangenart);
        assertNull(schlangenart.getId());
        assertEquals(0, schlangenart.getPunkte());
        assertEquals(0, schlangenart.getVerwendbarkeit());
        assertNull(schlangenart.getZeichenkette());
        assertNull(schlangenart.getNachStr());
    }
    
    @Test
    @DisplayName("Test Parameterisierter-Konstruktor")
    void testParameterisiertKonstruktor() {
        String id = "A1";
        int punkte = 10;
        int anzahl = 5;
        String zeichenkette = "XAT";
        Nachbarschaftsstruktur nachStr = new Nachbarschaftsstruktur();
        
        schlangenart = new Schlangenart(id, punkte, anzahl, zeichenkette, nachStr);
        
        assertEquals(id, schlangenart.getId());
        assertEquals(punkte, schlangenart.getPunkte());
        assertEquals(anzahl,schlangenart.getVerwendbarkeit());
        assertEquals(zeichenkette, schlangenart.getZeichenkette());
        assertEquals(nachStr, schlangenart.getNachStr());
        
    }
    
    @DisplayName("test negative Anzahl und Punkte von Schlangenarten.")
    @Test
    void testNegativAnzahlundPunkte() {
        String id = "A2";
        int punkte = -1;
        String zeichenkette = "XAT";
        Nachbarschaftsstruktur nachStr = new Nachbarschaftsstruktur();
        int anzahl = -1;
        
        assertThrows(IllegalArgumentException.class, () -> new Schlangenart(id, 1, anzahl, zeichenkette, nachStr),
                () -> "Fuer den (negativen) Anzahl '" + anzahl + "' wird keine Ausnahme erzeugt.");
        assertThrows(IllegalArgumentException.class, () -> new Schlangenart(id, punkte, 1, zeichenkette, nachStr),
                () -> "Fuer den (negativen) Anzahl '" + punkte + "' wird keine Ausnahme erzeugt.");
    }
    
    @Test
    @DisplayName("Test Setters und Getters")
    void testSettersundGetters() {
        String id = "A2";
        int punkte = 5;
        int anzahl = 3;
        String zeichenkette = "ABC";
        Nachbarschaftsstruktur nachStr = new Nachbarschaftsstruktur();
        
        schlangenart.setId(id);
        schlangenart.setPunkte(punkte);
        schlangenart.setAnzahl(anzahl);
        schlangenart.setZeichenkette(zeichenkette);
        schlangenart.setNachStr(nachStr);

        assertEquals(id, schlangenart.getId());
        assertEquals(punkte, schlangenart.getPunkte());
        assertEquals(anzahl,schlangenart.getVerwendbarkeit());
        assertEquals(zeichenkette, schlangenart.getZeichenkette());
        assertEquals(nachStr, schlangenart.getNachStr());
        
        
    }
}
