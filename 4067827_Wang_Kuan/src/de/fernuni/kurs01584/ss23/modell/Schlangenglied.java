package de.fernuni.kurs01584.ss23.modell;

public class Schlangenglied {
	// TODO: Attribute
	private Feld feld;
	// TODO: Konstruktoren
    public Schlangenglied(Feld feld) {
        this.feld = feld;
    }
    
    public Schlangenglied() {
 
    }
	// TODO: Methoden
    public Feld getFeld() {
        return feld;
    }
    public void setFeld(Feld feld) {
        this.feld = feld;
    }

}
