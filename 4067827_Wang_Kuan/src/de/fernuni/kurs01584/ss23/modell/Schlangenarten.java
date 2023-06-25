package de.fernuni.kurs01584.ss23.modell;

import java.util.List;

public class Schlangenarten {
    
    // Attribute
    private List<Schlangenart> schlangenarte;
    
    // Konstruktoren
    public Schlangenarten() {
    }

    public Schlangenarten(List<Schlangenart> schlangenarte) {
        this.schlangenarte = schlangenarte;
    }
    // Methoden getters and setters
    public List<Schlangenart> getSchlangenarte() {
        return schlangenarte;
    }

    public void setSchlangenarte(List<Schlangenart> schlangenarte) {
        this.schlangenarte = schlangenarte;
    }
    
    
    
}
