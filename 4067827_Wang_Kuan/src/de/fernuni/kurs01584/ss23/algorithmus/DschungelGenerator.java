package de.fernuni.kurs01584.ss23.algorithmus;

import java.util.*;

import de.fernuni.kurs01584.ss23.dateiverarbeitung.DatenausgabeXML;
import de.fernuni.kurs01584.ss23.dateiverarbeitung.DateneingabeXML;
import de.fernuni.kurs01584.ss23.modell.*;

public class DschungelGenerator {
    public static boolean erzeugProbleminstanzen(String xmlEingabedatei, String xmlAusgabedatei) throws Exception {
        // laden der vorgegebenen Probleminstanz
        Schlangenjagd problemInstanz = DateneingabeXML.parseXML(xmlEingabedatei);
        // Prüfen die Vollständigkeit der Daten
        try {
            if (!checkProbleminszanz(problemInstanz)) {
                System.out.println("Pflichtdaten fehlen in vorgegebene Probleminstanz.");
                return false;
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // Vervollständigung der Probleminstanz mit Hilfe des Dschungelgenerators

        // Prüfen die Vorgabe der Zeitlimit und Default Wert einstellen
        if (problemInstanz.getZeit().getVorgabe() == 0.0 || problemInstanz.getZeit().getEinheit() == null) {
            problemInstanz.getZeit().setEinheit("ms");
            problemInstanz.getZeit().setVorgabe(20000.0);
        }

        // Erzeugung eines Dschungels entsprechend der gegebenen Vorgaben
        if (problemInstanz.getDschungel().getFelder() == null || problemInstanz.getDschungel().getFelder().isEmpty()) {}
        else {
            // löschen bereits vorhandene Dschungelfelder in problemInstanz und neue Felder erstellen
            List <Feld> newFelder = new ArrayList<>();
            for (int FeldId = 0; FeldId < problemInstanz.getDschungel().getSpalten() * problemInstanz.getDschungel().getZeilen(); FeldId++) {
                Feld feld = new Feld();
                feld.setId("F" + FeldId);
                feld.setSpalte(FeldId % problemInstanz.getDschungel().getSpalten());
                feld.setZeile(FeldId / problemInstanz.getDschungel().getZeilen());
                feld.setVerwendbarkeit(1);
                feld.setPunkte(1);
                newFelder.add(feld);
            }
            problemInstanz.getDschungel().setFelder(newFelder);

            List <Schlangenart> schlangenartList = problemInstanz.getSchlangenarten();
            List <Schlangenart> failedTofind = new ArrayList<>();
            for (Schlangenart schlangenart: schlangenartList) {
                while (schlangenart.getVerwendbarkeit() > 0) {
                    // Zeitvorgabe überprüfen

                    String zeichenkette = schlangenart.getZeichenkette();
                    List<Schlangenglied> schlangengliedList = new ArrayList<>();
                    Schlangenglied startGlied = new Schlangenglied();

                    // Erzeugen des zulaessigeFelders
                    List<Feld> zulaessigesFelder = erzeugZulaessigeFelder(newFelder);
                    if (zulaessigesFelder.isEmpty()) {
                        System.out.println("keine zulaessigesFelder mehr für Schlangenarten. Der Vorgang muss abgebrochen werden.");
                        return false;
                    }
                    // StartFeld auswählen
                    char zeichen = zeichenkette.charAt(0);
                    setZufaelligFeldmitZeichen(zeichen,zulaessigesFelder,startGlied);
                    schlangengliedList.add(startGlied);

                    // suche Schlangenglied nach startGlied und Schlangenart
                    sucheGliednachSchlangenart(schlangenart,zulaessigesFelder,schlangengliedList,startGlied);

                    // keine vollständige Schlange angelegt
                    if (schlangengliedList.size() != zeichenkette.length()) {
                        schlangenart.setAnzahl(schlangenart.getVerwendbarkeit() - 1);
                        failedTofind.add(schlangenart);
                    }


                }

            }


            // alle Schlangenarten sind in der gewünschten Anzahl im Dschungel vorhanden, soll verbleibenden leeren Felder mit zufällige Zeichen in Zeichenmenge des Dschungels belegt werden
            List<Feld> leerFelder = new ArrayList<>();
            for (Feld feld: problemInstanz.getDschungel().getFelder()) {                
                if (feld.getZeichen() == null) {
                    // Erstellung eine randomgenerator
                    leerFelder.add(feld);
                }
            }

            for (Feld feld: leerFelder) {
                Random random = new Random();
                int randomIndex = random.nextInt(problemInstanz.getDschungel().getzeichenmenge().length());
                char zeichen = problemInstanz.getDschungel().getzeichenmenge().charAt(randomIndex);
                String zeichenStr = String.valueOf(zeichen);
                feld.setZeichen(zeichenStr);
            }
            
            if (failedTofind.isEmpty()) {
                System.out.println("Alle Schlangenarten sind in der gewünschten Anzahl im Dschungel vorhanden");
            }
            else {
                // Erstellen ein HashMap zur Speichern nicht angelegte Schlangenart
                Map<String, Integer> countMap = new HashMap<>();
                for (Schlangenart schlangenart : failedTofind) {
                    String schlangenartId = schlangenart.getId();
                    countMap.put(schlangenartId, countMap.getOrDefault(schlangenartId, 0) + 1);
                }


                System.out.println("unangelegte Anzahl von Schlangenart: ");
                for (Map.Entry<String, Integer>entry: countMap.entrySet()) {
                    System.out.println(entry.getKey() + ": " + entry.getValue() + "\n");
                }
            }
            

        }
        
        // Eingabedatei nochmal laden zur Speichern ursprungliche Anzahl von Schlangenarten
        Schlangenjagd Ausgangsdaten = DateneingabeXML.parseXML(xmlEingabedatei);
        DatenausgabeXML.writeProbleminstanzXML(Ausgangsdaten,problemInstanz, xmlAusgabedatei);
        return true;


    }

    private static boolean checkProbleminszanz(Schlangenjagd problemInstanz) {
        if (problemInstanz.getDschungel().getZeilen() == 0 || problemInstanz.getDschungel().getSpalten() == 0) {
            // Zeilen oder Spalten Anzahlen sind nicht definiert oder initialisiert
            System.out.println("Anzahl der Zeilen und Spalten des Dschungels sind nicht vollständig in Eingabedatei angegeben.");
            return false;
        }
        if (problemInstanz.getDschungel().getzeichenmenge() == null) {
            System.out.println("keine zulässige Zeichen wird für den Dschungel angegeben.");
            return false;
        }
        if (problemInstanz.getSchlangenarten() == null) {
            System.out.println("keine zulässige Schlangenarten werden für den Dschungel angegeben.");
            return false;
        }
        else {
            List <Schlangenart> schlangenarten = new ArrayList<>();
            for (Schlangenart schlangenart : schlangenarten) {
                // Anzahl von Schlangenart sind nicht definiert oder initialisiert
                if (schlangenart.getVerwendbarkeit() == 0) {
                    System.out.println("keine erwünschte Anzahl von Schlangen wird für Schlangenart " + schlangenart.getId() + " angegeben.");
                    return false;
                }
            }
        }

        return true;
    }

    private static void sucheGliednachSchlangenart(Schlangenart schlangenart, List<Feld> newFelder, List<Schlangenglied> schlangengliedList, Schlangenglied vorherigesGlied) {
        String zeichenkette = schlangenart.getZeichenkette();
        // erzeuge zulässige Nachbarfelder für vorherigesGlied
        int indexVorherigesGlied = schlangengliedList.indexOf(vorherigesGlied);
        // prüfen, ob die vollsändige Schlange angelegt ist
        if (schlangengliedList.size() == zeichenkette.length()) {
            schlangenart.setAnzahl(schlangenart.getVerwendbarkeit() - 1);
            return;
        }
        else {
            char aktuellezeichen = zeichenkette.charAt(indexVorherigesGlied + 1);
            Schlangenglied schlangenGlied = new Schlangenglied();
            // Erzeugen des zulaessigeFelders
            List<Feld> zulaessigesFelder = erzeugZulaessigeFelder(newFelder);
            // Erzeugen des Nachbarfelders
            List<Feld> nachbarFelder = erzeugNachbarFeld(zulaessigesFelder, vorherigesGlied, schlangenart);

            // keine zulässige Nachbarfelder gefunden
            if (nachbarFelder.isEmpty()) {
                if (schlangengliedList.size() == zeichenkette.length()) {
                    // vollständige Schlange nach Schlangenart gefunden
                    schlangenart.setAnzahl(schlangenart.getVerwendbarkeit() - 1);
                }
                else {
                    // keine vollständige Schlange gefunden, Schlangenglieder müssen zurückgenommen werden
                    schlangengliedList.remove(schlangengliedList.size() - 1);
                    vorherigesGlied.getFeld().setZeichen(null);
                }
                return;
            }

            else {
                mischenFeldRandom(nachbarFelder);
                erzeugZulaessigeFelder(nachbarFelder);
                for (Feld feld: nachbarFelder) {
                    String zeichenStr = String.valueOf(aktuellezeichen);
                    feld.setZeichen(zeichenStr);
                    schlangenGlied.setFeld(feld);
                    schlangengliedList.add(schlangenGlied);
                    sucheGliednachSchlangenart(schlangenart, zulaessigesFelder,schlangengliedList, schlangenGlied);
                    // Falls vollständige Schlange nach Schlangenart gefunden ist, bricht die Schleife ab
                    if (schlangengliedList.size() == zeichenkette.length()) {
                        break;
                    }
                }
            }
        }
        return;

    }

    private static List<Feld> erzeugZulaessigeFelder(List<Feld> felder) {
        List<Feld> freiFeld = new ArrayList<>();
        for (Feld feld: felder) {
            if (feld.getZeichen() == null) {
                freiFeld.add(feld);
            }
        }

        return freiFeld;
    }

    private static void setZufaelligFeldmitZeichen(char zeichen, List<Feld>felder, Schlangenglied schlangenGlied) {
        String zeichenStr = String.valueOf(zeichen);
        // Erstellung eine randomgenerator
        Random random = new Random();
        int randomIndex = random.nextInt(felder.size());
        Feld feld = felder.get(randomIndex);
        feld.setZeichen(zeichenStr);
        schlangenGlied.setFeld(feld);
    }

    private static List<Feld> erzeugNachbarFeld(List<Feld> felder, Schlangenglied vorherigesGlied, Schlangenart schlangenart){
        List<Feld> nachbarFeld = new ArrayList<>();
        Feld vorherigesGliedFeld = vorherigesGlied.getFeld();
        for (Feld schlangenGliedFeld: felder) {
            if (checkNachbarfeld(vorherigesGliedFeld, schlangenGliedFeld, schlangenart)) {
                nachbarFeld.add(schlangenGliedFeld);
            }
        }

        return nachbarFeld;
    }

    private static boolean checkNachbarfeld(Feld vorherigesFeld, Feld aktuellesFeld, Schlangenart schlangenart){
        String nachbarTyp = schlangenart.getNachStr().getTyp();
        List<Parameter> parameters = schlangenart.getNachStr().getParameters();
        if (nachbarTyp.equals("Distanz")) {
            int parameter = parameters.get(0).getWert();
            if (Math.abs(vorherigesFeld.getSpalte() - aktuellesFeld.getSpalte()) <= parameter && Math.abs(vorherigesFeld.getZeile() - aktuellesFeld.getZeile()) <= Math.abs(vorherigesFeld.getSpalte() - aktuellesFeld.getSpalte())) {
                return true;
            }
        }
        else {
            // Es gibt zwei Parametern für Sprung Typ
            int parameter_1 = parameters.get(0).getWert();
            int parameter_2 = parameters.get(1).getWert();

            if ((Math.abs(vorherigesFeld.getSpalte() - aktuellesFeld.getSpalte()) == parameter_1 && Math.abs(vorherigesFeld.getZeile() - aktuellesFeld.getZeile()) == parameter_2) || (Math.abs(vorherigesFeld.getSpalte() - aktuellesFeld.getSpalte()) == parameter_2 && Math.abs(vorherigesFeld.getZeile() - aktuellesFeld.getZeile()) == parameter_1)) {
                return true;
            }
        }

        return false;
    }

    private static <T> void mischenFeldRandom(List<T> felder){
        Collections.shuffle(felder);
    }


}
