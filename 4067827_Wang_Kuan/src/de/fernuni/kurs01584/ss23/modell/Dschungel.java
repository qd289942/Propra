package de.fernuni.kurs01584.ss23.modell;

import java.util.List;

public class Dschungel {
    // TODO: Attribute
    private int zeilen;
    private int spalten;
    private String zeichenmenge;
    private List<Feld> felder;

    // TODO: Konstruktoren
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
    
    // default zeichenmenge without input
    public Dschungel(int zeilen, int spalten) {
        this.zeilen = zeilen;
        this.spalten = spalten;
        this.zeichenmenge = "ABCDEFGHIJKLMNOPQRSTUVWXYZÄÖÜ";
    }


    // TODO: Methoden
    
    // add getters and setters of attributes
    
    public int getZeilen() {
        return zeilen;
    }
    public int getSpalten() {
        return spalten;
    }
    public String getzeichenmenge() {
        return zeichenmenge;
    }
    public List<Feld> getFelder() {
        return felder;
    }
    public void setZeilen(int zeilen) {
        this.zeilen = zeilen;
    }
    public void setSpalten(int spalten) {
        this.spalten = spalten;
    }
    public void setZeichenmenge(String zeichenmenge) {
        this.zeichenmenge = zeichenmenge;
    }
    public void setFelder(List<Feld> felder) {
        this.felder = felder;
    }
    
}
