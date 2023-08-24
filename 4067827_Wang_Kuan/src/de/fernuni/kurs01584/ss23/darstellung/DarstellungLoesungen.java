package de.fernuni.kurs01584.ss23.darstellung;

import java.util.ArrayList;
import java.util.List;

import de.fernuni.kurs01584.ss23.modell.*;

/**
 * Klass zur Darstellung des Probleminstanzs aus vorgegebenen Probleminstanz und der Lösung aus Datenausgabe
 */
public class DarstellungLoesungen {
	/**
	 * Darstellung der Lösung aus SchlangenSuche-Algorithmus
	 * @param schlangenjagd vollständige Lösung gerechnete durch SchlangenSuche
	 * @throws Exception
	 */
    public static void loesungDarstellen(Schlangenjagd schlangenjagd) throws Exception {
        System.out.print("\nLösungen: \nverwendete Schlangenarten: \n");
        
        List<Schlangenart> genutztSchlangenarten = new ArrayList<>();

        for (Schlange schlange : schlangenjagd.getSchlangen()) {
            if (!genutztSchlangenarten.contains(schlange.getSchlangenart())) {
                genutztSchlangenarten.add(schlange.getSchlangenart());
            }
        }
        for (Schlangenart schlangenart : genutztSchlangenarten) {
            System.out.print(schlangenart.getId() + ": Zeichenkette: " + schlangenart.getZeichenkette() + "; Nachbarschaftsstruktur: " + schlangenart.getNachStr().getTyp() + '\n');
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


        System.out.print("\nZeichendschungel: \n");
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
                    System.out.print("\n");
                    aktuelleZeile++;
                }
                if (gliedfelderIdList.contains(feld.getId())) {
                    System.out.printf(highlightEscapeSequence + feld.getZeichen() + resetStyleEscapeSequence);
                }
                else {
                    System.out.printf(".");
                }

            }
            System.out.print("\n");
            schlangecount++;
        }

        //Zeile und Spalte der aufeinanderfolgenden Schlangenglieder

        System.out.print("\n\nSchlangenglieder (Zeile, Spalte): \n");

        schlangecount = 1;
        for (Schlange schlange :  schlangen) {
            System.out.print("\nSchlange_" + schlangecount +" : ");
            for (Schlangenglied schlangeglied : schlange.getSchlangengliedmenge()) {
                System.out.print("(" + schlangeglied.getFeld().getZeile() + "," + schlangeglied.getFeld().getSpalte() + "); ");
            }
            schlangecount++;
        }
    }
    /**
	 * Darstellung des Probleminstanzs aus vorgegebener Dateneingabe
	 * @param schlangenjagd vollständiger Probleminstanz aus vorgegebener Dateneingabe 
	 * @throws Exception
     */
    public static void probleminstanzDarstellen(Schlangenjagd schlangenjagd) throws Exception {

        //System.out.println("ÄÖÜ");
        System.out.print("Probleminstanzen: \n");
        Dschungel dschungel = schlangenjagd.getDschungel();
        int zeilen = dschungel.getZeilen();
        int spalten = dschungel.getSpalten();
        String zeichenmenge = dschungel.getzeichenmenge();
        List<Feld> felder = dschungel.getFelder();


        // Darstellung der Anzahl der Zeilen und Spalten des Dschungels sowie Zeichenmenge
        System.out.print("Anzahl der Zeilen des Dschungels: " + zeilen + "\n");
        System.out.print("Anzahl der Spalten des Dschungels: " + spalten + "\n");
        System.out.print("Dschungel verwendeten Zeichen: " + zeichenmenge + "\n\n");
        // Darstellung der Schlangenarten
        System.out.print("vorhandene Schlangenarten: " + "\n");
        List<Schlangenart> schlangenarten = schlangenjagd.getSchlangenarten();

        for (Schlangenart schlangenart : schlangenarten) {
            System.out.print(schlangenart.getId() + ": Zeichenkette: " + schlangenart.getZeichenkette() + "; Nachbarschaftsstruktur: " + schlangenart.getNachStr().getTyp() + "; Punkten: " + schlangenart.getPunkte() + "; Anzahl: " + schlangenart.getVerwendbarkeit() + "\n");
        }

        System.out.print("\n");
        // Darstellung der Dschungelfelder
        System.out.print("Dschungelfelder sind folgend dargestellt: \n");


        for (Feld feld : felder) {
            System.out.print(feld.getId() + ": " + "Zeichen: " + feld.getZeichen() + "; Verwendbarkeit: " + feld.getVerwendbarkeit() + "; Punkten: " + feld.getPunkte() + "\n");
        }
    }
}

