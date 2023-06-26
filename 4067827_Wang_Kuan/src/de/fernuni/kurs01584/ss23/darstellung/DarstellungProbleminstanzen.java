package de.fernuni.kurs01584.ss23.darstellung;

import java.util.List;

import de.fernuni.kurs01584.ss23.dateiverarbeitung.DateneingabeXML;
import de.fernuni.kurs01584.ss23.modell.*;

public class DarstellungProbleminstanzen {
    public static void main(String[] args) throws Exception {
        String filePath = "C:\\Users\\Kuan\\Desktop\\Practice\\programming_practice\\datainput.xml";
        Schlangenjagd schlangenjagd = DateneingabeXML.parseXML(filePath);
        
        //System.out.println("ÄÖÜ");
        System.out.println("Probleminstanzen: ");
        Dschungel dschungel = schlangenjagd.getDschungel();
        int zeilen = dschungel.getZeilen();
        int spalten = dschungel.getSpalten();
        String zeichenmenge = dschungel.getzeichenmenge();
        List<Feld> felder = dschungel.getFelder();
        

        // Darstellung der Anzahl der Zeilen und Spalten des Dschungels sowie Zeichenmenge
        System.out.println("Anzahl der Zeilen des Dschungels: " + zeilen);
        System.out.println("Anzahl der Spalten des Dschungels: " + spalten);
        System.out.println("Dschungel verwendeten Zeichen: " + zeichenmenge);
        System.out.println();
        // Darstellung der Schlangenarten
        System.out.println("vorhandene Schlangenarten: ");
        List<Schlangenart> schlangenarten = schlangenjagd.getSchlangenarten();
        
        for (Schlangenart schlangenart : schlangenarten) {
            System.out.println(schlangenart.getId() + ": Zeichenkette: " + schlangenart.getZeichenkette() + "; Nachbarschaftsstruktur: " + schlangenart.getNachStr().getTyp() + "; Punkten: " + schlangenart.getPunkte() + "; Anzahl: " + schlangenart.getAnzahl());
        }
        
        System.out.println();
        // Darstellung der Dschungelfelder
        System.out.println("Dschungelfelder sind folgend dargestellt: ");

        
        for (Feld feld : felder) {
            System.out.println(feld.getId() + ": " + "Zeichen: " + feld.getZeichen() + "; Verwendbarkeit: " + feld.getVerwendbarkeit() + "; Punkten: " + feld.getPunkte());
        }
    }
}
