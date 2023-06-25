package de.fernuni.kurs01584.ss23.modell;

public class Zeit {
    // Attribute
    private String einheit;
    private int vorgabe;
    private int abgabe;
    
    // Konstruktor
    public Zeit() {
        
    }
    public Zeit(String einheit, int vorgabe, int abgabe) {
        this.einheit = einheit;
        this.vorgabe = vorgabe;
        this.abgabe = abgabe;
    }
    
    // Methoden getters und setters
    
    public String getEinheit() {
        return einheit;
    }

    public void setEinheit(String einheit) {
        this.einheit = einheit;
    }

    public int getVorgabe() {
        return vorgabe;
    }

    public void setVorgabe(int vorgabe) {
        this.vorgabe = vorgabe;
    }

    public int getAbgabe() {
        return abgabe;
    }

    public void setAbgabe(int abgabe) {
        this.abgabe = abgabe;
    }
    
}
