package de.fernuni.kurs01584.ss23.modell;

import java.util.List;

public class Schlange {
	/** 
	 * Attribute
	 * schlangenart: einziger Schlangenart für diese Schlange
	 * schlangengliedmenge: List von schlangenglied in Schlange
	 */
	private Schlangenart schlangenart;
	private List<Schlangenglied> schlangengliedmenge; 
	
	/**
	 * leere Konstruktor
	 */
	public Schlange() {
	    
	}
	/**
	 * Konstruktor mit schlangenart und schlangengliedmenge
	 * @param schlangenart einziger Schlangenart für diese Schlange
	 * @param schlangengliedmenge List von schlangenglied in Schlange
	 */
	public Schlange (Schlangenart schlangenart, List<Schlangenglied> schlangengliedmenge) {
	    this.schlangenart = schlangenart;
	    this.schlangengliedmenge = schlangengliedmenge;
	}
	
	/**
	 * getter von Schlangenart
	 * @return schlangenart einziger Schlangenart für diese Schlange
	 */
	public Schlangenart getSchlangenart() {
	    return schlangenart;
	}
	/**
	 * getter von Schlangengliedmenge
	 * @return schlangengliedmenge List von schlangenglied in Schlange
	 */
    public List<Schlangenglied> getSchlangengliedmenge() {
        return schlangengliedmenge;
    }
    /**
     * setter für Schlangenart
     * @param schlangenart einziger Schlangenart für diese Schlange
     */
    public void setSchlangenart(Schlangenart schlangenart) {
        this.schlangenart = schlangenart;
    }
    /**
     * setter für Schlangengliedmenge
     * @param schlangengliedmenge List von schlangenglied in Schlange
     */
    public void setSchlangengliedmenge(List<Schlangenglied> schlangengliedmenge) {
        this.schlangengliedmenge = schlangengliedmenge;
    }
}
