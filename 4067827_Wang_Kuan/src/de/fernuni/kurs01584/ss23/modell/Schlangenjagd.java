package de.fernuni.kurs01584.ss23.modell;

import java.util.List;

/**
 * Klasse Schlangenjagd zur Zusammenfassung der Modelle
 */
public class Schlangenjagd {
    /**
     *  Attribute
     *  zeit: Zeitelement: {einheit, vorgabe, abgabe}
     *  dschungel: Dschungelelement: {zeilen, spalten, zeichenmenge, felder}
     *  schlangenarten: List von Schlangenart
     *  schlangen: List von Schlangen
     */
    private Zeit zeit;
    private Dschungel dschungel;
    private List<Schlangenart> schlangenarten;
    private List<Schlange> schlangen;
    
    /**
     * leere Konstruktor
     */
    public Schlangenjagd() {
        
    }
    /**
     * Konstruktor mit vorgegebene Parametern
     * @param zeit
     * @param dschungel
     * @param schlangenarten
     * @param schlangen
     */
    public Schlangenjagd(Zeit zeit, Dschungel dschungel, List<Schlangenart> schlangenarten, List<Schlange> schlangen) {
        this.zeit = zeit;
        this.dschungel = dschungel;
        this.schlangenarten = schlangenarten;
        this.schlangen = schlangen;
    }

    /**
     * Getter von Zeit
     * @return zeit
     */
    public Zeit getZeit() {
        return zeit;
    }
    /**
     * Setter von Zeit
     * @param zeit
     */
    public void setZeit(Zeit zeit) {
        this.zeit = zeit;
    }
    /**
     * getter von Dschungel
     * @return
     */
    public Dschungel getDschungel() {
        return dschungel;
    }
    /**
     * setter von Dschungel
     * @param dschungel
     */
    public void setDschungel(Dschungel dschungel) {
        this.dschungel = dschungel;
    }
    /**
     * getter von Schlangenarten
     * @return schlangenarten
     */
    public List<Schlangenart> getSchlangenarten() {
        return schlangenarten;
    }
    /**
     * setter von Schlangenarten
     * @param schlangenarten
     */
    public void setSchlangenarten(List<Schlangenart> schlangenarten) {
        this.schlangenarten = schlangenarten;
    }
    /**
     * getter von Schlangen
     * @return schlangen
     */
    public List<Schlange> getSchlangen() {
        return schlangen;
    }
    /**
     * setter von Schlangen
     * @param schlangen
     */
    public void setSchlangen(List<Schlange> schlangen) {
        this.schlangen = schlangen;
    }
    
    
}
