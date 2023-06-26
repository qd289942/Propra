package de.fernuni.kurs01584.ss23.modell;

import java.util.List;

public class Schlangenjagd {
    // Attribute
    private Zeit zeit;
    private Dschungel dschungel;
    private List<Schlangenart> schlangenarten;
    private List<Schlange> schlangen;
    
    // Konstruktor
    public Schlangenjagd() {
        
    }
    public Schlangenjagd(Zeit zeit, Dschungel dschungel, List<Schlangenart> schlangenarten, List<Schlange> schlangen) {
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

    public List<Schlangenart> getSchlangenarten() {
        return schlangenarten;
    }

    public void setSchlangenarten(List<Schlangenart> schlangenarten) {
        this.schlangenarten = schlangenarten;
    }

    public List<Schlange> getSchlangen() {
        return schlangen;
    }

    public void setSchlangen(List<Schlange> schlangen) {
        this.schlangen = schlangen;
    }
    
    
}
