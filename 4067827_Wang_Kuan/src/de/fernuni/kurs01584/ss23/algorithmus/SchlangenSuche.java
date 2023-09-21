package de.fernuni.kurs01584.ss23.algorithmus;

import java.util.*;
import de.fernuni.kurs01584.ss23.modell.*;

public class SchlangenSuche {

    // Attribute definieren
    private int aktuellePunkte = 0;
    private int bisherMaxPunkte = 0;
    private List<Schlange> schlangeList = new ArrayList<>();
    private List<Schlange> loesungSchlangen = new ArrayList<>();
    // trenne problemInstanz von schlangenjagd, um die Ausgangszustand zu speichern
    private Schlangenjagd problemInstanz;
    private Schlangenjagd schlangenjagd;
    // flag dient zur beende der Suche
    private boolean flag = false;
    private long startZeit = 0;


    /**
     * Liest die vorgegebene Eingabedatei mit einer vollstaendigen Probleminstanz und startet das 
     * Loesungverfahren für die Schlangensuche. Suche mögliche Schlangen Analog zum Backtracking Algorithmus.
     * Die gefundene Loesung wird zusammen mit der Probleminstanz in der Ausgabedatei gespeichert.
     * 
     * @throws Exception
     */
    public void sucheSchlange() throws Exception {

        // Rechne aktuelle gesamte Punktzahl aus Schlangen
        if (schlangeList != null) {
            aktuellePunkte = rechnePunkt(schlangeList);
        }

        // Loesung Speichern
        if (aktuellePunkte > bisherMaxPunkte) {
            bisherMaxPunkte = aktuellePunkte;
            if (!loesungSchlangen.isEmpty()) {
                loesungSchlangen.clear();
            }
            // Kopie von Schlangenglied von jeder Schlange in SchlangenList
            for (Schlange schlange : schlangeList) {
                List<Schlangenglied> schlangeGliedKopie = new ArrayList<>(schlange.getSchlangengliedmenge());
                Schlange schlangeKopie = new Schlange();
                schlangeKopie.setSchlangenart(schlange.getSchlangenart());
                schlangeKopie.setSchlangengliedmenge(schlangeGliedKopie);
                loesungSchlangen.add(schlangeKopie);
            }
        }

        // startZeit initialisieren
        if (getStartZeit() == 0) {
            setStartZeit(System.currentTimeMillis());
        }

        long currentZeit = System.currentTimeMillis();
        // Umrechnung der ZeitInterval
        double zeitIntervalUmgerechnet = zeitUmrechunung(problemInstanz.getZeit().getEinheit(), currentZeit - getStartZeit());
        // Zeitvorgabe erreicht, beende Suche und gebe Lösung zurück
        if (zeitIntervalUmgerechnet >= problemInstanz.getZeit().getVorgabe()) {
            problemInstanz.getZeit().setAbgabe(zeitIntervalUmgerechnet);
            System.out.println("Bei der Schlangesuche wird Zeitvorgabe erreicht.");
            flag = true;
            return;
        }

        // erzeuge zulässige Startfelder
        List<Feld> felder = schlangenjagd.getDschungel().getFelder();
        // weist jeder feld in felder auf Dschungel zu
        for (Feld feld : felder) {
            feld.setDschungel(schlangenjagd.getDschungel());
        }
        // Schlangenkopfmenge für alle Schlangenarten erstellen zur filtern der startFelder
        String zeichenketteSchlangenkopf = new String();
        for (Schlangenart schlangenart : schlangenjagd.getSchlangenarten()) {
            zeichenketteSchlangenkopf += schlangenart.getZeichenkette().charAt(0);;
        }
        List<Feld> startFelder = erzeugZulaessigeStartFelder(felder, zeichenketteSchlangenkopf);
        List<Schlangenart> schlangenarten = schlangenjagd.getSchlangenarten();

        if (!startFelder.isEmpty()) {
            // priorisiere und sortiere zulässige Startfelder
            priorisieren(startFelder);

            for (Feld feld : startFelder) {
                // bestimme zulässige Schlangenarten für Startfeld
                List<Schlangenart> startSchlangenarten = erzeugZulassigeSchlangenart(schlangenarten, feld);
                if (!startSchlangenarten.isEmpty()) {
                    // priorisiere und sortiere zulässige Schlangenarten
                    priorisieren(startSchlangenarten);

                    for (Schlangenart schlangenart : startSchlangenarten) {
                        // erzeuge neue SchlangeList mit Schlangenkopf für Schlangenart
                        Schlangenglied schlangenkopf = new Schlangenglied();
                        //List<Schlange> schlangeList = new ArrayList<>();
                        // setze Schlangenkopf auf Startfeld
                        schlangenkopf.setFeld(feld);
                        feld.setVerwendbarkeit(feld.getVerwendbarkeit() - 1);
                        // erzeuge neue Schlange mit Schlangenkopf
                        Schlange schlange = new Schlange();
                        schlangenkopf.setSchlange(schlange);
                        // erstellt neue schlangengliedList Schlange und setzt schlangenkopf in schlangengliedList
                        List<Schlangenglied> schlangengliedList = new ArrayList<>();
                        schlangengliedList.add(schlangenkopf);
                        // weist schlangengliedList und Schlangenart zu Schlange
                        schlange.setSchlangengliedmenge(schlangengliedList);
                        schlange.setSchlangenart(schlangenart);
                        schlangeList.add(schlange);
                        // Rekursive Suchen nach Schlangenglieden anhand Schlangenkopf
                        sucheSchlangenglied(schlangenkopf);
                        // entferne Schlangenkopf und Schlange
                        schlangenkopf.getFeld().setVerwendbarkeit(schlangenkopf.getFeld().getVerwendbarkeit() + 1);
                        schlangeList.remove(schlange);
                        // Rückgabe wenn Zeitvorgabe erreicht ist
                        if (flag == true) {
                            return;
                        }
                    }
                }

            }
        }

        // Wenn alle Lösungen gefunden sind, speichert die Lösungen und gibt zurück

        return;

    }
    /**
     * Suche nach nächstes Schlangenglied anhand vorherigesGlied und Schlangenart
     * @param voherigesGlied letztes Schlangenglied aus schlangengliedList
     * @throws Exception
     */
    public void sucheSchlangenglied (Schlangenglied vorherigesGlied) throws Exception {

        // Wenn vorherigesGlied letztes Schlangenglied ist
        if (vorherigesGlied.getSchlange().getSchlangengliedmenge().size() == vorherigesGlied.getSchlange().getSchlangenart().getZeichenkette().length()) {
            sucheSchlange();
            return;
        }

        // erzeuge zulässige Nachbarfelder für vorherigesGlied
        List <Feld> nachbarFelder = erzeugZulaessigeNachbarFelder(vorherigesGlied);

        // priorisiere und sortiere zulässige Nachbarfelder
        priorisieren(nachbarFelder);

        for (Feld nachbarFeld: nachbarFelder) {
            Schlangenglied nachbarSchlangenglied = new Schlangenglied();
            // setze Schlangenglied auf Nachbarfeld
            nachbarSchlangenglied.setFeld(nachbarFeld);
            // Verwendbarkeit für jede verwendete Feld - 1
            nachbarFeld.setVerwendbarkeit(nachbarFeld.getVerwendbarkeit() - 1);
            // nachbarGlied zur Schlange zuordnen
            nachbarSchlangenglied.setSchlange(vorherigesGlied.getSchlange());
            vorherigesGlied.getSchlange().getSchlangengliedmenge().add(nachbarSchlangenglied);
            sucheSchlangenglied(nachbarSchlangenglied);

            // Wenn Schleife in eine Sackgasse geraten ist, löscht aktuelle Schlangenglied und sucht weiter

            nachbarFeld.setVerwendbarkeit(nachbarFeld.getVerwendbarkeit() + 1);
            vorherigesGlied.getSchlange().getSchlangengliedmenge().remove(nachbarSchlangenglied);

            // Rückgabe wenn Zeitvorgabe erreicht ist
            if (flag == true) {
                return;
            }
        }
    }
    /**
     * Erzeugen zulässige Startfelder nach Kriterien: Verwendbarkeit > 0
     * @param felder ursprüngliche Felder aus Duschungel
     * @param zulässige zeichenkette
     * @return Startfelder zulässige Startfelder zur Schlangen Suchen
     */
    public List<Feld> erzeugZulaessigeStartFelder (List<Feld> felder, String zeichenkette) {

        List<Feld> Startfelder = new ArrayList<>();
        for (Feld feld : felder) {
            if (feld.getVerwendbarkeit() > 0 && zeichenkette.contains(feld.getZeichen())) {
                Startfelder.add(feld);
            }
        }
        return Startfelder;

    }
    /**
     * Erzeugen zulässige Schlangenarten nach zeichen in Feld
     * @param schlangenarten List von Schlangenarten
     * @param feld aktuelle zulässige Feld
     * @return List von zulässigen Schlangenarten
     */

