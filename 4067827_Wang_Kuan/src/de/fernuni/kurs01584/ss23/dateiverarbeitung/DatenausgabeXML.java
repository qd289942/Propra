package de.fernuni.kurs01584.ss23.dateiverarbeitung;

import de.fernuni.kurs01584.ss23.modell.*;

import org.jdom2.*;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import java.io.FileWriter;
import java.util.List;

// Ergebnisse in XML zurück schreiben
public class DatenausgabeXML {
    public static void writeXML(Schlangenjagd problemInstanz, Schlangenjagd schlangenjagd, String filePath) throws Exception {
            // Root Element Schlangenjagd erstellen
            Element rootElement = new Element("Schlangenjagd");

            // Zeit Element erstellen
            Element zeitElement = new Element("Zeit");
            zeitElement.setAttribute("einheit", schlangenjagd.getZeit().getEinheit());
            
            Element vorgabeElement = new Element("Vorgabe");
            vorgabeElement.setText(Double.toString(schlangenjagd.getZeit().getVorgabe()));
            Element abgabeElement = new Element("Abgabe");
            abgabeElement.setText(Double.toString(schlangenjagd.getZeit().getAbgabe()));
            
            zeitElement.addContent(vorgabeElement);
            zeitElement.addContent(abgabeElement);
            rootElement.addContent(zeitElement);
            
            // Dschungel Element erstellen
            Element dschungelElement = new Element("Dschungel");
            dschungelElement.setAttribute("zeilen", Integer.toString(problemInstanz.getDschungel().getZeilen()));
            dschungelElement.setAttribute("spalten", Integer.toString(problemInstanz.getDschungel().getSpalten()));
            dschungelElement.setAttribute("zeichen", problemInstanz.getDschungel().getzeichenmenge());
            
          
            for (Feld feld : problemInstanz.getDschungel().getFelder()) {
                Element feldElement = new Element("Feld");
                
                // feld Element erstellen und Attribute zuweisen
                feldElement.setAttribute("id", feld.getId());
                feldElement.setAttribute("zeile", Integer.toString(feld.getZeile()));
                feldElement.setAttribute("spalte", Integer.toString(feld.getSpalte()));
                feldElement.setAttribute("verwendbarkeit", Integer.toString(feld.getVerwendbarkeit()));
                feldElement.setAttribute("punkte", Integer.toString(feld.getPunkte()));
                feldElement.setText(feld.getZeichen());
                
                // füge Element Feld in dschungel hinzu
                dschungelElement.addContent(feldElement);
            }
            
            rootElement.addContent(dschungelElement);
            
            // Schlangenarten Element erstellen
            Element schlangenartenElement = new Element("Schlangenarten");
            for (Schlangenart schlangenart : problemInstanz.getSchlangenarten()) {
                Element schlangenartElement = new Element("Schlangenart");
                
                // feld Element erstellen und Attribute zuweisen
                schlangenartElement.setAttribute("id", schlangenart.getId());
                schlangenartElement.setAttribute("punkte", Integer.toString(schlangenart.getPunkte()));
                schlangenartElement.setAttribute("anzahl", Integer.toString(schlangenart.getVerwendbarkeit()));
                
                // Zeichenkette und Nachbarschaftsstruktur Element erstellen
                Element zeichenketteElement = new Element("Zeichenkette");
                zeichenketteElement.setText(schlangenart.getZeichenkette());
                Element nachbarschaftsstrukturElement = new Element("Nachbarschaftsstruktur");
                nachbarschaftsstrukturElement.setAttribute("typ", schlangenart.getNachStr().getTyp());
                for (Parameter parameter : schlangenart.getNachStr().getParameters()) {
                    Element parameterElement = new Element("Parameter");
                    parameterElement.setAttribute("wert", Integer.toString(parameter.getWert()));
                    nachbarschaftsstrukturElement.addContent(parameterElement);
                }
                // füge Element zeichenkette und nachbarschaftsstruktur in schlangenart hinzu
                schlangenartElement.addContent(zeichenketteElement);
                schlangenartElement.addContent(nachbarschaftsstrukturElement);
                schlangenartenElement.addContent(schlangenartElement);
            }
            
            rootElement.addContent(schlangenartenElement);
            
            // Schlangen Element erstellen
            Element schlangenElement = new Element("Schlangen");
            
            // Schlange Element erstellen und Attribute zuweisen
            for (Schlange schlange : schlangenjagd.getSchlangen()) {
                Element schlangeElement = new Element("Schlange");
                schlangeElement.setAttribute("art", schlange.getSchlangenart().getId());
                // Schlangenglied Element erstellen und Attribute zuweisen
                for (Schlangenglied schlangenglied : schlange.getSchlangengliedmenge()) {
                    Element schlangengliedElement = new Element("Schlangenglied");
                    schlangengliedElement.setAttribute("feld", schlangenglied.getFeld().getId());
                    // füge Schlangenglied Element zu Schlange hinzu
                    schlangeElement.addContent(schlangengliedElement);
                }
                // füge Element Schlange in Schlangen hinzu
                schlangenElement.addContent(schlangeElement);
            }
            // füge Schlangen Element in Root Element hinzu
            rootElement.addContent(schlangenElement);
            
            // Dokument erstellen und rootElement zuweisen
            Document document = new Document(rootElement);
            // DOCTYPE Einstellen
            DocType docType = new DocType("Schlangenjagd", "schlangenjagd.dtd");
            document.setDocType(docType);

            // XML Outputter Erstellen
            XMLOutputter xmlOutputter = new XMLOutputter();
            xmlOutputter.setFormat(Format.getPrettyFormat());

            // Verbinden XMLOutputter und FileWriter
            FileWriter fileWriter = new FileWriter(filePath);
            xmlOutputter.output(document, fileWriter);
            fileWriter.close();

            System.out.println("XML Datei wurde erfolgreich ueberschrieben: " + filePath);
    }
}
