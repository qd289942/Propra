package de.fernuni.kurs01584.ss23.darstellung;

import java.util.ArrayList;
import java.util.List;

import de.fernuni.kurs01584.ss23.dateiverarbeitung.DateneinlesenmitSchlangen;
import de.fernuni.kurs01584.ss23.modell.*;

public class DarstellungLoesungen {
    public static void main(String[] args) throws Exception {
        String filePath = "C:\\Users\\Kuan\\Desktop\\Probleminstanzen2\\sj_p6_loesung.xml";
        // String filePath = "C:\\Users\\Kuan\\Desktop\\Practice\\programming_practice\\dataoutput.xml";
        Schlangenjagd schlangenjagd = DateneinlesenmitSchlangen.parseXMLmitSchlangen(filePath);
        
        //System.out.println("ÄÖÜ");
        System.out.println("Lösungen: ");
        List<Schlangenart> schlangenarten = schlangenjagd.getSchlangenarten();
        
        for (Schlangenart schlangenart : schlangenarten) {
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
        int aktuelleZeile = felder.get(0).getZeile();
        for (Feld feld : felder) {
            if (feld.getZeile() == aktuelleZeile) {
                if (feld.getVerwendbarkeit() == 0) {
                    System.out.print(".");
                }
                else {
                    boolean flag = false;
                    for (List<String> gliedfelderIdList : schlangengliedfelderIdList) {
                        if (gliedfelderIdList.contains(feld.getId())) {
                            flag = true;
                            break;
                        }
                    }
                    if (flag == true) {
                        System.out.print(highlightEscapeSequence + feld.getZeichen() + resetStyleEscapeSequence);
                    }
                    else {
                        System.out.print(feld.getZeichen());
                    }
                }
            }
            else {
                System.out.println();
                if (feld.getVerwendbarkeit() == 0) {
                    System.out.println(".");
                }
                else {
                    boolean flag = false;
                    for (List<String> gliedfelderIdList : schlangengliedfelderIdList) {
                        if (gliedfelderIdList.contains(feld.getId())) {
                            flag = true;
                            break;
                        }
                    }
                    if (flag == true) {
                        System.out.print(highlightEscapeSequence + feld.getZeichen() + resetStyleEscapeSequence);
                    }
                    else {
                        System.out.print(feld.getZeichen());
                    }
                }
                aktuelleZeile++;
            }

        }
        //Zeile und Spalte der aufeinanderfolgenden Schlangenglieder

        System.out.println("\n\nSchlangenglieder (Zeile, Spalte): ");
        
        int schlangecount = 1;
        for (Schlange schlange :  schlangen) {
            System.out.print("\nSchlange_" + schlangecount +" : ");
            for (Schlangenglied schlangeglied : schlange.getSchlangengliedmenge()) {
                System.out.print("(" + schlangeglied.getFeld().getZeile() + "," + schlangeglied.getFeld().getSpalte() + "); ");
            }
            schlangecount++;
        }
    }
}
