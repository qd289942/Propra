package de.fernuni.kurs01584.ss23.dateiverarbeitung;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import de.fernuni.kurs01584.ss23.modell.Schlangenjagd;
import de.fernuni.kurs01584.ss23.modell.Zeit;

public class DateneingabeXMLTest {
    @Test
    @DisplayName("Test parseXML_Zeit Element")
    void testParseXML_ZeitElement() {
        try {
            String relativePath = "..\\..\\..\\..\\Probleminstanzen2\\sj_p8_probleminstanz.xml";

            Schlangenjagd schlangenjagd = DateneingabeXML.parseXML(relativePath);
            
            Zeit zeit = schlangenjagd.getZeit();
            assertNotNull(zeit);
            assertEquals("min", zeit.getEinheit());
            assertEquals(0.2, zeit.getVorgabe());
            assertEquals(0.0, zeit.getAbgabe());
            
        } catch(Exception e) {
            fail("Exception: " + e.getMessage());
        }
    }
    
}
