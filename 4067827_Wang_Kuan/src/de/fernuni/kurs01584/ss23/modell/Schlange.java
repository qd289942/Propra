package de.fernuni.kurs01584.ss23.modell;

import java.util.ArrayList;

public class Schlange {
	// TODO: Attribute
	private Schlangenart schlangenart;
	private ArrayList<Schlangenglied> schlangengliedmenge; 
	// TODO: Konstruktoren
	public Schlange (Schlangenart schlangenart, ArrayList<Schlangenglied> schlangengliedmenge) {
	    this.schlangenart = schlangenart;
	    this.schlangengliedmenge = schlangengliedmenge;
	}
	// TODO: Methoden
	// add getters
	public Schlangenart getSchlangenart() {
	    return schlangenart;
	}
    public ArrayList<Schlangenglied> getSchlangengliedmenge() {
        return schlangengliedmenge;
    }
}
