package de.fernuni.kurs01584.ss23.modell;

import java.util.List;

public class Schlange {
	// TODO: Attribute
	private Schlangenart schlangenart;
	private List<Schlangenglied> schlangengliedmenge; 
	
	// TODO: Konstruktoren
	public Schlange() {
	    
	}
	public Schlange (Schlangenart schlangenart, List<Schlangenglied> schlangengliedmenge) {
	    this.schlangenart = schlangenart;
	    this.schlangengliedmenge = schlangengliedmenge;
	}
	
	// TODO: Methoden
	// add getters and setters
	public Schlangenart getSchlangenart() {
	    return schlangenart;
	}
    public List<Schlangenglied> getSchlangengliedmenge() {
        return schlangengliedmenge;
    }
    public void setSchlangenart(Schlangenart schlangenart) {
        this.schlangenart = schlangenart;
    }
    public void setSchlangengliedmenge(List<Schlangenglied> schlangengliedmenge) {
        this.schlangengliedmenge = schlangengliedmenge;
    }
}
