package de.fernuni.kurs01584.ss23.algorithmus;

import de.fernuni.kurs01584.ss23.darstellung.DarstellungLoesungen;
import de.fernuni.kurs01584.ss23.dateiverarbeitung.DatenausgabeXML;
import de.fernuni.kurs01584.ss23.dateiverarbeitung.DateneingabeXML;
import de.fernuni.kurs01584.ss23.modell.Schlangenjagd;

public class Test {
    public static void main(String[] args) throws Exception {
        String filePath = "C:\\Users\\Kuan\\Desktop\\Probleminstanzen2\\sj_p6_probleminstanz.xml";
        String filePath_2 = "C:\\Users\\Kuan\\Desktop\\sj_p6_loesung.xml";
        String filePath_3 = "C:\\Users\\Kuan\\Desktop\\Probleminstanzen2\\sj_p6_loesung.xml";
        Schlangenjagd schlangenjagd = DateneingabeXML.parseXML(filePath);
        SchlangenSuche.sucheSchlange(schlangenjagd, filePath_2);
        DarstellungLoesungen.loesungDarstellen(filePath_3, schlangenjagd);
        //DarstellungLoesungen.probleminstanzDarstellen(filePath_3, schlangenjagd);
    }
}
