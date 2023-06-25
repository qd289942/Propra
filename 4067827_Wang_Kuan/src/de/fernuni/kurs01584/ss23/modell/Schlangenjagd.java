package de.fernuni.kurs01584.ss23.modell;

import java.util.List;

public class Schlangenjagd {
    // Attribute
    private Zeit zeit;
    private Dschungel dschungel;
    private Schlangenarten schlangenarten;
    private Schlangen schlangen;
    
    // Konstruktor
    public Schlangenjagd() {
        
    }
    public Schlangenjagd(Zeit zeit, Dschungel dschungel, Schlangenarten schlangenarten, Schlangen schlangen) {
        this.zeit = zeit;
        this.dschungel = dschungel;
        this.schlangenarten = schlangenarten;
        this.schlangen = schlangen;
    }

    // Methoden getters und setters
    public Zeit getZeit() {
        return zeit;
    }

    public void setZeit(Zeit zeit) {
        this.zeit = zeit;
    }

    public Dschungel getDschungel() {
        return dschungel;
    }

    public void setDschungel(Dschungel dschungel) {
        this.dschungel = dschungel;
    }

    public Schlangenarten getSchlangenarten() {
        return schlangenarten;
    }

    public void setSchlangenarten(Schlangenarten schlangenarten) {
        this.schlangenarten = schlangenarten;
    }

    public Schlangen getSchlangen() {
        return schlangen;
    }

    public void setSchlangen(Schlangen schlangen) {
        this.schlangen = schlangen;
    }
    
    
}
