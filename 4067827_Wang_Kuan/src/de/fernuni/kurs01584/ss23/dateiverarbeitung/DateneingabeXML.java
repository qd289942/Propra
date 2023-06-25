package de.fernuni.kurs01584.ss23.dateiverarbeitung;
import de.fernuni.kurs01584.ss23.modell.*;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DateneingabeXML {
    public static Schlangenjagd parseXML(String filePath) throws Exception {
        SAXBuilder builder = new SAXBuilder();
        Document document = builder.build(new File(filePath));
        // RootElment
        Element rootElement = document.getRootElement();

        // Root Objekt definieren
        Schlangenjagd schlangenjagd = new Schlangenjagd();

        // Parse Zeit Element
        Element zeitElement = rootElement.getChild("Zeit");

        if (zeitElement != null) {
            // Zeit Objekt erstellen und Attribute zuweisen
            Zeit zeit = new Zeit();
            zeit.setEinheit(zeitElement.getAttributeValue("einheit"));

            Element vorgabeElement = zeitElement.getChild("Vorgabe");
            if (vorgabeElement != null) {
                zeit.setVorgabe(Integer.parseInt(vorgabeElement.getText()));
            }

            Element abgabeElement = zeitElement.getChild("Abgabe");
            if (abgabeElement != null) {
                zeit.setAbgabe(Integer.parseInt(abgabeElement.getText()));
            }

            schlangenjagd.setZeit(zeit);
        }

        // Parse Dschungel element
        Element dschungelElement = rootElement.getChild("Dschungel");
        Dschungel dschungel = new Dschungel();
        // dschungel Objekt erstellen und Attribute zuweisen
        dschungel.setZeilen(Integer.parseInt(dschungelElement.getAttributeValue("zeilen")));
        dschungel.setSpalten(Integer.parseInt(dschungelElement.getAttributeValue("spalten")));
        dschungel.setZeichenmenge(dschungelElement.getAttributeValue("zeichen"));

        // Feld in Dschungel zuordnen
        List<Feld> felder = new ArrayList<>();
        List<Element> feldElements = dschungelElement.getChildren("Feld");
        // FeldList erstellen und Feld in List einpacken und attribute zuweisen
        for (Element feldElement : feldElements) {
            Feld feld = new Feld();
            feld.setId(feldElement.getAttributeValue("id"));
            feld.setZeile(Integer.parseInt(feldElement.getAttributeValue("zeile")));
            feld.setSpalte(Integer.parseInt(feldElement.getAttributeValue("spalte")));
            feld.setVerwendbarkeit(Integer.parseInt(feldElement.getAttributeValue("verwendbarkeit")));
            feld.setPunkte(Integer.parseInt(feldElement.getAttributeValue("punkte")));
            feld.setZeichen(feldElement.getText());
            felder.add(feld);
        }
        dschungel.setFelder(felder);
        schlangenjagd.setDschungel(dschungel);

        // Parse Schlangenarten Element
        Element schlangenartenElement = rootElement.getChild("Schlangenarten");
        Schlangenarten schlangenarten = new Schlangenarten();
        // List für Schlangenart erstellen
        List<Schlangenart> schlangenartList = new ArrayList<>();
        // List für SchlangenartElements in XML Datei
        List<Element> schlangenartElements = schlangenartenElement.getChildren("Schlangenart");
        
        // für jede Schleife Schlangenart Objekt erstellen und Attribute zuweisen
        for (Element schlangenartElement : schlangenartElements) {
            Schlangenart schlangenart = new Schlangenart();
            schlangenart.setId(schlangenartElement.getAttributeValue("id"));
            schlangenart.setPunkte(Integer.parseInt(schlangenartElement.getAttributeValue("punkte")));
            schlangenart.setAnzahl(Integer.parseInt(schlangenartElement.getAttributeValue("anzahl")));
            schlangenart.setZeichenkette(schlangenartElement.getChildText("Zeichenkette"));

            Element nachbarschaftsstrukturElement = schlangenartElement.getChild("Nachbarschaftsstruktur");
            // Objekt für Nachbarschaftsstruktur erstellen und Attribute zuweisen
            Nachbarschaftsstruktur nachbarschaftsstruktur = new Nachbarschaftsstruktur();
            nachbarschaftsstruktur.setTyp(nachbarschaftsstrukturElement.getAttributeValue("typ"));
            // List für Parameter erstellen
            List<Parameter> parameterList = new ArrayList<>();
            // List für parameterElements in XML Datei
            List<Element> parameterElements = nachbarschaftsstrukturElement.getChildren("Parameter");
            
            // für jede Schleife Parameter Objekt erstellen und Attribute zuweisen
            for (Element parameterElement : parameterElements) {
                Parameter parameter = new Parameter();
                parameter.setWert(Integer.parseInt(parameterElement.getAttributeValue("wert")));
                parameterList.add(parameter);
            }
            
            nachbarschaftsstruktur.setParameters(parameterList);
            schlangenart.setNachStr(nachbarschaftsstruktur);
            // Schlangenarten in List zuordnen
            schlangenartList.add(schlangenart);
        }
        schlangenarten.setSchlangenarte(schlangenartList);
        schlangenjagd.setSchlangenarten(schlangenarten);

        return schlangenjagd;
    }
}
