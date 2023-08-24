package de.fernuni.kurs01584.ss23.modell;

import java.util.List;

/**
 * Klasse Dschungel
 * Dies ist eine einfache Java-Klasse, die einen Dschugel darstellt.
 * */

public class Dschungel {
    /** Attribute
     * zeilen: gesamte Zeilen in Dschungel
     * spalten: gesamte Spalten in Dschungel
     * zeichenmenge: zulässige Zeichen in Dschungel
     * felder: List in Dschungel zugeordnete Felder
     */
    private int zeilen;
    private int spalten;
    private String zeichenmenge;
    private List<Feld> felder;

    /**
     *  Konstruktor 1: leere Konstruktor
     *  Konstruktor 2: Konstruktor mit alle Attribute Wert
     *  @param zeilen: Anzahl Zeilen Dschungel
     *  @param spalten: Anzahl Spalten Dschungel
     *  @param zeichenmenge Zeichen Dschungel
     *  @param felder: List von Feld in Dschungel
     *  Konstruktor 3: nur mit zeilen und spalten sowie default Zeichenmenge
     *  @param zeilen: Anzahl Zeilen Dschungel
     *  @param spalten: Anzahl Spalten Dschungel
     */
    public Dschungel() {
        
    }
    
    public Dschungel(int zeilen, int spalten, String zeichenmenge, List<Feld> felder) {
        if (zeilen < 0 || spalten < 0) {
            throw new IllegalArgumentException(
                    "Fuer die Klasse 'Dschungel' duerfen die Attribute 'zeilen' und 'spalten' keine negativen Werte annehmen.");
        }
        this.zeilen = zeilen;
        this.spalten = spalten;
        this.zeichenmenge = zeichenmenge;
        this.felder = felder;
    }
    
    public Dschungel(int zeilen, int spalten) {
        this.zeilen = zeilen;
        this.spalten = spalten;
        this.zeichenmenge = "ABCDEFGHIJKLMNOPQRSTUVWXYZÄÖÜ";
    }


    /**
     * Getter von Zeilen
     * @return Anzahl von gesamte Zeilen in Dschungel
     */
    public int getZeilen() {
        return zeilen;
    }
    
    /**
     * Getter von Spalten
     * @return Anzahl von gesamte Spalten in Dschungel
     */
    public int getSpalten() {
        return spalten;
    }
    
    /**
     * Getter von Zeichenmenge
     * @return zulässige Zeichenmenge in Dschungel
     */
    public String getzeichenmenge() {
        return zeichenmenge;
    }
    
    /**
     * Getter von Felder
     * @return Felder in Dschungel
     */
    public List<Feld> getFelder() {
        return felder;
    }
    
    /**
     * Setter von Anzahl der Zeilen
     * @param zeilen
     */
    public void setZeilen(int zeilen) {
        this.zeilen = zeilen;
    }
    /**
     * Setter von Anzahl der Spalten
     * @param spalten
     */
    public void setSpalten(int spalten) {
        this.spalten = spalten;
    }
    /**
     * Setter von Zeichenmenge
     * @param zeichenmenge
     */
    public void setZeichenmenge(String zeichenmenge) {
        this.zeichenmenge = zeichenmenge;
    }
    /**
     * Setter von Felder in Dschungel
     * @param felder
     */
    public void setFelder(List<Feld> felder) {
        this.felder = felder;
    }
    
}
