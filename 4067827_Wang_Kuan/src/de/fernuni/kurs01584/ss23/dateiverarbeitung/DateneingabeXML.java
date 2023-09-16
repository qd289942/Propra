package de.fernuni.kurs01584.ss23.dateiverarbeitung;
import de.fernuni.kurs01584.ss23.modell.*;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 *  Klasse zur Darstellung der Dateneinlesenprozess
 */
public class DateneingabeXML {
    /**
     * Dateneinlesen durch vorgegebene Probleminstanz
     * @param filePath von Probleminstanz
     * @return Schlangenjagd Modelle mit vorgegebener Daten aus Probleminstanz
     * @throws Exception
     */
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
                zeit.setVorgabe(Double.parseDouble(vorgabeElement.getText()));
            }

            Element abgabeElement = zeitElement.getChild("Abgabe");
            if (abgabeElement != null) {
                zeit.setAbgabe(Double.parseDouble(abgabeElement.getText()));
            }

            schlangenjagd.setZeit(zeit);
        }

        // Parse Dschungel element
        Element dschungelElement = rootElement.getChild("Dschungel");
        Dschungel dschungel = new Dschungel();
        // dschungel Objekt erstellen und Attribute zuweisen
        String dschungelzeilen = dschungelElement.getAttributeValue("zeilen");
        if (dschungelzeilen != null && dschungelzeilen != "") {
            dschungel.setZeilen(Integer.parseInt(dschungelzeilen));
        }
        String dschungelsplaten = dschungelElement.getAttributeValue("spalten");
        if (dschungelsplaten != null && dschungelsplaten != "") {
            dschungel.setSpalten(Integer.parseInt(dschungelsplaten));
        }
        String dschungelzeichen = dschungelElement.getAttributeValue("zeichen");
        if (dschungelzeichen != null && dschungelzeichen != "") {
            dschungel.setZeichenmenge(dschungelzeichen);
        }

        // Feld in Dschungel zuordnen
        List<Element> feldElements = dschungelElement.getChildren("Feld");
        List<Feld> felder = new ArrayList<>();
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
        if (schlangenartenElement != null) {
            // List für SchlangenartElements in XML Datei
            List<Element> schlangenartElements = schlangenartenElement.getChildren("Schlangenart");
            // List für Schlangenart erstellen
            List<Schlangenart> schlangenartList = new ArrayList<>();
            if (!schlangenartElements.isEmpty()) {

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
                schlangenjagd.setSchlangenarten(schlangenartList);
            }
        }


        return schlangenjagd;
    }
    /**
     * Dateneinlesen mit Schlangen(Lösung) zur Auswertung
     * @param filePath von ausgegebene Lösung durch SchlangenSuche Algorithmus
     * @return Schlangenjagd Modelle mit vorgegebener Daten aus Probleminstanz sowie Schlangen aus Lösungen
     * @throws Exception
     */
    public static Schlangenjagd parseXMLmitSchlangen(String filePath) throws Exception {
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
                zeit.setVorgabe(Double.parseDouble(vorgabeElement.getText()));
            }

            Element abgabeElement = zeitElement.getChild("Abgabe");
            if (abgabeElement != null) {
                zeit.setAbgabe(Double.parseDouble(abgabeElement.getText()));
            }

            schlangenjagd.setZeit(zeit);
        }

        // Parse Dschungel element
        Element dschungelElement = rootElement.getChild("Dschungel");
        Dschungel dschungel = new Dschungel();
        // dschungel Objekt erstellen und Attribute zuweisen
        String dschungelzeilen = dschungelElement.getAttributeValue("zeilen");
        if (dschungelzeilen != null && dschungelzeilen != "") {
            dschungel.setZeilen(Integer.parseInt(dschungelzeilen));
        }
        String dschungelsplaten = dschungelElement.getAttributeValue("spalten");
        if (dschungelsplaten != null && dschungelsplaten != "") {
            dschungel.setSpalten(Integer.parseInt(dschungelsplaten));
        }
        String dschungelzeichen = dschungelElement.getAttributeValue("zeichen");
        if (dschungelzeichen != null && dschungelzeichen != "") {
            dschungel.setZeichenmenge(dschungelzeichen);
        }

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
        if (schlangenartenElement != null) {
            // List für Schlangenart erstellen
            List<Schlangenart> schlangenartList = new ArrayList<>();
            // List für SchlangenartElements in XML Datei
            List<Element> schlangenartElements = schlangenartenElement.getChildren("Schlangenart");
            if (!schlangenartElements.isEmpty()) {

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
                schlangenjagd.setSchlangenarten(schlangenartList);
            }
        }

        // Parse Schlangen Element
        Element schlangenElement = rootElement.getChild("Schlangen");
        if (schlangenElement != null) {
            // List für Schlange erstellen
            List<Schlange> schlangenList = new ArrayList<>();

            List<Element> schlangenElements = schlangenElement.getChildren("Schlange");

            for (Element schlangeElement : schlangenElements) {
                Schlange schlange = new Schlange();
                String artId = schlangeElement.getAttributeValue("art");

                // Sucht Schlangenart anhand id
                Schlangenart suchendeSchlangenart = new Schlangenart();
                for (Schlangenart schlangenart : schlangenjagd.getSchlangenarten()) {
                    if (schlangenart.getId().equals(artId)) {
                        suchendeSchlangenart = schlangenart;
                        break;
                    }
                }
                schlange.setSchlangenart(suchendeSchlangenart);

                // List für Schlangenglied erstellen

                List<Element> schlangengliedElements = schlangeElement.getChildren("Schlangenglied");
                List<Schlangenglied> SchlangengliedList = new ArrayList<>();
                for (Element schlangegliedElement: schlangengliedElements) {
                    Schlangenglied schlangenglied = new Schlangenglied();
                    String FeldId = schlangegliedElement.getAttributeValue("feld");
                    // Sucht Feld anhand id
                    Feld suchendeFeld = new Feld();
                    for (Feld feld : schlangenjagd.getDschungel().getFelder()) {
                        if (feld.getId().equals(FeldId)) {
                            suchendeFeld = feld;
                            break;
                        }
                    }
                    schlangenglied.setFeld(suchendeFeld); 
                    // füge Schlangenglied in SchlangengliedList hinzu
                    SchlangengliedList.add(schlangenglied);
                }
                schlange.setSchlangengliedmenge(SchlangengliedList);
                // füge Schlange in SchlangenList hinzu
                schlangenList.add(schlange);
            }

            // Schlangen Attribute in Schlangenjagd zuordnen
            schlangenjagd.setSchlangen(schlangenList);
        }
        return schlangenjagd;
    }

}