    public List<Schlangenart> erzeugZulassigeSchlangenart (List<Schlangenart> schlangenarten, Feld feld){
        List<Schlangenart> startSchlangenarten = new ArrayList<>();
        boolean flag_1 = true;
        String str = new String();
        for (Feld fd : feld.getDschungel().getFelder()) {
            if (fd.getVerwendbarkeit() > 0) {
                str += fd.getZeichen();
            }
        }
        for (Schlangenart schlangenart : schlangenarten) {
            String firstZeichen = String.valueOf(schlangenart.getZeichenkette().charAt(0));

            if (feld.getZeichen().equals(firstZeichen)) {
                // prüft, ob es noch Felder vorhanden sind, die für Schlangenarten mit entsprechender Zeichenkette nutzbar sind.
                for (int i = 0; i <schlangenart.getZeichenkette().length(); i++) {
                    if (!str.contains(String.valueOf(schlangenart.getZeichenkette().charAt(i)))) {
                        flag_1 = false;
                        break;
                    }
                }
                if (flag_1 == true) {
                    startSchlangenarten.add(schlangenart);
                }
            }
        }
        return startSchlangenarten;
    }

    /**
     * Erzeugen zulässige NachbarschaftFelder
     * @param voherigesGlied letzte Schlangenglied in SchlangengliedList 
     * @return zulässige NachbarschaftFelder
     */

