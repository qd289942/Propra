package de.fernuni.kurs01584.ss23.hauptkomponente;

import java.util.ArrayList;
import java.util.List;

import de.fernuni.kurs01584.ss23.algorithmus.SchlangenSuche;
import de.fernuni.kurs01584.ss23.dateiverarbeitung.DateneingabeXML;
import de.fernuni.kurs01584.ss23.modell.*;


public class Schlangenjagd implements SchlangenjagdAPI {
    // TODO: Implementierung von Schnittstelle und Programm-Einstieg
    public static void main (String[] args) {
        
    }
    @Override
    public boolean loeseProbleminstanz(String xmlEingabeDatei, String xmlAusgabeDatei) throws Exception {
        // TODO Implementierung der API-Methode zur Loesung von Probleminstanzen.
        de.fernuni.kurs01584.ss23.modell.Schlangenjagd schlangenjagd = DateneingabeXML.parseXML(xmlEingabeDatei);
        // Probleminstanz wird gelöst und in AusgabeDatei gespeichert
        SchlangenSuche.sucheSchlange(schlangenjagd, xmlAusgabeDatei);
        return true;
    }

    @Override
    public boolean erzeugeProbleminstanz(String xmlEingabeDatei, String xmlAusgabeDatei) {
        // TODO Implementierung der API-Methode zur Erzeugung von Probleminstanzen.
        return false;
    }

    @Override
    public List<Fehlertyp> pruefeLoesung(String xmlEingabeDatei) throws Exception {

        // TODO Implementierung der API-Methode zur Pruefung von Loesungen.
        de.fernuni.kurs01584.ss23.modell.Schlangenjagd loesung = DateneingabeXML.parseXMLmitSchlangen(xmlEingabeDatei);
        List<Fehlertyp> fehlertypList = new ArrayList<>();
        List<Schlange> schlangeList = loesung.getSchlangen();
        // prüft jede Schlange und gibt ggf. Fehlertyp in fehlertypList zurück
        for (Schlange schlange : schlangeList) {
            if (schlange.getSchlangengliedmenge().size() != schlange.getSchlangenart().getZeichenkette().length()) {
                fehlertypList.add(Fehlertyp.GLIEDER);
            }

            for (Schlangenglied schlangenglied: schlange.getSchlangengliedmenge()) {
                if (schlangenglied.getFeld().getZeichen().equals(String.valueOf(schlange.getSchlangenart().getZeichenkette().charAt(schlange.getSchlangengliedmenge().indexOf(schlangenglied))))) {
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

                    if (Typ == "Distanz") {
                        Parameter parameter = parameters.get(0);
                        int wert = parameter.getWert();
                        if (Math.abs(vorherigesGlied.getFeld().getSpalte() - schlangenglied.getFeld().getSpalte()) > wert || Math.abs(vorherigesGlied.getFeld().getZeile() - schlangenglied.getFeld().getZeile()) > wert) {
                            fehlertypList.add(Fehlertyp.NACHBARSCHAFT);
                        }
                    }
                    else {
                        List<Integer> werts = new ArrayList<>();
                        for (Parameter parameter : parameters) {
                            werts.add(parameter.getWert());
                        }

                        if ((Math.abs(vorherigesGlied.getFeld().getSpalte() - schlangenglied.getFeld().getSpalte()) == werts.get(0) && Math.abs(vorherigesGlied.getFeld().getZeile() - schlangenglied.getFeld().getZeile()) == werts.get(1)) || (Math.abs(vorherigesGlied.getFeld().getSpalte() - schlangenglied.getFeld().getSpalte()) == werts.get(1) && Math.abs(vorherigesGlied.getFeld().getZeile() - schlangenglied.getFeld().getZeile()) == werts.get(0))) {}
                        else {
                            fehlertypList.add(Fehlertyp.NACHBARSCHAFT);
                        }

                    }
                }

            }
        }
        return fehlertypList;
    }

    @Override
    public int bewerteLoesung(String xmlEingabeDatei) throws Exception {
        // TODO Implementierung der API-Methode zur Bewertung von Loesungen.
        de.fernuni.kurs01584.ss23.modell.Schlangenjagd loesung = DateneingabeXML.parseXMLmitSchlangen(xmlEingabeDatei);
        int punktzahl = 0;
        punktzahl = SchlangenSuche.rechnePunkt(loesung, punktzahl);
        return punktzahl;
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

}
