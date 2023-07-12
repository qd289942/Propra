package de.fernuni.kurs01584.ss23.algorithmus;

import java.util.*;

import de.fernuni.kurs01584.ss23.dateiverarbeitung.DateneingabeXML;
import de.fernuni.kurs01584.ss23.modell.*;

public class DschungelGenerator {
    public boolean erzeugProbleminstanzen(String xmlEingabedatei, String xmlAusgabedatei) throws Exception {
        // laden der vorgegebenen Probleminstanz
        Schlangenjagd problemInstanz = DateneingabeXML.parseXML(xmlEingabedatei);
        // Prüfen die Vollständigkeit der Daten
        if (!checkProbleminszanz(problemInstanz)) {
            System.out.println("Pflichtdaten fehlen in vorgegebene Probleminstanz.");
            return false;
        }

        // Vervollständigung der Probleminstanz mit Hilfe des Dschungelgenerators

        // Prüfen die Vorgabe der Zeitlimit und Default Wert einstellen
        if (problemInstanz.getZeit().getVorgabe() == 0.0 || problemInstanz.getZeit().getEinheit().equals(null)) {
            problemInstanz.getZeit().setEinheit("ms");
            problemInstanz.getZeit().setVorgabe(20000.0);
        }

        // Erzeugung eines Dschungels entsprechend der gegebenen Vorgaben
        if (problemInstanz.getDschungel().getFelder().equals(null) || problemInstanz.getDschungel().getFelder().isEmpty()) {}
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

            List <Schlangenart> schlangenartList = new ArrayList<>();
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

                }

            }
            // alle Schlangenarten sind in der gewünschten Anzahl im Dschungel vorhanden, soll verbleibenden leeren Felder mit zufällige Zeichen in Zeichenmenge des Dschungels belegt werden
            List<Feld> leerFelder = new ArrayList<>();
            for (Feld feld: problemInstanz.getDschungel().getFelder()) {                
                if (feld.getZeichen().equals(null)) {
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
            
            
        }

        return true;
    }

    private boolean checkProbleminszanz(Schlangenjagd problemInstanz) {
        if (problemInstanz.getDschungel().getZeilen() == 0 || problemInstanz.getDschungel().getSpalten() == 0) {
            // Zeilen oder Spalten Anzahlen sind nicht definiert oder initialisiert
            System.out.println("Anzahl der Zeilen und Spalten des Dschungels sind nicht vollständig in Eingabedatei angegeben.");
            return false;
        }
        if (problemInstanz.getDschungel().getzeichenmenge().equals(null)) {
            System.out.println("keine zulässige Zeichen wird für den Dschungel angegeben.");
            return false;
        }
        if (problemInstanz.getSchlangenarten().equals(null)) {
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
    
    private void sucheGliednachSchlangenart(Schlangenart schlangenart, List<Feld> newFelder, List<Schlangenglied> schlangengliedList, Schlangenglied vorherigesGlied) {
        String zeichenkette = schlangenart.getZeichenkette();
        // erzeuge zulässige Nachbarfelder für vorherigesGlied
        int indexVorherigesGlied = schlangengliedList.indexOf(vorherigesGlied);
        char aktuellezeichen = zeichenkette.charAt(indexVorherigesGlied);
        Schlangenglied schlangenGlied = new Schlangenglied();
        // Erzeugen des zulaessigeFelders
        List<Feld> zulaessigesFelder = erzeugZulaessigeFelder(newFelder);
        // Erzeugen des Nachbarfelders
        List<Feld> nachbarFelder = erzeugNachbarFeld(zulaessigesFelder, vorherigesGlied, schlangenGlied, schlangenart);
        
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
        return;
        
    }
    
    private List<Feld> erzeugZulaessigeFelder(List<Feld> felder) {
        List<Feld> freiFeld = new ArrayList<>();
        for (Feld feld: felder) {
            if (feld.getZeichen().equals(null)) {
                freiFeld.add(feld);
            }
        }

        return freiFeld;
    }

    private void setZufaelligFeldmitZeichen(char zeichen, List<Feld>felder, Schlangenglied schlangenGlied) {
        String zeichenStr = String.valueOf(zeichen);
        // Erstellung eine randomgenerator
        Random random = new Random();
        int randomIndex = random.nextInt(felder.size());
        Feld feld = felder.get(randomIndex);
        feld.setZeichen(zeichenStr);
        schlangenGlied.setFeld(feld);
    }

    private List<Feld> erzeugNachbarFeld(List<Feld> felder, Schlangenglied vorherigesGlied, Schlangenglied schlangenGlied, Schlangenart schlangenart){
        List<Feld> nachbarFeld = new ArrayList<>();
        Feld vorherigesGliedFeld = vorherigesGlied.getFeld();
        Feld schlangenGliedFeld = schlangenGlied.getFeld();
        for (Feld feld: felder) {
            if (checkNachbarfeld(vorherigesGliedFeld, schlangenGliedFeld, schlangenart)) {
                nachbarFeld.add(feld);
            }
        }

        return nachbarFeld;
    }

    private boolean checkNachbarfeld(Feld vorherigesFeld, Feld aktuellesFeld, Schlangenart schlangenart){
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
    
    private <T> void mischenFeldRandom(List<T> felder){
        Collections.shuffle(felder);
    }


}
