package de.fernuni.kurs01584.ss23.modell;

/**
 * Klasse zur Darstellung der Schlangenarten
 */
public class Schlangenart implements Priorisierbar{
    /**
     * Attribute:
     * String id: spezifische ID für jeden Schlangenart
     * int punkte: Punktzahl für Schlangenart
     * int anzahl: Anzahl von Schlangenart in Dschungel
     * String zeichenkette: Zeichenkette für jeweiligen Schlangenart
     * Nachbarschaftsstruktur nachStr: Nachbarschaftsstruktur für den Schlangenart
     */
    private String id;
    // Standarwert 1
    private int punkte;
    private int anzahl;
    private String zeichenkette;
    private Nachbarschaftsstruktur nachStr;

    /**
     *  leere Konstruktor
     */
    public Schlangenart () {

    }
    /**
     * Konstruktor mit aller Parametern
     * @param id
     * @param punkte
     * @param anzahl
     * @param zeichenkette
     * @param nachStr
     */
    public Schlangenart (String id, int punkte, int anzahl, String zeichenkette, Nachbarschaftsstruktur nachStr) {
        if (punkte < 0 || anzahl < 0) {
            throw new IllegalArgumentException(
                    "Fuer die Klasse 'Schlangenart' duerfen die Attribute 'punkt' und 'anzahl' keine negativen Werte annehmen.");
        }
        this.id = id;
        this.punkte = punkte;
        this.anzahl = anzahl;
        this.zeichenkette = zeichenkette;
        this.nachStr = nachStr;
    }

    /**
     *  dritte Konstruktor mit default Punkt = 1
     * @param id
     * @param anzahl
     * @param zeichenkette
     * @param nachStr
     */
    public Schlangenart (String id, int anzahl, String zeichenkette, Nachbarschaftsstruktur nachStr) {
        if (anzahl < 0) {
            throw new IllegalArgumentException(
                    "Fuer die Klasse 'Schlangenart' duerfen die Attribute'anzahl' keine negativen Werte annehmen.");
        }
        this.id = id;
        this.anzahl = anzahl;
        this.zeichenkette = zeichenkette;
        this.nachStr = nachStr;
    }

    
    /**
     * getter von Id des Schlangenarts
     * @return
     */
    public String getId() {
        return id;
    }
    /**
     * setter Id
     * @param id spezifische ID für jeden Schlangenart
     */
    public void setId(String id) {
        this.id = id;
    }
    /**
     * getter Punktzahl des Schlangenarts
     * @return punkte: Punktzahl für Schlangenart
     */
    @Override
    public int getPunkte() {
        return punkte;
    }
    /**
     * setter Punktzahl des Schlangenarts
     * @param punkte: Punktzahl für Schlangenart
     */
    public void setPunkte(int punkte) {
        this.punkte = punkte;
    }

    /** 
     * getter Anzahl von Schlangenart
     * @return anzahl: Anzahl von Schlangenart in Dschungel
     */
    @Override
    public int getVerwendbarkeit() {
        return anzahl;
    }
    
    /**
     * setter Anzahl von Schlangenart
     * @param anzahl Anzahl von Schlangenart in Dschungel
     */
    public void setAnzahl(int anzahl) {
        this.anzahl = anzahl;
    }
    
    /**
     * getter Zeichenkette von Schlangenart
     * @return zeichenkette: Zeichenkette für jeweiligen Schlangenart
     */
    public String getZeichenkette() {
        return zeichenkette;
    }
    /**
     * setter Zeichenkette von Schlangenart
     * @param zeichenkette: Zeichenkette für jeweiligen Schlangenart
     */
    public void setZeichenkette(String zeichenkette) {
        this.zeichenkette = zeichenkette;
    }
    
    /**
     * getter Nachbarschaftsstruktur
     * @return nachStr: Nachbarschaftsstruktur für den Schlangenart
     */
    public Nachbarschaftsstruktur getNachStr() {
        return nachStr;
    }
    /**
     * setter Nachbarschaftsstruktur
     * @param nachStr: Nachbarschaftsstruktur für den Schlangenart
     */
    public void setNachStr(Nachbarschaftsstruktur nachStr) {
        this.nachStr = nachStr;
    }


}
