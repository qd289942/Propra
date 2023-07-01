package de.fernuni.kurs01584.ss23.algorithmus;

import java.util.*;

import de.fernuni.kurs01584.ss23.dateiverarbeitung.*;
import de.fernuni.kurs01584.ss23.modell.*;

public class SchlangenSuche {

    public static Schlangenjagd sucheSchlange(Schlangenjagd schlangenjagd, String FilePath) throws Exception {
        

        
        int aktuellePunkt = 0;
        int maximalePunkt = 0;
        double zeitvorgabe = schlangenjagd.getZeit().getVorgabe();

        if (aktuellePunkt > maximalePunkt) {
            DatenausgabeXML.writeXML(schlangenjagd, FilePath);
        }

        if (System.currentTimeMillis() >= zeitvorgabe) {
            return schlangenjagd;
        }

        // erzeuge zulässige Startfelder
        List<Feld> felder = schlangenjagd.getDschungel().getFelder();
        List<Feld> startFelder = erzeugZulaessigeStartFelder(felder);

        // priorisiere und sortiere zulässige Startfelder


        List<Schlange> schlangen = schlangenjagd.getSchlangen();
        List<Schlangenart> schlangenarten = schlangenjagd.getSchlangenarten();


        for (Feld feld : startFelder) {
            int aktuelleVerwendbarkeit = feld.getVerwendbarkeit();
            feld.setVerwendbarkeit(aktuelleVerwendbarkeit - 1);
            // bestimme zulässige Schlangenarten für Startfeld
            List<Schlangenart> startSchlangenarten = erzeugZulassigeSchlangenart(schlangenarten, feld);

            // priorisiere und sortiere zulässige Schlangenarten


            for (Schlangenart schlangenart : startSchlangenarten) {
                // erzeuge neue Schlange mit Schlangenkopf für Schlangenart
                Schlange schlange = new Schlange();
                schlange.setSchlangenart(schlangenart);
                schlangen.add(schlange);
                Schlangenglied schlangenkopf = new Schlangenglied();

                // setze Schlangenkopf auf Startfeld
                schlangenkopf.setFeld(feld);
                // suche Schlangenglieden mit Schlangenkopf
                sucheSchlangenglied(schlangenkopf,schlange,startFelder);
                // entferne Schlangenkopf und Schlange
                schlangen.remove(schlange);

            }
 
        }



        return schlangenjagd;

    }

    private static void sucheSchlangenglied (Schlangenglied voherigesGlied, Schlange schlange, List<Feld> startFelder) throws Exception {
        List<Schlangenglied> gufundeneSchlangenglieden = new ArrayList<>();
        
        // Wenn vorherigesGlied ist letztes Schlangenglied
        if (gufundeneSchlangenglieden.indexOf(voherigesGlied) == gufundeneSchlangenglieden.size() - 1) {
            schlange.setSchlangengliedmenge(gufundeneSchlangenglieden);
            sucheSchlange();
            return;
        }
        // erzeuge zulässige Nachbarfelder für vorherigesGlied
        int indexVorherigesGlied = gufundeneSchlangenglieden.indexOf(voherigesGlied);
        List <Feld> nachbarFelder = erzeugZulaessigeNachbarFelder(schlange.getSchlangenart(), voherigesGlied, indexVorherigesGlied, startFelder);
        // priorisiere und sortiere zulässige Nachbarfelder
        
        for (Feld nachbarFeld: nachbarFelder) {
            Schlangenglied nachbarSchlangenglied = new Schlangenglied();
            // setze Schlangenglied auf Nachbarfeld
            nachbarSchlangenglied.setFeld(nachbarFeld);
            // Verwendbarkeit - 1 wegen verwendetes Nachbarfeld
            nachbarFeld.setVerwendbarkeit(nachbarFeld.getVerwendbarkeit() - 1);
            gufundeneSchlangenglieden.add(nachbarSchlangenglied);
            sucheSchlangenglied(nachbarSchlangenglied, schlange, startFelder);
            // entferne Schlangenglied
            schlange.setSchlangengliedmenge(null);
        }
        
        
    }

    private static List<Feld> erzeugZulaessigeStartFelder (List<Feld> felder) {

        List<Feld> Startfelder = new ArrayList<>();
        for (Feld feld : felder) {
            if (feld.getVerwendbarkeit() > 0) {
                Startfelder.add(feld);
            }
        }
        return Startfelder;

    }

    private static List<Schlangenart> erzeugZulassigeSchlangenart (List<Schlangenart> schlangenarten, Feld feld){
        List<Schlangenart> startSchlangenarten = new ArrayList<>();
        for (Schlangenart schlangenart : schlangenarten) {
            if (feld.getZeichen() == String.valueOf(schlangenart.getZeichenkette().charAt(0))) {
                startSchlangenarten.add(schlangenart);
            }
        }

        return startSchlangenarten;
    }

    private static List<Feld> erzeugZulaessigeNachbarFelder (Schlangenart schlangenart, Schlangenglied voherigesGlied, int index, List<Feld> startfelder) {
        
        int feldSpalte = voherigesGlied.getFeld().getSpalte();
        int feldZeile = voherigesGlied.getFeld().getZeile();

        String zeichen = String.valueOf(schlangenart.getZeichenkette().charAt(index));

        List<Feld> nachbarfelder = new ArrayList<>();
        String nachbarTyp = schlangenart.getNachStr().getTyp();
        List<Parameter> parameters = schlangenart.getNachStr().getParameters();

        for (Feld nachbarFeld : startfelder) {
            if (nachbarFeld.getVerwendbarkeit() > 0) {
                if (nachbarFeld.getZeichen().equals(zeichen)) {
                    if (nachbarTyp.equals("Distanz")) {
                        // Es gibt nur ein Parameter für Distanz Typ
                        int parameter = parameters.get(0).getWert();
                        if (Math.abs(nachbarFeld.getSpalte() - feldSpalte) <= parameter && Math.abs(nachbarFeld.getZeile() - feldZeile) <= Math.abs(nachbarFeld.getSpalte() - feldSpalte)) {
                            nachbarfelder.add(nachbarFeld);
                        }
                    }

                    // Es gibt zwei Parametern für Sprung Typ
                    int parameter_1 = parameters.get(0).getWert();
                    int parameter_2 = parameters.get(0).getWert();

                    if ((Math.abs(nachbarFeld.getSpalte() - feldSpalte) == parameter_1 && Math.abs(nachbarFeld.getZeile() - feldZeile) == parameter_2) || (Math.abs(nachbarFeld.getSpalte() - feldSpalte) == parameter_2 && Math.abs(nachbarFeld.getZeile() - feldZeile) == parameter_1)) {
                        nachbarfelder.add(nachbarFeld);
                    }
                }
            }
        }

        return nachbarfelder;

    }
   
    
}
