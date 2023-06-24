package de.fernuni.kurs01584.ss23.modell;

public class Dschungel {
    // TODO: Attribute
    private int zeilen;
    private int spalten;
    private String zeichenmenge;

    // TODO: Konstruktoren

    public Dschungel(int zeilen, int spalten, String zeichenmenge) {
        if (zeilen < 0 || spalten < 0) {
            throw new IllegalArgumentException(
                    "Fuer die Klasse 'Dschungel' duerfen die Attribute 'zeilen' und 'spalten' keine negativen Werte annehmen.");
        }
        this.zeilen = zeilen;
        this.spalten = spalten;
        this.zeichenmenge = zeichenmenge;
    }
    
    
    // default zeichenmenge without input
    public Dschungel(int zeilen, int spalten) {
        this.zeilen = zeilen;
        this.spalten = spalten;
        this.zeichenmenge = "ABCDEFGHIJKLMNOPQRSTUVWXYZÄÖÜ";
    }


    // TODO: Methoden
    
    // add getters of attributes
    public int getZeilen() {
        return zeilen;
    }

    public int getSpalten() {
        return spalten;
    }

    public String getzeichenmenge() {
        return zeichenmenge;
    }
}
