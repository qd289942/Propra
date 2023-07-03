package de.fernuni.kurs01584.ss23.algorithmus;

import java.util.*;
import java.util.concurrent.TimeUnit;

import de.fernuni.kurs01584.ss23.dateiverarbeitung.*;
import de.fernuni.kurs01584.ss23.modell.*;

public class SchlangenSuche {

    public static void sucheSchlange(Schlangenjagd schlangenjagd, Schlangenjagd loesung, int maximalePunkt) throws Exception {

        String outputFilePath = "C:\\Users\\Kuan\\Desktop\\Probleminstanzen2\\sj_p6_probleminstanz.xml";
        int aktuellePunkt = 0;
        long startZeit = System.currentTimeMillis();     

        // Rechne aktuelle gesamte Punktzahl aus Schlangen


        if (schlangenjagd.getSchlangen() != null) {
            for (Schlange schlange : schlangenjagd.getSchlangen()) {
                int schlangenartPunkt = schlange.getSchlangenart().getPunkte();
                List<Schlangenglied> schlangenglieden = schlange.getSchlangengliedmenge();
                int summenFeldPunkt = 0;
                for (Schlangenglied schlangengiled : schlangenglieden) {
                    summenFeldPunkt += schlangengiled.getFeld().getPunkte();
                }
                int schlangePunkt = schlangenartPunkt + summenFeldPunkt;
                aktuellePunkt += schlangePunkt;
            }
        }


        if (aktuellePunkt > maximalePunkt) { 
            loesung = schlangenjagd;
            maximalePunkt = aktuellePunkt;
        }


        // Umrechnung der Zeiteinheit
        String einheit = schlangenjagd.getZeit().getEinheit();
        double zeitVorgabe = schlangenjagd.getZeit().getVorgabe();

        double zeitVorgabeUmgerechnet = zeitUmrechunung(einheit, zeitVorgabe);
        long currentZeit = System.currentTimeMillis();
        long zeitInterval = currentZeit - startZeit;
        if (zeitInterval >= zeitVorgabeUmgerechnet) {
            //DatenausgabeXML.writeXML(lösung, outputFilePath);
            System.out.println("bisherige maximale Punktzahl: " + maximalePunkt);
            return;
        }



        // erzeuge zulässige Startfelder
        List<Feld> felder = schlangenjagd.getDschungel().getFelder();
        List<Feld> startFelder = erzeugZulaessigeStartFelder(felder);
        List<Schlangenart> schlangenarten = schlangenjagd.getSchlangenarten();

        if (startFelder.isEmpty()) {
            System.out.println("bisherige maximale Punktzahl: " + maximalePunkt);
            return;
        }
        // priorisiere und sortiere zulässige Startfelder
        priorisieren(startFelder);


        for (Feld feld : startFelder) {
            // Überprüft trotzdem Verwendbarkeit wegen der Änderung durch Einsetzen der Nachbarfelder
            if (feld.getVerwendbarkeit() > 0) {
                // bestimme zulässige Schlangenarten für Startfeld
                List<Schlangenart> startSchlangenarten = erzeugZulassigeSchlangenart(schlangenarten, feld);
                if (!startSchlangenarten.isEmpty()) {
                    // priorisiere und sortiere zulässige Schlangenarten
                    priorisieren(startSchlangenarten);

                    feld.setVerwendbarkeit(feld.getVerwendbarkeit() - 1);

                    for (Schlangenart schlangenart : startSchlangenarten) {
                        // Überprüft trotzdem Verwendbarkeit wegen der Änderung durch Einsetzen der Schlange
                        if (schlangenart.getVerwendbarkeit() > 0) {
                            // schlangenart Anzahl - 1
                            schlangenart.setAnzahl(schlangenart.getVerwendbarkeit() - 1);
                            // erzeuge neue Schlange mit Schlangenkopf für Schlangenart
                            Schlange schlange = new Schlange();
                            schlange.setSchlangenart(schlangenart);
                            //schlangen.add(schlange);
                            Schlangenglied schlangenkopf = new Schlangenglied();
                            // setze Schlangenkopf auf Startfeld
                            schlangenkopf.setFeld(feld);
                            // erstellt neue schlangengliedList und setzt schlangenkopf in schlangengliedList
                            List<Schlangenglied> schlangengliedList = new ArrayList<>();
                            schlangengliedList.add(schlangenkopf);
                            // suche Schlangenglieden mit Schlangenkopf
                            sucheSchlangenglied(schlangenjagd,schlangengliedList,schlangenkopf,schlange,startFelder);
                            // keine Schlange für diese Schlangenart gefunden
                            if (schlange.getSchlangengliedmenge() == null) {
                                // Anzahl zurücksetzen
                                schlangenart.setAnzahl(schlangenart.getVerwendbarkeit() + 1);
                            }
                            // entferne Schlangenkopf, Schlangenglied und Schlange
                            schlangengliedList = null;
                            schlange = null;
                            schlangenkopf = null;

                        }
                    }
                }
            }

        }
        return;

    }