    public List<Feld> erzeugZulaessigeNachbarFelder (Schlangenglied vorherigesGlied) {

        int feldSpalte = vorherigesGlied.getFeld().getSpalte();
        int feldZeile = vorherigesGlied.getFeld().getZeile();
        // index von vorherigesGlied in SchlangengliedList
        int index = vorherigesGlied.getSchlange().getSchlangengliedmenge().indexOf(vorherigesGlied);
        String zeichen = String.valueOf(vorherigesGlied.getSchlange().getSchlangenart().getZeichenkette().charAt(index + 1));
        Schlangenart schlangenart = vorherigesGlied.getSchlange().getSchlangenart();
        List <Feld> startfelder = erzeugZulaessigeStartFelder(vorherigesGlied.getFeld().getDschungel().getFelder(), zeichen);
        List<Feld> nachbarfelder = new ArrayList<>();

        // prüft ob vorherigesGlied letzte Glied von Schlangenart ist
        if (index < schlangenart.getZeichenkette().length() - 1) {

            String nachbarTyp = schlangenart.getNachStr().getTyp();
            List<Parameter> parameters = schlangenart.getNachStr().getParameters();

            for (Feld nachbarFeld : startfelder) {
                if (nachbarFeld.getVerwendbarkeit() > 0) {
                    if (nachbarTyp.equals("Distanz")) {
                        // Es gibt nur ein Parameter für Distanz Typ
                        int parameter = parameters.get(0).getWert();
                        if (Math.abs(nachbarFeld.getSpalte() - feldSpalte) <= parameter && Math.abs(nachbarFeld.getZeile() - feldZeile) <= parameter) {
                            // Ein Feld liegt nicht in seiner eigenen Nachbarschaft
                            if (nachbarFeld.getSpalte() == feldSpalte && nachbarFeld.getZeile() == feldZeile) {}
                            else {
                                nachbarfelder.add(nachbarFeld);
                            }
                        }
                    }
                    else {
                        // Es gibt zwei Parametern für Sprung Typ
                        int parameter_1 = parameters.get(0).getWert();
                        int parameter_2 = parameters.get(1).getWert();

                        if ((Math.abs(nachbarFeld.getSpalte() - feldSpalte) == parameter_1 && Math.abs(nachbarFeld.getZeile() - feldZeile) == parameter_2) || (Math.abs(nachbarFeld.getSpalte() - feldSpalte) == parameter_2 && Math.abs(nachbarFeld.getZeile() - feldZeile) == parameter_1)) {
                            nachbarfelder.add(nachbarFeld);
                        }
                    }
                }
            }
        }

        return nachbarfelder;

    }

