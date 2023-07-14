package de.fernuni.kurs01584.ss23.darstellung.text;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import de.fernuni.kurs01584.ss23.darstellung.DarstellungLoesungen;
import de.fernuni.kurs01584.ss23.dateiverarbeitung.DateneingabeXML;
import de.fernuni.kurs01584.ss23.modell.*;

import static org.junit.jupiter.api.Assertions.*;

import java.io.*;
import java.nio.file.Paths;
import java.util.*;

public class DarstellungLoesungenTest {
    // Lade die Datei mit relativem Pfad
    File inputFile = new File("./Probleminstanzen2/sj_p8_probleminstanz.xml");
    File outputFile = new File("./Probleminstanzen2/Test/sj_p8_loesung_1.xml");

    String inputfilePathMitPunkte = inputFile.getAbsolutePath();
    String outputfilePathMitPunkte = outputFile.getAbsolutePath();

    String inputnormalizedPath = Paths.get(inputfilePathMitPunkte).normalize().toString();
    String outputnormalizedPath = Paths.get(outputfilePathMitPunkte).normalize().toString();
    
    @DisplayName("test Loesung Darstellung")
    @Test
    void testLoesungDarstellen() throws Exception {
        // neue Schlangenjagd erstelle zu testen
        // Einstellung der Testdaten
        Dschungel dschungel = new Dschungel(2, 3, "PR☻PR♥", null);
        List<Feld> felder = new ArrayList<>();
        Feld feld1 = new Feld("F0", 0, 0, 1, 1, "P");
        Feld feld2 = new Feld("F1", 0, 1, 1, 1, "R");
        Feld feld3 = new Feld("F2", 0, 2, 1, 1, "☻");
        Feld feld4 = new Feld("F3", 1, 0, 1, 1, "P");
        Feld feld5 = new Feld("F4", 1, 1, 1, 0, "R");
        Feld feld6 = new Feld("F5", 1, 2, 1, 1, "♥");
        
        felder.add(feld1);
        felder.add(feld2);
        felder.add(feld3);
        felder.add(feld4);
        felder.add(feld5);
        felder.add(feld6);
        dschungel.setFelder(felder);
        
        Parameter p1 = new Parameter(5);
        List<Parameter> parameters = new ArrayList<>();
        parameters.add(p1);
        
        Nachbarschaftsstruktur nachstr = new Nachbarschaftsstruktur();
        nachstr.setTyp("Distanz");
        nachstr.setParameters(parameters);
        
        Schlangenart schlangenart = new Schlangenart();
        schlangenart.setAnzahl(2);
        schlangenart.setId("A0");
        schlangenart.setNachStr(nachstr);
        schlangenart.setPunkte(1);
        schlangenart.setZeichenkette("PR☻PR♥");
        
        Schlangenjagd schlangenjagd = new Schlangenjagd();
        List <Schlange> schlangenList = new ArrayList<>();
        schlangenList.add(new Schlange());
        
        List<Schlangenart> schlangenarten = new ArrayList<>();
        schlangenarten.add(schlangenart);
        
        List<Schlangenglied> schlangengliedList = new ArrayList<>();
        
        schlangengliedList.add(new Schlangenglied());
        schlangengliedList.add(new Schlangenglied());
        
        
        int i = 0;
        for (Schlangenglied schlangenglied : schlangengliedList) {
            schlangenglied.setFeld(felder.get(5));
            i++;
        }
        
        for (Schlange schlange : schlangenList) {
            schlange.setSchlangenart(schlangenart);
            schlange.setSchlangengliedmenge(schlangengliedList);
        }
        
        schlangenjagd.setSchlangen(schlangenList);
        schlangenjagd.setDschungel(dschungel);
        schlangenjagd.setSchlangenarten(schlangenarten);
        schlangenjagd.setZeit(null);
        
        // System.out umleiten, um die Ausgabe zu erfassen
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        PrintStream originalPrintStream = System.out;
        System.setOut(printStream);
        
        // Aufruf von Methode loesungDarstellen
        DarstellungLoesungen.loesungDarstellen(schlangenjagd);
        String expectedOutput = "Lösungen: \nverwendete Schlangenarten: \n" +
                "\nA0: Zeichenkette: PR☻PR♥; Nachbarschaftsstruktur: Distanz\n" +
                "\nZeichendschungel: \n" +
                "Schlange_1 : \n" +
                "\u001B[31mP\u001B[0m" + "\u001B[31mR\u001B[0m" + ".\n..." +
                "\n\n\nSchlangenglieder (Zeile, Spalte): \n" +
                "\nSchlange_1 : (0,0); (0,1);";
        assertEquals(expectedOutput, outputStream.toString().trim());
    }
    
}