    private static void sucheSchlangenglied (Schlangenjagd schlangenjagd, List<Schlangenglied> schlangengliedList, Schlangenglied voherigesGlied, Schlange schlange,  List<Feld> priorisierteFelder) throws Exception {

        // erzeuge zulässige Nachbarfelder für vorherigesGlied
        int indexVorherigesGlied = schlangengliedList.indexOf(voherigesGlied);
        List <Feld> nachbarFelder = erzeugZulaessigeNachbarFelder(schlange.getSchlangenart(), voherigesGlied, indexVorherigesGlied , priorisierteFelder);

        // Wenn vorherigesGlied ist letztes Schlangenglied
        if (nachbarFelder.isEmpty()) {
            // prüft, ob Schlange vollständig ist nach Schlangenart
            if (schlangengliedList.size() == schlange.getSchlangenart().getZeichenkette().length()) {
                schlange.setSchlangengliedmenge(schlangengliedList); 
                if (schlangenjagd.getSchlangen() == null) {
                    List<Schlange> schlangen = new ArrayList<>();
                    schlangen.add(schlange);
                    schlangenjagd.setSchlangen(schlangen);
                    schlangen = null;
                }
                else {
                    schlangenjagd.getSchlangen().add(schlange);
                }
            }

            else {
                // ein Schritt zurück falls keine vollständige Schlange gefunden wird
                voherigesGlied.getFeld().setVerwendbarkeit(voherigesGlied.getFeld().getVerwendbarkeit() + 1);
                // letzte Element in schlangengliedList entfernen
                schlangengliedList.remove(schlangengliedList.size() - 1);

            }
            return;

        }

        else {
            // priorisiere und sortiere zulässige Nachbarfelder
            priorisieren(nachbarFelder);

            for (Feld nachbarFeld: nachbarFelder) {
                // Überprüft trotzdem Verwendbarkeit wegen der Änderung durch Einsetzen der Nachbarfelder
                if (nachbarFeld.getVerwendbarkeit() > 0) {
                    Schlangenglied nachbarSchlangenglied = new Schlangenglied();
                    // setze Schlangenglied auf Nachbarfeld
                    nachbarSchlangenglied.setFeld(nachbarFeld);
                    // Verwendbarkeit für jede verwendete Feld - 1
                    nachbarFeld.setVerwendbarkeit(nachbarFeld.getVerwendbarkeit() - 1);
                    schlangengliedList.add(nachbarSchlangenglied);
                    sucheSchlangenglied(schlangenjagd, schlangengliedList, nachbarSchlangenglied, schlange, priorisierteFelder);
                    if (schlange.getSchlangengliedmenge() != null) {
                        break;
                    }
                    nachbarSchlangenglied = null;
                }

            }


            return;
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
            String firstZeichen = String.valueOf(schlangenart.getZeichenkette().charAt(0));
            if (feld.getZeichen().equals(firstZeichen) && schlangenart.getVerwendbarkeit() > 0) {
                startSchlangenarten.add(schlangenart);
            }
        }

        return startSchlangenarten;
    }


