package de.fernuni.kurs01584.ss23.modell;

public class Parameter {
    // Attribute
    private int wert;
    
    // Kontruktor
    public Parameter() {
        
    }
    public Parameter(int wert) {
        super();
        this.wert = wert;
    }
    
    // getters und setters
    public int getWert() {
        return wert;
    }

    public void setWert(int wert) {
        this.wert = wert;
    }
    
}
