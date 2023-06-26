package de.fernuni.kurs01584.ss23.modell;

public class Zeit {
    // Attribute
    private String einheit;
    private double vorgabe;
    private double abgabe;
    
    // Konstruktor
    public Zeit() {
        
    }
    public Zeit(String einheit, double vorgabe, double abgabe) {
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

    public double getVorgabe() {
        return vorgabe;
    }

    public void setVorgabe(double vorgabe) {
        this.vorgabe = vorgabe;
    }

    public double getAbgabe() {
        return abgabe;
    }

    public void setAbgabe(double abgabe) {
        this.abgabe = abgabe;
    }
    
}
