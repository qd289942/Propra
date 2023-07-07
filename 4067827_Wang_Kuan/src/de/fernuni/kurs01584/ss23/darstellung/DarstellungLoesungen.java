package de.fernuni.kurs01584.ss23.darstellung;

import java.util.ArrayList;
import java.util.List;

import de.fernuni.kurs01584.ss23.modell.*;

public class DarstellungLoesungen {
    public static void loesungDarstellen(String filePath, Schlangenjagd schlangenjagd) throws Exception {
        System.out.println("\nLösungen: \nverwendete Schlangenarten: \n");
        
        List<Schlangenart> genutztSchlangenarten = new ArrayList<>();

        for (Schlange schlange : schlangenjagd.getSchlangen()) {
            if (!genutztSchlangenarten.contains(schlange.getSchlangenart())) {
                genutztSchlangenarten.add(schlange.getSchlangenart());
            }
        }
        for (Schlangenart schlangenart : genutztSchlangenarten) {
            System.out.println(schlangenart.getId() + ": Zeichenkette: " + schlangenart.getZeichenkette() + "; Nachbarschaftsstruktur: " + schlangenart.getNachStr().getTyp());
        }
        // Hervorhebung der im Zeichendschungel angeordneten Schlange

        List<Schlange> schlangen = schlangenjagd.getSchlangen();
        List<List<String>> schlangengliedfelderIdList = new ArrayList<>();

        for (Schlange schlange :  schlangen) {
            List<Schlangenglied> schlangenglieds = schlange.getSchlangengliedmenge();
            List<String> gliedfelderIdList = new ArrayList<>();
            for (Schlangenglied schlangenglied : schlangenglieds) {
                String FeldId = schlangenglied.getFeld().getId();
                gliedfelderIdList.add(FeldId);
            }
            schlangengliedfelderIdList.add(gliedfelderIdList);
        }


        System.out.println("\nZeichendschungel: ");
        Dschungel dschungel = schlangenjagd.getDschungel();
        List<Feld> felder = dschungel.getFelder();
        String highlightEscapeSequence = "\u001B[31m";
        String resetStyleEscapeSequence = "\u001B[0m";

        // weist the Zeilenummer von erstem Feld zu aktuelleZeile zu
        int schlangecount = 1;
        for (List<String> gliedfelderIdList: schlangengliedfelderIdList) {

            System.out.print("Schlange_" + schlangecount +" : \n");
            int aktuelleZeile = felder.get(0).getZeile();
            for (Feld feld : felder) {
                if (aktuelleZeile != feld.getZeile()) {
                    System.out.println("");
                    aktuelleZeile++;
                }
                if (gliedfelderIdList.contains(feld.getId())) {
                    System.out.printf(highlightEscapeSequence + feld.getZeichen() + resetStyleEscapeSequence);
                }
                else {
                    System.out.printf(".");
                }

            }
            System.out.println();
            schlangecount++;
        }

        //Zeile und Spalte der aufeinanderfolgenden Schlangenglieder

        System.out.println("\n\nSchlangenglieder (Zeile, Spalte): ");

        schlangecount = 1;
        for (Schlange schlange :  schlangen) {
            System.out.print("\nSchlange_" + schlangecount +" : ");
            for (Schlangenglied schlangeglied : schlange.getSchlangengliedmenge()) {
                System.out.print("(" + schlangeglied.getFeld().getZeile() + "," + schlangeglied.getFeld().getSpalte() + "); ");
            }
            schlangecount++;
        }
    }
    public static void probleminstanzDarstellen(String filePath, Schlangenjagd schlangenjagd) throws Exception {

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
            System.out.println(schlangenart.getId() + ": Zeichenkette: " + schlangenart.getZeichenkette() + "; Nachbarschaftsstruktur: " + schlangenart.getNachStr().getTyp() + "; Punkten: " + schlangenart.getPunkte() + "; Anzahl: " + schlangenart.getVerwendbarkeit());
        }

        System.out.println();
        // Darstellung der Dschungelfelder
        System.out.println("Dschungelfelder sind folgend dargestellt: ");


        for (Feld feld : felder) {
            System.out.println(feld.getId() + ": " + "Zeichen: " + feld.getZeichen() + "; Verwendbarkeit: " + feld.getVerwendbarkeit() + "; Punkten: " + feld.getPunkte());
        }
    }
}

