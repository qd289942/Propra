package de.fernuni.kurs01584.ss23.darstellung;

import java.util.List;

import de.fernuni.kurs01584.ss23.dateiverarbeitung.*;
import de.fernuni.kurs01584.ss23.modell.*;

public class Darstellung {
    public static void main(String[] args) throws Exception {
        String filePath = "C:\\Users\\Kuan\\Desktop\\Practice\\programming_practice\\datainput.xml";
        Schlangenjagd schlangenjagd = DateneingabeXML.parseXML(filePath);
        System.out.println("ÄÖÜ");
    
        Dschungel dschungel = schlangenjagd.getDschungel();
        int zeilen = dschungel.getZeilen();
        int spalten = dschungel.getSpalten();
        String zeichenmenge = dschungel.getzeichenmenge();
        List<Feld> felder = dschungel.getFelder();
        

        System.out.println("Zeilen: " + zeilen);
        System.out.println("Spalten: " + spalten);
        System.out.println("Zeichen: " + zeichenmenge);
        
        for (Feld feld : felder) {
            System.out.println("FeldId: " + feld.getId() + ", Zeichen: " + feld.getZeichen());
        }
    }
}
