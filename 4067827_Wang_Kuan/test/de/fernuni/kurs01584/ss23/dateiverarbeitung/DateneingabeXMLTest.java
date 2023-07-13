package de.fernuni.kurs01584.ss23.dateiverarbeitung;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import de.fernuni.kurs01584.ss23.modell.Schlangenjagd;
import de.fernuni.kurs01584.ss23.modell.Zeit;

public class DateneingabeXMLTest {
    @Test
    @DisplayName("Test parseXML_Zeit Element")
    void testParseXML_ZeitElement(String filePath) {
        try {
            //String filePath = "path/to/xml/file.xml";
            Schlangenjagd schlangenjagd = DateneingabeXML.parseXML(filePath);
            
            Zeit zeit = schlangenjagd.getZeit();
            assertNotNull(zeit);
            assertEquals("s", zeit.getEinheit());
            assertEquals(10.0, zeit.getVorgabe());
            assertEquals(20.0, zeit.getAbgabe());
            
        } catch(Exception e) {
            fail("Exception: " + e.getMessage());
        }
    }
    
}