    private static List<Feld> erzeugZulaessigeNachbarFelder (Schlangenart schlangenart, Schlangenglied voherigesGlied, int index, List<Feld> startfelder) {

        int feldSpalte = voherigesGlied.getFeld().getSpalte();
        int feldZeile = voherigesGlied.getFeld().getZeile();
        List<Feld> nachbarfelder = new ArrayList<>();
        // prüft ob vorherigesGlied letzte Glied von Schlangenart ist
        if (index >= schlangenart.getZeichenkette().length() - 1) {}
        else {
            String zeichen = String.valueOf(schlangenart.getZeichenkette().charAt(index + 1));
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
                        else {
                            // Es gibt zwei Parametern für Sprung Typ
                            int parameter_1 = parameters.get(0).getWert();
                            int parameter_2 = parameters.get(0).getWert();

                            if ((Math.abs(nachbarFeld.getSpalte() - feldSpalte) == parameter_1 && Math.abs(nachbarFeld.getZeile() - feldZeile) == parameter_2) || (Math.abs(nachbarFeld.getSpalte() - feldSpalte) == parameter_2 && Math.abs(nachbarFeld.getZeile() - feldZeile) == parameter_1)) {
                                nachbarfelder.add(nachbarFeld);
                            }
                        }
                    }
                }
            }
        }

        return nachbarfelder;

    }

    // Priorisieren 1. Punkte 2. Verwendbarkeit
    private static <T extends Priorisierbar> void priorisieren(List<T> list) {
        Comparator<T> comparator_1 = new Comparator<T>() {
            @Override
            public int compare(T o1, T o2) {
                Integer punkt1 = o1.getPunkte();
                Integer punkt2 = o2.getPunkte();
                return punkt2.compareTo(punkt1);
            }
        };

        Comparator<T> comparator_2 = new Comparator<T>() {
            @Override
            public int compare(T o1, T o2) {
                Integer verwendbarkeit1 = o1.getVerwendbarkeit();
                Integer verwendbarkeit2 = o2.getVerwendbarkeit();
                return verwendbarkeit2.compareTo(verwendbarkeit1);
            }
        };

        Collections.sort(list, comparator_1);
        Collections.sort(list, comparator_2);
    }

    private static double zeitUmrechunung(String einheit, double inputZeit) {
        long umgerechnetZeit = 0;
        if (einheit.equals("d")) {
            umgerechnetZeit = convertToMilliseconds(inputZeit, TimeUnit.DAYS);
        }
        else if(einheit.equals("h")) {
            umgerechnetZeit = convertToMilliseconds(inputZeit, TimeUnit.HOURS);
        }
        else if(einheit.equals("min")) {
            umgerechnetZeit = convertToMilliseconds(inputZeit, TimeUnit.MINUTES);
        }
        else if(einheit.equals("s")) {
            umgerechnetZeit = convertToMilliseconds(inputZeit, TimeUnit.SECONDS);
        }
        else {umgerechnetZeit = (long) inputZeit;}

        return umgerechnetZeit;
    }

    public static long convertToMilliseconds(double time, TimeUnit unit) {
        long milliseconds;

        switch (unit) {
        case NANOSECONDS:
            milliseconds = (long) (time / 1000000);
            break;
        case MICROSECONDS:
            milliseconds = (long) (time / 1000);
            break;
        case MILLISECONDS:
            milliseconds = (long) time;
            break;
        case SECONDS:
            milliseconds = (long) (time * 1000);
            break;
        case MINUTES:
            milliseconds = (long) (time * 60000);
            break;
        case HOURS:
            milliseconds = (long) (time * 3600000);
            break;
        case DAYS:
            milliseconds = (long) (time * 86400000);
            break;
        default:
            throw new IllegalArgumentException("ungültige Zeiteinheit: " + unit);
        }

        return milliseconds;
    }
}
