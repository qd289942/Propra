package de.fernuni.kurs01584.ss23.dateiverarbeitung;

import de.fernuni.kurs01584.ss23.modell.*;

import org.jdom2.*;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import java.io.FileWriter;

// Ergebnisse in XML zurück schreiben
public class DatenausgabeXML {
    public static void writeXML(Schlangenjagd schlangenjagd, String filePath) throws Exception {
            // Root Element Schlangenjagd erstellen
            Element rootElement = new Element("Schlangenjagd");

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

            // XML Outputter Erstellen
            XMLOutputter xmlOutputter = new XMLOutputter();
            xmlOutputter.setFormat(Format.getPrettyFormat());

            // Verbinden XMLOutputter und FileWriter
            FileWriter fileWriter = new FileWriter(filePath);
            xmlOutputter.output(document, fileWriter);
            fileWriter.close();

            System.out.println("XML Datei wurde erfolgreich überschrieben: " + filePath);
    }
}