    /**
     * Priorisieren die List von Feld und Schlangenart nach 1. Punkte 2. Verwendbarkeit
     * @param <T> Element in List
     * @param list priorisierte List
     */
    public <T extends Priorisierbar> void priorisieren(List<T> list) {
        Comparator<T> comparator_1 = new Comparator<T>() {
            @Override
            public int compare(T o1, T o2) {
                Integer punkt1 = o1.getPunkte();
                Integer punkt2 = o2.getPunkte();
                return punkt2.compareTo(punkt1);
            }
        };

        Collections.sort(list, comparator_1);
    }
    /**
     * Umrechnung der Zeit anhand einheit
     * @param einheit vorgegebenen Zeiteinheit
     * @param inputZeit 
     * @return umgerechneteZeit
     */
    public static double zeitUmrechunung(String einheit, double inputZeit) {
        double result = 0;
        if (einheit.equals("d")) {
            // Nachkommestelle bis 9
            result = Math.round((double) inputZeit / 86400000 * 1e9) / 1e9;
        }
        else if(einheit.equals("h")) {
            result = Math.round((double) inputZeit / 3600000 * 1e9) / 1e9;
        }
        else if(einheit.equals("min")) {
            result = Math.round((double) inputZeit / 60000 * 1e9) / 1e9;
        }
        else if(einheit.equals("s")) {
            result = Math.round((double) inputZeit / 1000 * 1e9) / 1e9;
        }
        else {
            result = (double) inputZeit;
        }

        return result;
    }

    /**
     * Rechnen gesamte bisherige Punktzahl von Schlangen in Schlangenjagd 
     * @param schlangenjagd aktuelle Schlangenjagd Modelle
     * @param bisherigePunkt
     * @return GesamtPunktzahl aktuelle 
     */
    public static int rechnePunkt(List<Schlange> schlangen) {
        // Rechne aktuelle gesamte Punktzahl aus Schlangen
        int Punktzahl = 0;
        if (schlangen != null) {
            for (Schlange schlange : schlangen) {
                int schlangenartPunkt = schlange.getSchlangenart().getPunkte();
                List<Schlangenglied> schlangenglieden = schlange.getSchlangengliedmenge();
                int summenFeldPunkt = 0;
                for (Schlangenglied schlangengiled : schlangenglieden) {
                    summenFeldPunkt += schlangengiled.getFeld().getPunkte();
                }
                int schlangePunkt = schlangenartPunkt + summenFeldPunkt;
                Punktzahl += schlangePunkt;
            }
        }

        return Punktzahl;
    }
    public long getStartZeit() {
        return startZeit;
    }
    public void setStartZeit(long startZeit) {
        this.startZeit = startZeit;
    }
    public List<Schlange> getLoesungSchlangen() {
        return loesungSchlangen;
    }
    public void setLoesungSchlangen(List<Schlange> loesungSchlangen) {
        this.loesungSchlangen = loesungSchlangen;
    }
    public Schlangenjagd getProblemInstanz() {
        return problemInstanz;
    }
    public void setProblemInstanz(Schlangenjagd problemInstanz) {
        this.problemInstanz = problemInstanz;
    }
    public Schlangenjagd getSchlangenjagd() {
        return schlangenjagd;
    }
    public void setSchlangenjagd(Schlangenjagd schlangenjagd) {
        this.schlangenjagd = schlangenjagd;
    }

}
