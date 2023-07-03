package de.fernuni.kurs01584.ss23.algorithmus;

import de.fernuni.kurs01584.ss23.dateiverarbeitung.DatenausgabeXML;
import de.fernuni.kurs01584.ss23.dateiverarbeitung.DateneingabeXML;
import de.fernuni.kurs01584.ss23.modell.Schlangenjagd;

public class Test {
    public static void main(String[] args) throws Exception {
        String filePath = "C:\\Users\\Kuan\\Desktop\\Probleminstanzen2\\sj_p6_probleminstanz.xml";
        String filePath_2 = "C:\\Users\\Kuan\\Desktop\\sj_p6_loesung.xml";
        Schlangenjagd schlangenjagd = DateneingabeXML.parseXML(filePath);
        Schlangenjagd loesung = new Schlangenjagd();
        int maximalPunkt = 0;
        SchlangenSuche.sucheSchlange(schlangenjagd, loesung, maximalPunkt);
        DatenausgabeXML.writeXML(schlangenjagd, filePath_2);
        
        System.out.println("Program fertig.");
    }
}
