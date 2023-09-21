package de.fernuni.kurs01584.ss23.hauptkomponente;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.jdom2.*;
import org.jdom2.input.SAXBuilder;

import de.fernuni.kurs01584.ss23.algorithmus.DschungelGenerator;
import de.fernuni.kurs01584.ss23.algorithmus.SchlangenSuche;
import de.fernuni.kurs01584.ss23.darstellung.DarstellungLoesungen;
import de.fernuni.kurs01584.ss23.dateiverarbeitung.DatenausgabeXML;
import de.fernuni.kurs01584.ss23.dateiverarbeitung.DateneingabeXML;
import de.fernuni.kurs01584.ss23.modell.*;


public class Schlangenjagd implements SchlangenjagdAPI {

    /**
     * Implementierung von Schnittstelle und Programm-Einstieg
     * @param args Kommandozeilenparameter
     * @throws Exception
     */
    public static void main (String[] args) throws Exception {

        // Kommandozeilenparameter überprüfen
        if (args.length < 3) {
            System.out.println("Kommandozeilenparametern sind nicht vollständig. Dateipfad von Ausgabe fehlt noch!");
            return;
        }

        // Kommandozeilenparameter zuweisen
        String ablauf = args[0].substring(args[0].indexOf('=') + 1);
        String eingabe = args[1].substring(args[1].indexOf('=') + 1);
        String ausgabe = args[2].substring(args[2].indexOf('=') + 1);

        // Erstellen neue Objekt von Hauptkomponent Schlangenjagd
        Schlangenjagd schlangenjagd = new Schlangenjagd();

        for (char c: ablauf.toCharArray()) {
            switch (c) {
            // Für eine gegebene Probleminstanz wird nach einer neuen Lösung gesucht und bei Angabe einer Ausgabedatei gespeichert.
            case 'l':
                boolean flag = schlangenjagd.loeseProbleminstanz(eingabe, ausgabe);
                if (flag == false) {
                    System.out.println("keine Schlange gefunden.");
                }
                break;
                // Eine neue Probleminstanz wird auf Basis der gegebenen Parameter erzeugt und bei Angabe einer Ausgabedatei gespeichert.    
            case 'e':
                boolean flag_1 = schlangenjagd.erzeugeProbleminstanz(eingabe, ausgabe);
                if (flag_1 == true) {
                    System.out.println("Probleminstanz wird generiert.");
                }
                break;
                // Die Probleminstanz und die zugehörige Lösung werden in der Konsole dargestellt.
            case 'd':
                schlangenjagd.darstellung(eingabe, ausgabe, ablauf);
                break;
                // Die Zulässigkeit der gegebenen Lösung wird überprüft. Bei Unzulässigkeit werden die Art und Anzahl der verletzten Bedingungen in der Konsole ausgegeben
            case 'p':
                List<Fehlertyp> fehlerTypList = schlangenjagd.pruefeLoesung(ausgabe);
                System.out.println("\n\nFehlertyp und Anzahl: ");
                if (fehlerTypList.isEmpty()) {
                    System.out.println("keine Fehlern gefunden");
                    break;
                }
                // Bei Unzulässigkeit werden die Art und Anzahl der verletzten Bedingungen in der Konsole ausgegeben
                Map<Fehlertyp, Long> countMap = fehlerTypList.stream()
                        .collect(Collectors.groupingBy(fehlertyp -> fehlertyp, Collectors.counting()));
                for (Entry<Fehlertyp, Long> entry : countMap.entrySet()) {
                    System.out.println(entry.getKey() + ": " + entry.getValue());
                }
                break;
                // Die Gesamtpunktzahl der Lösung wird unabhängig von der Zulässigkeit berechnet und in der Konsole ausgegeben.
            case 'b':
                int gesamtPunkte = schlangenjagd.bewerteLoesung(ausgabe);
                System.out.println("\n\nGesamtPunkt: " + gesamtPunkte);
                break;

            default:
                System.out.println("ungültige ablauf Parameter");
                return;
            }
        }

    }
    /**
     * Implementierung der API-Methode zur Loesung von Probleminstanzen.
     * 
     */
    @Override
    public boolean loeseProbleminstanz(String xmlEingabeDatei, String xmlAusgabeDatei) {

        de.fernuni.kurs01584.ss23.modell.Schlangenjagd schlangenjagd = null;
        de.fernuni.kurs01584.ss23.modell.Schlangenjagd probleminstanz = null;

        validateDTD(xmlEingabeDatei); 
        try {
            schlangenjagd = DateneingabeXML.parseXML(xmlEingabeDatei);
            probleminstanz = DateneingabeXML.parseXML(xmlEingabeDatei);
        } catch (Exception e2) {
            // TODO Auto-generated catch block
            e2.printStackTrace();
        }
        
        SchlangenSuche s1 = new SchlangenSuche();
        
        try {
            s1.setProblemInstanz(probleminstanz);
            s1.setSchlangenjagd(schlangenjagd);
            s1.sucheSchlange();
            
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        // Wenn die Zeitabgabe noch unbekannt ist
        if (s1.getProblemInstanz().getZeit().getAbgabe() == 0.0) {
            double zeitinterval = System.currentTimeMillis() - s1.getStartZeit();
            double zeitintervalUmgerechnet = SchlangenSuche.zeitUmrechunung(probleminstanz.getZeit().getEinheit(), zeitinterval);
            s1.getProblemInstanz().getZeit().setAbgabe(zeitintervalUmgerechnet);
        }
        try {
            DatenausgabeXML.writeXML(probleminstanz,s1.getLoesungSchlangen(),xmlAusgabeDatei);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        if (s1.getLoesungSchlangen() != null) {
            System.out.println("Rueckgabe erfolgt, Loesungen sind in XML gespeichert.");
            return true;
        }
        else {
            return false;
        }  
    }
    /**
     * Implementierung der API-Methode zur Erzeugung von Probleminstanzen.
     */
    @Override
    public boolean erzeugeProbleminstanz(String xmlEingabeDatei, String xmlAusgabeDatei) {

        validateDTD(xmlEingabeDatei);
        
        DschungelGenerator dg1 = new DschungelGenerator();
        
        try {
            dg1.erzeugProbleminstanzen(xmlEingabeDatei, xmlAusgabeDatei);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }
    /**
     * Implementierung der API-Methode zur Pruefung von Loesungen.
     */
    @Override
    public List<Fehlertyp> pruefeLoesung(String xmlEingabeDatei) {
        de.fernuni.kurs01584.ss23.modell.Schlangenjagd loesung = null;
        validateDTD(xmlEingabeDatei);
        try {
            loesung = DateneingabeXML.parseXMLmitSchlangen(xmlEingabeDatei);
        } catch (Exception e) {
            e.printStackTrace();
        }

        List<Fehlertyp> fehlertypList = new ArrayList<>();
        List<Schlange> schlangeList = loesung.getSchlangen();
        List<Feld> felder = loesung.getDschungel().getFelder();
        // prüft jede Schlange und gibt ggf. Fehlertyp in fehlertypList zurück

        List<Schlangenglied> gesamteSchlangenglied = new ArrayList<>();

        for (Schlange schlange : schlangeList) {
            if (schlange.getSchlangengliedmenge().size() != schlange.getSchlangenart().getZeichenkette().length()) {
                fehlertypList.add(Fehlertyp.GLIEDER);
            }

            else {

                for (Schlangenglied schlangenglied: schlange.getSchlangengliedmenge()) {
                    if (!schlangenglied.getFeld().getZeichen().equals(String.valueOf(schlange.getSchlangenart().getZeichenkette().charAt(schlange.getSchlangengliedmenge().indexOf(schlangenglied))))) {
                        fehlertypList.add(Fehlertyp.ZUORDNUNG);
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
                            else if (vorherigesGlied.getFeld().getSpalte() == schlangenglied.getFeld().getSpalte() && vorherigesGlied.getFeld().getZeile() == schlangenglied.getFeld().getZeile()){
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
            gesamteSchlangenglied.addAll(schlange.getSchlangengliedmenge());
        }

        // Erstellen ein HashMap zur Speichern Schlangenglieden mit identische gliedId
        Map<String, Integer> countMap = new HashMap<>();
        for (Schlangenglied glied : gesamteSchlangenglied) {
            String gliedId = glied.getFeld().getId();
            countMap.put(gliedId, countMap.getOrDefault(gliedId, 0) + 1);
        }

        // prüft ob ein Schlangenglied einem bereits maximal verwendeten Feld zugeordnet ist
        for (Map.Entry<String, Integer>entry: countMap.entrySet()) {
            for (Feld feld : felder) {
                if (feld.getId().equals(entry.getKey())) {
                    if (entry.getValue() > feld.getVerwendbarkeit()) {
                        fehlertypList.add(Fehlertyp.VERWENDUNG);
                    }
                }
            }
        }

        return fehlertypList;
    }
    /**
     * Implementierung der API-Methode zur Bewertung von Loesungen.
     */
    @Override
    public int bewerteLoesung(String xmlEingabeDatei) {
        de.fernuni.kurs01584.ss23.modell.Schlangenjagd loesung = null;
        try {
            loesung = DateneingabeXML.parseXMLmitSchlangen(xmlEingabeDatei);
        } catch (Exception e) {
            e.printStackTrace();
        }
        int punktzahl = 0;
        punktzahl = SchlangenSuche.rechnePunkt(loesung.getSchlangen());
        return punktzahl;
    }
    /**
     * Darstellung der Lösungen und Probleminstanz
     * @param xmlEingabeDatei Dateipfad zu einer XML-Datei mit Parametern fuer eine
     *                        Probleminstanz, die erzeugt werden soll.
     * @param xmlAusgabeDatei Dateipfad zu einer XML-Datei fuer die erzeugte Probleminstanz.
     * @param ablauf Ablaufparameter
     * @throws Exception
     */
    public void darstellung(String xmlEingabeDatei, String xmlAusgabeDatei, String ablauf) throws Exception {
        if (ablauf.contains("e")) {
            de.fernuni.kurs01584.ss23.modell.Schlangenjagd probleminstanz = DateneingabeXML.parseXML(xmlAusgabeDatei);
            DarstellungLoesungen.probleminstanzDarstellen(probleminstanz);
        }
        else {
            de.fernuni.kurs01584.ss23.modell.Schlangenjagd loesung = DateneingabeXML.parseXMLmitSchlangen(xmlAusgabeDatei);            
            DarstellungLoesungen.loesungDarstellen(loesung);
        }
    }

    /**
     * Implementierung der API-Methode zur Rueckgabe Ihres vollstaenigen Namens.
     */
    @Override
    public String getName() {

        String vorname = "Kuan";
        String nachname = "Wang";

        return nachname + ", " + vorname;
    }

    /**
     * Implementierung der API-Methode zur Rueckgabe Ihrer Matrikelnummer.
     */
    @Override
    public String getMatrikelnummer() {

        String matriketnummer = "4067827";
        return matriketnummer;
    }

    /**
     * Implementierung der API-Methode zur Rueckgabe Ihrer E-Mail Adresse.
     */
    @Override
    public String getEmail() {
        String Email = "wangkuan42@gmail.com";
        return Email;
    }
    /**
     * Validierung der DTD Datei
     * @param eingabe Datenpfad
     * @return falsch wenn Exception ausgelöst ist, sonst true
     */
    private boolean validateDTD(String eingabe) {
        try {
            // Erstellen des SAXBuilders
            SAXBuilder builder = new SAXBuilder();

            // Parsing der XML-Datei
            Document document = builder.build(new File(eingabe));
            if (!document.hasRootElement()) {
                System.out.println("Die eingelesene XML-Datei entspricht nicht den Vorgaben der DTD.");
                System.exit(0);
            }
        } catch (JDOMException e) {
            System.out.println("eingelesene XML-Datei in "+ eingabe +" entspricht nicht den Vorgaben der DTD.");
            System.exit(0);
        } catch (FileNotFoundException e) {
            System.out.println("keine DTD-Datei in " + eingabe+ " gefunden.");
            System.exit(0);
        } catch (Exception e) {
            System.out.println("sonstige Fehlern");
            System.exit(0);
        }

        return true;

    }




}
