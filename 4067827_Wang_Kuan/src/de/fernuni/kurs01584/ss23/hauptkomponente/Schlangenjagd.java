package de.fernuni.kurs01584.ss23.hauptkomponente;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.jdom2.*;
import org.jdom2.input.SAXBuilder;

import de.fernuni.kurs01584.ss23.algorithmus.SchlangenSuche;
import de.fernuni.kurs01584.ss23.darstellung.DarstellungLoesungen;
import de.fernuni.kurs01584.ss23.dateiverarbeitung.DateneingabeXML;
import de.fernuni.kurs01584.ss23.modell.*;


public class Schlangenjagd implements SchlangenjagdAPI {

    // TODO: Implementierung von Schnittstelle und Programm-Einstieg
    public static void main (String[] args) throws Exception {

        // Kommandozeilenparameter zuweisen
        String ablauf = args[0];
        String eingabe = args[1];
        String ausgabe = args[2];

        // Kommandozeilenparameter überprüfen
        if (args.length != 3) {
            System.out.println("biite nochmal Kommandozeilenparametern angeben!");
            return;
        }
        // Erstellen neue Objekt von Hauptkomponent Schlangenjagd
        Schlangenjagd schlangenjagd = new Schlangenjagd();

        for (char c: ablauf.toCharArray()) {
            switch (c) {
            case 'l':
                boolean flag = schlangenjagd.loeseProbleminstanz(eingabe, ausgabe);
                if (flag == false) {
                    System.out.println("keine Schlange gefunden.");
                }
                break;

                /*case 'e':
                schlangenjagd.loeseProbleminstanz(eingabe, ausgabe);
                break;*/

            case 'd':
                schlangenjagd.darstellung(eingabe, ausgabe);
                break;

            case 'p':
                List<Fehlertyp> fehlerTypList = schlangenjagd.pruefeLoesung(ausgabe);
                System.out.println("\n\nFehlertyp und Anzahl: ");
                if (fehlerTypList.isEmpty()) {
                    System.out.println("keine Fehlern gefunden");
                }
                // Bei Unzulässigkeit werden die Art und Anzahl der verletzten Bedingungen in der Konsole ausgegeben
                Map<Fehlertyp, Long> countMap = fehlerTypList.stream()
                        .collect(Collectors.groupingBy(fehlertyp -> fehlertyp, Collectors.counting()));
                for (Entry<Fehlertyp, Long> entry : countMap.entrySet()) {
                    System.out.println(entry.getKey() + ": " + entry.getValue());
                }
                break;

            case 'b':
                int gesamtPunkte = schlangenjagd.bewerteLoesung(ausgabe);
                System.out.println("\nGesamtPunkt: " + gesamtPunkte);
                break;

            default:
                System.out.println("ungültige ablauf Parameter");
                return;
            }
        }

    }
    @Override
    public boolean loeseProbleminstanz(String xmlEingabeDatei, String xmlAusgabeDatei) {
        // TODO Implementierung der API-Methode zur Loesung von Probleminstanzen.
        // Probleminstanz wird gelöst und in AusgabeDatei gespeichert
        // Erstellen neue Objekt von Modelle Schlangenjagd als Probleminstanz

        de.fernuni.kurs01584.ss23.modell.Schlangenjagd probleminstanz = null;
        if (!validateDTD(xmlEingabeDatei)) {
            return false;
        }

        try {
            probleminstanz = DateneingabeXML.parseXML(xmlEingabeDatei);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try {
            SchlangenSuche.sucheSchlange(probleminstanz, xmlAusgabeDatei);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if (probleminstanz.getSchlangen() != null && !probleminstanz.getSchlangen().isEmpty()) {
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public boolean erzeugeProbleminstanz(String xmlEingabeDatei, String xmlAusgabeDatei) {
        // TODO Implementierung der API-Methode zur Erzeugung von Probleminstanzen.
        if (!validateDTD(xmlEingabeDatei)) {
            return false;
        }
        return false;
    }

    @Override
    public List<Fehlertyp> pruefeLoesung(String xmlEingabeDatei) {
        de.fernuni.kurs01584.ss23.modell.Schlangenjagd loesung = null;
        if (!validateDTD(xmlEingabeDatei)) {
            return null;
        }
        try {
            loesung = DateneingabeXML.parseXMLmitSchlangen(xmlEingabeDatei);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // TODO Implementierung der API-Methode zur Pruefung von Loesungen.

        List<Fehlertyp> fehlertypList = new ArrayList<>();
        List<Schlange> schlangeList = loesung.getSchlangen();
        // prüft jede Schlange und gibt ggf. Fehlertyp in fehlertypList zurück
        for (Schlange schlange : schlangeList) {
            if (schlange.getSchlangengliedmenge().size() != schlange.getSchlangenart().getZeichenkette().length()) {
                fehlertypList.add(Fehlertyp.GLIEDER);
            }

            else {

                for (Schlangenglied schlangenglied: schlange.getSchlangengliedmenge()) {
                    if (!schlangenglied.getFeld().getZeichen().equals(String.valueOf(schlange.getSchlangenart().getZeichenkette().charAt(schlange.getSchlangengliedmenge().indexOf(schlangenglied))))) {
                        fehlertypList.add(Fehlertyp.ZUORDNUNG);
                    }

                    if (schlangenglied.getFeld().getVerwendbarkeit() < 0) {
                        fehlertypList.add(Fehlertyp.VERWENDUNG);
                    }

                    if (schlange.getSchlangengliedmenge().indexOf(schlangenglied) > 0) {
                        int aktuelleIndex = schlange.getSchlangengliedmenge().indexOf(schlangenglied);
                        Schlangenglied vorherigesGlied = schlange.getSchlangengliedmenge().get(aktuelleIndex - 1);
                        String Typ = schlange.getSchlangenart().getNachStr().getTyp();
                        List<Parameter> parameters = schlange.getSchlangenart().getNachStr().getParameters();

                        if (Typ.equals("Distanz")) {
                            Parameter parameter = parameters.get(0);
                            int wert = parameter.getWert();
                            if (Math.abs(vorherigesGlied.getFeld().getSpalte() - schlangenglied.getFeld().getSpalte()) > wert || Math.abs(vorherigesGlied.getFeld().getZeile() - schlangenglied.getFeld().getZeile()) > wert) {
                                fehlertypList.add(Fehlertyp.NACHBARSCHAFT);
                            }
                        }
                        else {
                            int parameter_1 = parameters.get(0).getWert();
                            int parameter_2 = parameters.get(1).getWert();

                            if ((Math.abs(vorherigesGlied.getFeld().getSpalte() - schlangenglied.getFeld().getSpalte()) == parameter_1 && Math.abs(vorherigesGlied.getFeld().getZeile() - schlangenglied.getFeld().getZeile()) == parameter_2 || (Math.abs(vorherigesGlied.getFeld().getSpalte() - schlangenglied.getFeld().getSpalte()) == parameter_2 && Math.abs(vorherigesGlied.getFeld().getZeile() - schlangenglied.getFeld().getZeile()) == parameter_1))) {}
                            else {
                                fehlertypList.add(Fehlertyp.NACHBARSCHAFT);
                            }

                        }
                    }
                }

            }
        }
        return fehlertypList;
    }

    @Override
    public int bewerteLoesung(String xmlEingabeDatei) {
        // TODO Implementierung der API-Methode zur Bewertung von Loesungen.
        de.fernuni.kurs01584.ss23.modell.Schlangenjagd loesung = null;
        try {
            loesung = DateneingabeXML.parseXMLmitSchlangen(xmlEingabeDatei);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        int punktzahl = 0;
        punktzahl = SchlangenSuche.rechnePunkt(loesung, punktzahl);
        return punktzahl;
    }

    public void darstellung(String xmlEingabeDatei, String xmlAusgabeDatei) throws Exception {
        if (!xmlEingabeDatei.equals("")) {
            de.fernuni.kurs01584.ss23.modell.Schlangenjagd probleminstanz = DateneingabeXML.parseXML(xmlEingabeDatei);
            de.fernuni.kurs01584.ss23.modell.Schlangenjagd loesung = DateneingabeXML.parseXMLmitSchlangen(xmlAusgabeDatei);
            DarstellungLoesungen.probleminstanzDarstellen(xmlEingabeDatei, probleminstanz);
            DarstellungLoesungen.loesungDarstellen(xmlAusgabeDatei, loesung);}
        else {
            de.fernuni.kurs01584.ss23.modell.Schlangenjagd loesung = DateneingabeXML.parseXMLmitSchlangen(xmlAusgabeDatei);
            DarstellungLoesungen.loesungDarstellen(xmlAusgabeDatei, loesung);
        }
    }

    @Override
    public String getName() {
        // TODO Implementierung der API-Methode zur Rueckgabe Ihres vollstaenigen Namens.
        String vorname = "Kuan";
        String nachname = "Wang";

        return nachname + ", " + vorname;
    }

    @Override
    public String getMatrikelnummer() {
        // TODO Implementierung der API-Methode zur Rueckgabe Ihrer Matrikelnummer.
        String matriketnummer = "4067827";
        return matriketnummer;
    }

    @Override
    public String getEmail() {
        // TODO Implementierung der API-Methode zur Rueckgabe Ihrer E-Mail Adresse.
        String Email = "wangkuan42@gmail.com";
        return Email;
    }

    private boolean validateDTD(String eingabe) {
        try {
            // Erstellen des SAXBuilders
            SAXBuilder builder = new SAXBuilder();

            // Parsing der XML-Datei
            Document document = builder.build(new File(eingabe));
            if (!document.hasRootElement()) {
                System.out.println("Die eingelesene XML-Datei entspricht nicht den Vorgaben der DTD.");
                return false;
            }

        } catch (JDOMException e) {
            System.out.println("eingelesene XML-Datei entspricht nicht den Vorgaben der DTD.");
        } catch (FileNotFoundException e) {
            System.out.println("keine DTD-Datei gefunden");
        } catch (Exception e) {
            System.out.println("sonstige Fehlern");
        }

        return true;

    }


}
