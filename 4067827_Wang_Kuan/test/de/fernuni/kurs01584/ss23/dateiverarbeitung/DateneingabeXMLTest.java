package de.fernuni.kurs01584.ss23.dateiverarbeitung;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.nio.file.*;
import java.util.List;

import org.junit.jupiter.api.*;

import de.fernuni.kurs01584.ss23.modell.*;

public class DateneingabeXMLTest {
    
    // Lade die Datei mit relativem Pfad
    File file = new File("./Probleminstanzen2/sj_p8_probleminstanz.xml");

    String filePathMitPunkte = file.getAbsolutePath();

    String normalizedPath = Paths.get(filePathMitPunkte).normalize().toString();
    
    
    
    @Test
    @DisplayName("Test parseXML Zeit Element")
    void testParseXML_ZeitElement() throws Exception {
        Schlangenjagd schlangenjagd = DateneingabeXML.parseXML(normalizedPath);
        Zeit zeit = schlangenjagd.getZeit();
        assertNotNull(zeit);
        assertEquals("min", zeit.getEinheit());
        assertEquals(0.2, zeit.getVorgabe());
        assertEquals(0.0, zeit.getAbgabe());
    }

    @Test
    @DisplayName("Test parseXML Dschungel Element")
    void testParseXML_DschungelElement() throws Exception {
        Schlangenjagd schlangenjagd = DateneingabeXML.parseXML(normalizedPath);
        Dschungel dschungel = schlangenjagd.getDschungel();
        assertNotNull(dschungel);
        assertEquals(8, dschungel.getZeilen());
        assertEquals(8, dschungel.getSpalten());
        assertEquals("☀☁☂☃☄★☆☇☔☕☼☽☾♨♫⚘⚽⛄⛅⛆⛈⛐⛰⛵⛷⛸⛹⛺", dschungel.getzeichenmenge());
        assertNotNull(dschungel.getFelder());
        assertEquals(64, dschungel.getFelder().size());
    }
    
    @Test
    @DisplayName("Test parseXML Schlangenarten Element")
    void testParseXML_SchlangenartenElement() throws Exception {
        Schlangenjagd schlangenjagd = DateneingabeXML.parseXML(normalizedPath);
        List<Schlangenart> schlangenarten = schlangenjagd.getSchlangenarten();
        assertNotNull(schlangenarten);
        assertEquals(5, schlangenarten.size());
        
        Schlangenart schlangenart1 = schlangenarten.get(0);
        assertEquals("A0", schlangenart1.getId());
        assertEquals(10, schlangenart1.getPunkte());
        assertEquals(1, schlangenart1.getVerwendbarkeit());
        assertEquals("PR☻PR♥", schlangenart1.getZeichenkette());
        assertNotNull(schlangenart1.getNachStr());
        assertEquals("Distanz", schlangenart1.getNachStr().getTyp());
        assertNotNull(schlangenart1.getNachStr().getParameters());
        assertEquals(1, schlangenart1.getNachStr().getParameters().size());
        assertEquals(1, schlangenart1.getNachStr().getParameters().get(0).getWert());
        
        Schlangenart schlangenart2 = schlangenarten.get(1);
        assertEquals("A1", schlangenart2.getId());
        assertEquals(10, schlangenart2.getPunkte());
        assertEquals(1, schlangenart2.getVerwendbarkeit());
        assertEquals("PR☺PR♡", schlangenart2.getZeichenkette());
        assertNotNull(schlangenart2.getNachStr());
        assertEquals("Distanz", schlangenart2.getNachStr().getTyp());
        assertNotNull(schlangenart2.getNachStr().getParameters());
        assertEquals(1, schlangenart2.getNachStr().getParameters().size());
        assertEquals(1, schlangenart2.getNachStr().getParameters().get(0).getWert());
        
        Schlangenart schlangenart3 = schlangenarten.get(2);
        assertEquals("A2", schlangenart3.getId());
        assertEquals(1, schlangenart3.getPunkte());
        assertEquals(6, schlangenart3.getVerwendbarkeit());
        assertEquals("☺☻", schlangenart3.getZeichenkette());
        assertNotNull(schlangenart3.getNachStr());
        assertEquals("Distanz", schlangenart3.getNachStr().getTyp());
        assertNotNull(schlangenart3.getNachStr().getParameters());
        assertEquals(1, schlangenart3.getNachStr().getParameters().size());
        assertEquals(1, schlangenart3.getNachStr().getParameters().get(0).getWert());
    }

}
