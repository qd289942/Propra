package de.fernuni.kurs01584.ss23.modell;

import java.util.List;

public class Nachbarschaftsstruktur {
    // Attribute
    private String typ;
    private List<Parameter> parameters;
    
    // Konstruktor
    public Nachbarschaftsstruktur() {
        
    }
    public Nachbarschaftsstruktur(String typ, List<Parameter> parameters) {
        this.typ = typ;
        this.parameters = parameters;               
    }

    // Methodes
    // add the setters of class attributes
    public String getTyp() {
        return typ;
    }

    public void setTyp(String typ) {
        this.typ = typ;
    }

    public List<Parameter> getParameters() {
        return parameters;
    }

    public void setParameters(List<Parameter> parameters) {
        this.parameters = parameters;
    }
    
    
    
}
