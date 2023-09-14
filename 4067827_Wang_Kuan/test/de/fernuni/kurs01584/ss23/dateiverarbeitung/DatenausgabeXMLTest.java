package de.fernuni.kurs01584.ss23.dateiverarbeitung;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import de.fernuni.kurs01584.ss23.modell.*;

import static org.junit.jupiter.api.Assertions.*;
import java.io.File;
import java.nio.file.Paths;
import java.util.*;

public class DatenausgabeXMLTest {
    // Lade die Datei mit relativem Pfad
    File inputFile = new File("./Probleminstanzen2/sj_p8_probleminstanz.xml");
    File outputFile = new File("./Probleminstanzen2/Test/sj_p8_loesung_1.xml");

    String inputfilePathMitPunkte = inputFile.getAbsolutePath();
    String outputfilePathMitPunkte = outputFile.getAbsolutePath();

    String inputnormalizedPath = Paths.get(inputfilePathMitPunkte).normalize().toString();
    String outputnormalizedPath = Paths.get(outputfilePathMitPunkte).normalize().toString();
    
    /**
     * Testfälle für WriteXML
     * @throws Exception
     */
    @Test
    @DisplayName("test Write XML")
    void testWriteXML() throws Exception {
        Schlangenjagd problemInstanz = DateneingabeXML.parseXML(inputnormalizedPath);
        Schlangenjagd schlangenjagd = new Schlangenjagd();
        schlangenjagd = problemInstanz;
        List <Schlange> schlangenList = new ArrayList<>();
        schlangenList.add(new Schlange());
        schlangenList.add(new Schlange());
        schlangenList.add(new Schlange());
        
        List<Schlangenglied> schlangengliedList = new ArrayList<>();
        
        schlangengliedList.add(new Schlangenglied());
        schlangengliedList.add(new Schlangenglied());
        schlangengliedList.add(new Schlangenglied());
        schlangengliedList.add(new Schlangenglied());
        schlangengliedList.add(new Schlangenglied());
        
        
        Random random = new Random();
        int randomIndex = random.nextInt(0, problemInstanz.getDschungel().getFelder().size() - 1);
        for (Schlangenglied schlangenglied : schlangengliedList) {
            schlangenglied.setFeld(problemInstanz.getDschungel().getFelder().get(randomIndex));
        }
        
        for (Schlange schlange : schlangenList) {
            schlange.setSchlangenart(problemInstanz.getSchlangenarten().get(0));
            schlange.setSchlangengliedmenge(schlangengliedList);
        }
        
        schlangenjagd.setSchlangen(schlangenList);
        
        // Schreibt Daten in XML File
        DatenausgabeXML.writeXML(problemInstanz, schlangenList, outputnormalizedPath);
        
        File file = new File(outputnormalizedPath);
        assertTrue(file.exists());
        
        
        Schlangenjagd loesung = DateneingabeXML.parseXMLmitSchlangen(outputnormalizedPath);
        assertTrue(loesung.getSchlangen() != null);
    }
    
    
}
